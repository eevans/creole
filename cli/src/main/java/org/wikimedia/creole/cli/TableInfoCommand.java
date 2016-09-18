package org.wikimedia.creole.cli;

import org.kohsuke.args4j.Option;

public class TableInfoCommand extends Command {

    @Option(name = "-k", aliases = { "--keyspace" }, required = true, metaVar = "KEYSPACE")
    private String keyspaceName;

    @Option(name = "-t", aliases = { "--table" }, required = true, metaVar = "TABLE")
    private String tableName;

    @Override
    // XXX: Untested
    public void execute(Args args) throws Exception {
        writeJson(System.out, getProbe(args).getTableInfo(this.keyspaceName, this.tableName));
    }

}
