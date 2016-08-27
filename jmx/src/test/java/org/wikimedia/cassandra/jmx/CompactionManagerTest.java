package org.wikimedia.cassandra.jmx;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CompactionManagerTest extends AbstractJmxTestCase {

    private CompactionManager cManager;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.cManager = new CompactionManager(this.conn);
    }

    // XXX: These do nothing more than exercise the corresponding methods for the moment. 

    @Test
    public void testGetCompactions() throws IOException {
        this.cManager.getCompactions();
    }

    @Test
    public void testGetCompactionHistory() throws IOException {
        this.cManager.getCompactionHistory();
    }

}
