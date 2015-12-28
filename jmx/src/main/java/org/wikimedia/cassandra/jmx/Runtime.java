package org.wikimedia.cassandra.jmx;

import static java.lang.management.ManagementFactory.RUNTIME_MXBEAN_NAME;
import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;

public class Runtime extends MBean {

    public Runtime(JmxClient client) {
        super(client, newObjectName(RUNTIME_MXBEAN_NAME));
    }

    /** JVM uptime in milliseconds. */
    public long getUptime() throws IOException {
        return (long) getAttribute("Uptime");
    }

}
