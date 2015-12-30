package org.wikimedia.cassanddra.jmx;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wikimedia.cassandra.jmx.Connection;
import org.wikimedia.cassandra.jmx.StorageService;

public class StorageServiceTest extends AbstractJmxTestCase {

    private Connection conn;
    private StorageService ss;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.conn = new Connection("localhost", JMX_PORT_VALUE);
        this.ss = new StorageService(this.conn);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        this.conn.close();
    }

    @Test
    public void testReleaseVersion() throws IOException {
        assertThat(ss.getReleaseVersion(), any(String.class));
    }

    @Test
    public void testLocalHostId() throws IOException {
        assertThat(UUID.fromString(ss.getLocalHostId()).toString(), is(ss.getLocalHostId()));
    }

}
