package org.wikimedia.cassandra.jmx;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.google.common.base.Throwables;

public class Connection implements Closeable {

    private static final String URL = "service:jmx:rmi:///jndi/rmi://%s:%d/jmxrmi";

    private final JMXServiceURL jmxUrl;

    private JMXConnector jmxc;
    private MBeanServerConnection mbeanServerConn;

    public Connection(String host, int port) throws IOException {
        try {
            this.jmxUrl = new JMXServiceURL(String.format(URL, host, port));
        }
        catch (MalformedURLException e) {
            throw Throwables.propagate(e);
        }
        
        connect();
    }

    public void close() throws IOException {
        this.jmxc.close();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jmxUrl == null) ? 0 : jmxUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Connection other = (Connection) obj;
        if (jmxUrl == null) {
            if (other.jmxUrl != null)
                return false;
        }
        else if (!jmxUrl.equals(other.jmxUrl))
            return false;
        return true;
    }

    MBeanServerConnection getConnection() {
        return this.mbeanServerConn;
    }

    private void connect() throws IOException {
        this.jmxc = JMXConnectorFactory.connect(this.jmxUrl, Collections.emptyMap());
        this.mbeanServerConn = jmxc.getMBeanServerConnection();
    }

}
