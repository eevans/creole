package org.wikimedia.cassandra.jmx;

import static org.cassandraunit.utils.EmbeddedCassandraServerHelper.CASSANDRA_RNDPORT_YML_FILE;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Before;
import org.wikimedia.cassandra.jmx.Connection;

public abstract class AbstractJmxTestCase {

    static final String JMX_PORT_PROPERTY = "cassandra.jmx.local.port";
    static final int JMX_PORT_VALUE = 7099;

    protected Connection conn;
    
    @Before
    public void setUp() throws Exception {
        System.setProperty(JMX_PORT_PROPERTY, Integer.toString(JMX_PORT_VALUE));
        EmbeddedCassandraServerHelper.startEmbeddedCassandra(CASSANDRA_RNDPORT_YML_FILE);
        this.conn = new Connection("localhost", JMX_PORT_VALUE);
    }

    @After
    public void tearDown() throws Exception {
        System.clearProperty(JMX_PORT_PROPERTY);
        this.conn.close();
    }

}
