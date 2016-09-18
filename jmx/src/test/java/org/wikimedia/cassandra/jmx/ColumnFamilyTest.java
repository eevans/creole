package org.wikimedia.cassandra.jmx;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
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
    public void testGetName() {
        assertThat(this.cf.getName(), is(equalTo("local")));
    }

    @Test
    public void testGetKeyspace() {
        assertThat(this.cf.getKeyspace(), is(equalTo("system")));
    }

    @Test
    public void testGetCompactionStrategyClass() throws IOException {
        assertThat(this.cf.getCompactionStrategyClass(), not(isEmptyOrNullString()));
    }

    @Test
    public void testGetAutoCompactionDisabled() throws IOException {
        assertThat(this.cf.getAutoCompactionDisabled(), anything());
    }

    @Test
    public void testGetCompactionParameters() throws IOException {
        assertThat(this.cf.getCompactionParameters(), not(nullValue()));
    }

    @Test
    public void testGetCompressionParameters() throws IOException {
        assertThat(this.cf.getCompressionParameters(), not(nullValue()));
    }

    @Test
    public void testGetMinimumCompactionThreshold() throws IOException {
        assertThat(this.cf.getMinimumCompactionThreshold(), is(greaterThan(0)));
    }

    @Test
    public void testGetMaximumCompactionThreshold() throws IOException {
        assertThat(this.cf.getMaximumCompactionThreshold(), is(greaterThan(0)));
    }

    @Test
    public void testGetBuiltIndexes() throws IOException {
        assertThat(this.cf.getBuiltIndexes(), not(nullValue()));
    }

    @Test
    public void testGetUnleveledSSTables() throws IOException {
        assertThat(this.cf.getUnleveledSSTables(), anything());
    }

    public void testGetSSTableCountPerLevel() throws IOException {
        assertThat(this.cf.getSSTableCountPerLevel(), not(nullValue()));
    }

    @Test
    public void testGetDroppableTombstoneRatio() throws IOException {
        assertThat(this.cf.getDroppableTombstoneRatio(), greaterThan(0.0d));
    }

}
