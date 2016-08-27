package org.wikimedia.cassandra.jmx;

import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.TabularData;

public class StorageService extends MBean {

    public StorageService(Connection client) {
        super(client, newObjectName("org.apache.cassandra.db:type=StorageService"));
    }

    public boolean isGossipRunning() throws IOException {
        return (Boolean) getAttribute("GossipRunning");
    }

    public boolean isRPCServerRunning() throws IOException {
        return (Boolean) getAttribute("RPCServerRunning");
    }

    public boolean isNativeTransportRunning() throws IOException {
        return (Boolean) getAttribute("NativeTransportRunning");
    }

    public String getLocalHostId() throws IOException {
        return (String) getAttribute("LocalHostId");
    }

    public boolean isJoined() throws IOException {
        return (Boolean) getAttribute("Joined");
    }

    public int getStreamThroughputMbPerSec() throws IOException {
        return (Integer) getAttribute("StreamThroughputMbPerSec");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getTokenToEndpointMap() throws IOException {
        return (Map<String, String>) getAttribute("TokenToEndpointMap");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getHostIdMap() throws IOException {
        return (Map<String, String>) getAttribute("HostIdMap");
    }

    public Map<String, String> getEndpointToHostId() throws IOException {
        return getHostIdMap();
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getHostIdToEndpoint() throws IOException {
        return (Map<String, String>) getAttribute("HostIdToEndpoint");
    }

    @SuppressWarnings("unchecked")
    public List<String> getTokens() throws IOException {
        return (List<String>) getAttribute("Tokens");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getLoadMap() throws IOException {
        return (Map<String, String>) getAttribute("LoadMap");
    }
    
    public String getLoadString() throws IOException {
        return (String) getAttribute("LoadString");
    }

    public String getReleaseVersion() throws IOException {
        return (String) getAttribute("ReleaseVersion");
    }

    public String getSchemaVersion() throws IOException {
        return (String)getAttribute("SchemaVersion");
    }

    @SuppressWarnings("unchecked")
    public List<String> getLeavingNodes() throws IOException {
        return (List<String>) getAttribute("LeavingNodes");
    }

    @SuppressWarnings("unchecked")
    public List<String> getMovingNodes() throws IOException {
        return (List<String>) getAttribute("MovingNodes");
    }

    @SuppressWarnings("unchecked")
    public List<String> getJoiningNodes() throws IOException {
        return (List<String>) getAttribute("JoiningNodes");
    }

    @SuppressWarnings("unchecked")
    public List<String> getLiveNodes() throws IOException {
        return (List<String>) getAttribute("LiveNodes");
    }

    @SuppressWarnings("unchecked")
    public List<String> getUnreachableNodes() throws IOException {
        return (List<String>) getAttribute("UnreachableNodes");
    }

    public Integer getCurrentGenerationNumber() throws IOException {
        return (Integer) getAttribute("CurrentGenerationNumber");
    }

    @SuppressWarnings("unchecked")
    public Map<String, TabularData> getSnapshotDetails() throws IOException {
        return (Map<String, TabularData>) getAttribute("SnapshotDetails");
    }

    // TODO
    // Map<String, String> getLoggingLevels()
    // String getRemovalStatus()
    
    public String getOperationMode() throws IOException {
        return (String) getAttribute("OperationMode");
    }
    
    // TODO
    // boolean isStarting()
    // String getDrainProgress()
    // Map<InetAddress, Float> getOwnership()
    // int getExceptionCount()
    // double getTraceProbability()
    // double getTracingProbability()
    // String[] getAllDataFileLocations()
    // String getCommitLogLocation()
    // String getSavedCachesLocation()
    // List<String> getKeyspaces()
    // String getPartitionerName()
    // String getClusterName()
    // int getCompactionThroughputMbPerSec()
    // int getTombstoneWarnThreshold()
    // int getTombestoneFailureThreshold()
    // boolean IsIncrementalBackupsEnabled()

    public boolean isInitialized() throws IOException {
        return (Boolean) getAttribute("Initialized");
    }

    String getEndpoint() throws IOException {
        return getHostIdToEndpoint().get(getHostIdToEndpoint());
    }

    public void forceKeyspaceCompaction(boolean split, String keyspaceName, String... columnFamilies) throws IOException {
        invoke(
                "forceKeyspaceCompaction",
                new Object[] { keyspaceName, columnFamilies },
                new String[] { String.class.getName(), String[].class.getName() });
    }

}
