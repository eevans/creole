package org.wikimedia.cassandra.jmx;

import static org.wikimedia.cassandra.jmx.Util.newObjectName;

import java.io.IOException;

public class ColumnFamily extends MBean {

    public ColumnFamily(Connection client, String keyspace, String columnfamily) {
        super(
                client,
                newObjectName(
                        "org.apache.cassandra.db:type=ColumnFamilies,keyspace=%s,columnfamily=%s",
                        keyspace,
                        columnfamily));
    }

    public double getDroppableTombstoneRatio() throws IOException {
        return (double) getAttribute("DroppableTombstoneRatio");
    }

}
