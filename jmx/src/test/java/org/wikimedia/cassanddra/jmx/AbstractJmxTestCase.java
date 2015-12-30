package org.wikimedia.cassanddra.jmx;

import static org.cassandraunit.utils.EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractJmxTestCase {

    static final String JMX_PORT_PROPERTY = "cassandra.jmx.local.port";
    static final int JMX_PORT_VALUE = 7099;

    @Before
    public void setUp() throws Exception {
        System.setProperty(JMX_PORT_PROPERTY, Integer.toString(JMX_PORT_VALUE));
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(CASSANDRA_RNDPORT_YML_FILE);
    }

    @After
    public void tearDown() throws Exception {
        System.clearProperty(JMX_PORT_PROPERTY);
    }

}
