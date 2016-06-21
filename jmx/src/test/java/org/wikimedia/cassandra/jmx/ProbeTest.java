package org.wikimedia.cassandra.jmx;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.wikimedia.cassandra.jmx.Probe;
import org.wikimedia.cassandra.jmx.dto.Metric;

public class ProbeTest extends AbstractJmxTestCase {

    private Probe probe;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.probe = new Probe(this.conn);
    }

    @Test
    public void test() throws IOException {
//        for (Metric metric : this.probe.getMetrics()) {
//            for (Metric.Attribute attr : metric.getAttributes()) {
//                if (!metric.getType().startsWith("org.apache.cassandra.metrics.CassandraMetricsRegistry"))
//                    continue;
//                System.err
//                        .printf("org.apache.cassandra.metrics.%s.%s %s%n", metric.getName(), attr.getName(), attr.getValue());
//            }
//        }
    }

}
