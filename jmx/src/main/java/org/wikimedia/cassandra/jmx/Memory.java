package org.wikimedia.cassandra.jmx;

import static java.lang.management.ManagementFactory.MEMORY_MXBEAN_NAME;
import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;

import javax.management.openmbean.CompositeDataSupport;

public class Memory extends MBean {

    public Memory(Connection client) {
        super(client, newObjectName(MEMORY_MXBEAN_NAME));
    }

    public long getUsed() throws IOException {
        return (long) getHeapMemoryUsage().get("used");
    }

    public long getMax() throws IOException {
        return (long) getHeapMemoryUsage().get("max");
    }

    private CompositeDataSupport getHeapMemoryUsage() throws IOException {
        return (CompositeDataSupport) getAttribute("HeapMemoryUsage");
    }

}
