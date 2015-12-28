package org.wikimedia.cassandra.jmx;

import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StorageService extends MBean {

    public StorageService(JmxClient client) {
        super(client, newObjectName("org.apache.cassandra.db:type=StorageService"));
    }

    public String getReleaseVersion() throws IOException {
        return (String) getAttribute("ReleaseVersion");
    }

    public String getLocalHostId() throws IOException {
        return (String) getAttribute("LocalHostId");
    }

    public String getOperationMode() throws IOException {
        return (String) getAttribute("OperationMode");
    }

    public boolean isInitialized() throws IOException {
        return (Boolean) getAttribute("Initialized");
    }

    public boolean isRPCServerRunning() throws IOException {
        return (Boolean) getAttribute("RPCServerRunning");
    }

    public String getLoadString() throws IOException {
        return (String) getAttribute("LoadString");
    }

    public Integer getCurrentGenerationNumber() throws IOException {
        return (Integer) getAttribute("CurrentGenerationNumber");
    }

    public String getEndpoint() throws IOException {
        return getHostIdToEndpoint().get(getHostIdToEndpoint());
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getHostIdToEndpoint() throws IOException {
        return (Map<String, String>) getAttribute("HostIdToEndpoint");
    }

    @SuppressWarnings("unchecked")
    public List<String> getTokens() throws IOException {
        return (List<String>) getAttribute("Tokens");
    }

}
