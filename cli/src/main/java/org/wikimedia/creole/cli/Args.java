package org.wikimedia.creole.cli;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

public class Args {
    @Option(name = "-H", aliases = {
            "--jmx-host" }, required = true, metaVar = "HOST", usage = "Host of JMX endpoint to connect to")
    private String jmxHost;

    @Option(name = "-P", aliases = { "--jmx-port" }, required = true, metaVar = "PORT", usage = "JMX port number")
    private int jmxPort;

    @Argument(handler = SubCommandHandler.class, required = true, metaVar = "sub-command", usage = "Sub-command name")
    @SubCommands({
            @SubCommand(name = "info", impl = InfoCommand.class),
            @SubCommand(name = "netstat", impl = NetstatCommand.class),
            @SubCommand(name = "metrics-dump", impl = MetricsCommand.class),
            @SubCommand(name = "compactions", impl = CompactionsCommand.class),
            @SubCommand(name = "compaction-history", impl = CompactionHistoryCommand.class),
            @SubCommand(name = "table-info", impl = TableInfoCommand.class)})
    private Command command;

    @Option(name = "-h", aliases = { "--help" }, help = true, usage = "Print help synopsis")
    private boolean needsHelp;

    public String getJmxHost() {
        return jmxHost;
    }

    public int getJmxPort() {
        return jmxPort;
    }

    public boolean needsHelp() {
        return this.needsHelp;
    }

    public Command getCommand() {
        return this.command;
    }

}