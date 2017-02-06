package org.wikimedia.creole.cli;

import java.io.IOException;

import org.wikimedia.cassandra.jmx.Probe;

import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;

public abstract class BaseCommand implements Runnable {

    @Required
    @Option(name = {"-H", "--jmx-host" }, description = "Host of JMX endpoint to connect to")
    private String jmxHost;

    @Required
    @Option(name = {"-P", "--jmx-port" }, description = "JMX port number")
    private int jmxPort;

    public String getJmxHost() {
        return jmxHost;
    }

    public int getJmxPort() {
        return jmxPort;
    }

    protected Probe getProbe() throws IOException {
        return new Probe(getJmxHost(), getJmxPort());
    }

}
