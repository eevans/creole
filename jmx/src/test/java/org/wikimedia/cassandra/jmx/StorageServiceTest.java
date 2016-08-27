package org.wikimedia.cassandra.jmx;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.wikimedia.cassandra.jmx.StorageService;

public class StorageServiceTest extends AbstractJmxTestCase {

    private StorageService ss;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.ss = new StorageService(this.conn);
    }

    @Test
    public void testIsGossipRunning() throws IOException {
        assertThat(ss.isGossipRunning(), is(true));
    }

    @Test
    public void testIsRPCServerRunning() throws IOException {
        assertThat(ss.isRPCServerRunning(), is(true));
    }

    @Test
    public void testIsNativeTransportRunning() throws IOException {
        assertThat(ss.isNativeTransportRunning(), is(true));
    }

    @Test
    public void testLocalHostId() throws IOException {
        assertThat(UUID.fromString(ss.getLocalHostId()).toString(), is(ss.getLocalHostId()));
    }

    @Test
    public void testIsJoined() throws IOException {
        assertThat(ss.isJoined(), is(true));
    }

    @Test
    public void testGetStreamThroughputMbPerSec() throws IOException {
        assertThat(ss.getStreamThroughputMbPerSec(), any(Integer.class));
    }

    @Test
    public void testGetTokenToEndpointMap() throws IOException {
        assertThat(ss.getTokenToEndpointMap().size(), equalTo(1));
    }

    @Test
    public void testGetHostIdMap() throws IOException {
        assertThat(ss.getHostIdMap().size(), equalTo(1));
    }

    @Test
    public void testGetEndpointToHostId() throws IOException {
        assertThat(ss.getEndpointToHostId().size(), equalTo(1));
    }

//    @Test
//    public void testGetHostIdToEndpoint() throws IOException {
//        assertThat(ss.getHostIdToEndpoint().size(), equalTo(1));
//    }

    @Test
    public void testGetTokens() throws IOException {
        assertThat(ss.getTokens().size(), equalTo(1));
    }

    @Test
    public void testGetLoadString() throws IOException {
        assertThat(ss.getLoadString(), any(String.class));
    }

    @Test
    public void testGetLoadMap() throws IOException {
        assertThat(ss.getLoadMap(), any(Map.class));
    }

    @Test
    public void testReleaseVersion() throws IOException {
        assertThat(ss.getReleaseVersion(), any(String.class));
    }

    @Test
    public void testGetSchemaVersion() throws IOException {
        assertThat(UUID.fromString(ss.getSchemaVersion()).toString(), equalTo(ss.getSchemaVersion()));
    }

    @Test
    public void testGetLeavingNodes() throws IOException {
        assertThat(ss.getLeavingNodes().size(), equalTo(0));
    }

    @Test
    public void testGetMovingNodes() throws IOException {
        assertThat(ss.getMovingNodes().size(), equalTo(0));
    }

    @Test
    public void testGetJoiningNodes() throws IOException {
        assertThat(ss.getJoiningNodes().size(), equalTo(0));
    }

    @Test
    public void testGetLiveNodes() throws IOException {
        assertThat(ss.getLiveNodes().size(), equalTo(1));
    }

    @Test
    public void testGetUnreachableNodes() throws IOException {
        assertThat(ss.getUnreachableNodes().size(), equalTo(0));
    }

    @Test
    public void testGetCurrentGenerationNumber() throws IOException {
        assertThat(ss.getCurrentGenerationNumber(), any(Integer.class));
    }

    @Test
    public void testGetSnapshotDetails() throws IOException {
        
    }

}
