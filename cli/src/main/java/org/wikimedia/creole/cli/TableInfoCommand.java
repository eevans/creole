package org.wikimedia.creole.cli;

import org.wikimedia.cassandra.jmx.dto.ColumnFamily;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.restrictions.Required;

@Command(name="table-info")
public class TableInfoCommand extends JsonCommand<ColumnFamily> {

    @Required
    @Option(name = {"-k", "--keyspace" })
    private String keyspaceName;

    @Required
    @Option(name = {"-t","--table" })
    private String tableName;

    public String getKeyspaceName() {
        return keyspaceName;
    }

    public String getTableName() {
        return tableName;
    }

    /***
     * {@inheritDoc}
     */
    @Override
    // XXX: Untested
    public ColumnFamily get() throws Exception {
        return getProbe().getTableInfo(getKeyspaceName(), getTableName());
    }

}
