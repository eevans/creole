package org.wikimedia.cassandra.jmx;

import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;

public class EndpointSnitchInfo extends MBean {

    public EndpointSnitchInfo(JmxClient client) {
        super(client, newObjectName("org.apache.cassandra.db:type=EndpointSnitchInfo"));
    }

    public String getDatacenter() throws IOException {
        return (String) invoke("getDatacenter", new Object[] { getEndpoint() }, new String[] { "java.lang.String" });
    }

    public String getRack() throws IOException {
        return (String) invoke("getRack", new Object[] { getEndpoint() }, new String[] { "java.lang.String" });
    }

    private String getEndpoint() throws IOException {
        return new StorageService(this.client).getEndpoint();
    }

}