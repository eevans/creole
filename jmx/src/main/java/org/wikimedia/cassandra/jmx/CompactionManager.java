package org.wikimedia.cassandra.jmx;

import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.TabularData;

import com.google.common.base.Joiner;

public class CompactionManager extends MBean {

    Joiner csvJoiner = Joiner.on(',').skipNulls();

    public CompactionManager(Connection client) {
        super(client, newObjectName("org.apache.cassandra.db:type=CompactionManager"));
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> getCompactions() throws IOException {
        return (List<Map<String, String>>) getAttribute("Compactions");
    }

    public TabularData getCompactionHistory() throws IOException {
        return (TabularData) getAttribute("CompactionHistory");
    }

    public void forceUserDefinedCompaction(String... files) throws IOException {
        invoke("forceUserDefinedCompaction", new Object[] { csvJoiner.join(files) }, new String[] { String.class.getName() });
    }

}
