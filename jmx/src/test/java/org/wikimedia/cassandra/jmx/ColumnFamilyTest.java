package org.wikimedia.cassandra.jmx;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ColumnFamilyTest extends AbstractJmxTestCase {

    private ColumnFamily cf;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.cf = new ColumnFamily(this.conn, "system", "local");
    }

    @Test
    public void testGetDroppableTombstoneRatio() throws IOException {
        assertThat(this.cf.getDroppableTombstoneRatio(), greaterThan(0.0d));
    }

}
