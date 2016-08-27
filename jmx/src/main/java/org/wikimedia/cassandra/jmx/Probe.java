package org.wikimedia.cassandra.jmx;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.management.MBeanAttributeInfo;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

import org.wikimedia.cassandra.jmx.dto.Compaction;
import org.wikimedia.cassandra.jmx.dto.CompactionHistoryItem;
import org.wikimedia.cassandra.jmx.dto.Metric;
import org.wikimedia.cassandra.jmx.dto.Node;
import org.wikimedia.cassandra.jmx.dto.Stream;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Probe {

    private final Connection client;

    public Probe(String host, int port) throws IOException {
        this(new Connection(checkNotNull(host, "host argument"), checkNotNull(port, "port argument")));
    }

    public Probe(Connection client) {
        this.client = checkNotNull(client);
    }

    public List<Compaction> getCompactions() throws IOException {
        List<Compaction> compactions = Lists.newArrayList();
        for (Map<String, String> compaction : new CompactionManager(this.client).getCompactions())
            compactions.add(Compaction.create(compaction));
        return compactions;
    }

    public Collection<CompactionHistoryItem> getCompactionHistory() throws IOException {
        List<CompactionHistoryItem> items = Lists.newArrayList();
        for (Object item : new CompactionManager(this.client).getCompactionHistory().values())
            items.add(CompactionHistoryItem.create((CompositeData) item));
        return items;
    }

    // XXX: Untested
    public Collection<Stream> getStreams() throws IOException {
        List<Stream> streams = Lists.newArrayList();
        StreamManager manager = new StreamManager(this.client);

        for (CompositeData data : manager.getCurrentStreams()) {
            Stream s = new Stream();
            s.setPlanId((String) data.get("planId"));
            s.setDescription((String) data.get("description"));
            // s.get("sessions");
            s.setCurrentRxBytes((Long) data.get("currentRxBytes"));
            s.setTotalRxBytes((Long) data.get("totalRxBytes"));
            s.setRxPercentage((Double) data.get("rxPercentage"));
            s.setCurrentTxBytes((Long) data.get("currentTxBytes"));
            s.setTotalTxBytes((Long) data.get("totalTxBytes"));
            s.setTxPercentage((Double) data.get("txPercentage"));
            streams.add(s);
        }

        return streams;
    }

    public Node getNode() throws IOException {
        StorageService storage = new StorageService(this.client);
        Runtime runtime = new Runtime(this.client);
        Memory memory = new Memory(this.client);
        EndpointSnitchInfo snitch = new EndpointSnitchInfo(this.client);

        Node node = new Node();
        node.setVersion(storage.getReleaseVersion());
        node.setId(storage.getLocalHostId());
        node.setMode(storage.getOperationMode());
        node.setGossipActive(storage.isInitialized());
        node.setThriftActive(storage.isRPCServerRunning());
        node.setLoad(storage.getLoadString());
        node.setGenerationNo(storage.getCurrentGenerationNumber());
        node.setTokens(storage.getTokens());
        node.setUpTime(runtime.getUptime());
        node.setHeapUsed(memory.getUsed());
        node.setHeapSize(memory.getMax());
        node.setDatacenter(snitch.getDatacenter());
        node.setRack(snitch.getRack());

        return node;
    }

    public Collection<Metric> getMetrics() throws IOException {
        List<Metric> metrics = Lists.newArrayList();

        ObjectName search = newObjectName("org.apache.cassandra.metrics:*");
        for (ObjectInstance instance : this.client.getConnection().queryMBeans(search, null)) {
            MBean mBean = new MBean(this.client, instance.getObjectName());
            Metric metric = new Metric(metricName(mBean.getObjectName()), mBean.getClassName());
            List<Metric.Attribute> attributes = Lists.newArrayList();
            metric.setAttributes(attributes);
            metrics.add(metric);

            /* @formatter:off
             *  _____.___.                   .__               
             *  \__  |   | ____  ____   _____|  |__            
             *   /   |   |/ __ \/ __ \ /  ___/  |  \           
             *   \____   \  ___|  ___/ \___ \|   Y  \          
             *   / ______|\___  >___  >____  >___|  / /\ /\ /\ 
             *   \/           \/    \/     \/     \/  \/ \/ \/ 
             *
             * EstimatedRowSizeHistogram and EstimatedColumnCountHistogram are of type JmxReporter$Gauge, but
             * instead of a numeric, the value instead holds a long[]. Cassandra tooling calculates a
             * histogram from this value, so we do the same here (borrowing code from Cassandra, in fact).
             * 
             * @formatter:on
             */

            String metricName = instance.getObjectName().getKeyProperty("name");
            // No special-casing needed, return raw attribute values.
            if (!(metricName.equals("EstimatedRowSizeHistogram") || metricName.equals("EstimatedColumnCountHistogram"))) {
                for (MBeanAttributeInfo ai : mBean.listAttributes()) {
                    attributes.add(new Metric.Attribute(ai.getName(), ai.getType(), mBean.getAttribute(ai.getName())));
                }
            }
            // Special-cased histogram handling.
            else {
                // TODO: Should we do something about mBean.className()?
                Object value = mBean.getAttribute("Value");
                double[] percentiles = metricPercentilesAsArray((long[]) value);
                attributes.add(new Metric.Attribute("50thPercentile", "double", percentiles[0]));
                attributes.add(new Metric.Attribute("75thPercentile", "double", percentiles[1]));
                attributes.add(new Metric.Attribute("95thPercentile", "double", percentiles[2]));
                attributes.add(new Metric.Attribute("98thPercentile", "double", percentiles[3]));
                attributes.add(new Metric.Attribute("99thPercentile", "double", percentiles[4]));
                attributes.add(new Metric.Attribute("Min", "double", percentiles[5]));
                attributes.add(new Metric.Attribute("Max", "double", percentiles[6]));
            }

        }

        return metrics;
    }

    // Copy-pasta from o.a.cassandra.tools.NodeProbe
    private static double[] metricPercentilesAsArray(long[] counts) {
        double[] result = new double[7];

        if (counts == null || counts.length == 0) {
            Arrays.fill(result, Double.NaN);
            return result;
        }

        double[] offsetPercentiles = new double[] { 0.5, 0.75, 0.95, 0.98, 0.99 };
        long[] offsets = new EstimatedHistogram(counts.length).getBucketOffsets();
        EstimatedHistogram metric = new EstimatedHistogram(offsets, counts);

        if (metric.isOverflowed()) {
            System.err.println(
                    String.format(
                            "EstimatedHistogram overflowed larger than %s, unable to calculate percentiles",
                            offsets[offsets.length - 1]));
            for (int i = 0; i < result.length; i++) {
                result[i] = Double.NaN;
            }
        }
        else {
            for (int i = 0; i < offsetPercentiles.length; i++) {
                result[i] = metric.percentile(offsetPercentiles[i]);
            }
        }
        result[5] = metric.min();
        result[6] = metric.max();
        return result;
    }

    private static String metricName(ObjectName oName) {
        StringBuilder builder = new StringBuilder();

        // Ideally we'd be able to build a deterministic metric name by iterating over the already
        // parsed property list, but as it's a hash map, the ordering is lost, and we're forced to resort to
        // string manipulation.

        String propsString = oName.getKeyPropertyListString();

        // If type=ColumnFamily, but keyspace=null, then add this metric to a special "all" keyspace. This is
        // what Dropwizard's GraphiteReporter does, and we aim to be compatible.
        if (oName.getKeyProperty("type").equals("ColumnFamily")) {
            if (oName.getKeyProperty("keyspace") == null) {
                propsString = propsString.replaceFirst("type=ColumnFamily", "type=ColumnFamily,keyspace=all");
            }
        }

        // Add the remaining properties in the order they were defined.
        List<String> properties = Splitter.on(",").trimResults().splitToList(propsString);
        for (int i = 0; i < properties.size(); i++) {
            List<String> kv = Splitter.on("=").trimResults().limit(2).splitToList(properties.get(i));
            builder.append(scrub(kv.get(1)));
            if (i < (properties.size() - 1)) {
                builder.append('.');
            }
        }

        return builder.toString();
    }

    /** scrub problematic characters */
    private static String scrub(String name) {
        return name.replace(' ', '-');
    }

}
