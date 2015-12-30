package org.wikimedia.cassandra.jmx;

import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;
import java.util.Set;

import javax.management.openmbean.CompositeData;

public class StreamManager extends MBean {

    public StreamManager(Connection client) {
        super(client, newObjectName("org.apache.cassandra.net:type=StreamManager"));
    }

    @SuppressWarnings("unchecked")
    public Set<CompositeData> getCurrentStreams() throws IOException {
        return (Set<CompositeData>) getAttribute("CurrentStreams");
    }

}
