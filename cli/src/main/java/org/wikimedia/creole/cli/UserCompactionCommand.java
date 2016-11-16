package org.wikimedia.creole.cli;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.wikimedia.cassandra.jmx.CompactionManager;
import org.wikimedia.cassandra.jmx.Connection;

public class UserCompactionCommand extends Command {

    @Argument(required = true)
    private List<String> arguments = new ArrayList<String>();

    @Override
    public void execute(Args args) throws Exception {
        try (Connection conn = new Connection(args.getJmxHost(), args.getJmxPort())) {
            CompactionManager mgr = new CompactionManager(conn);
            mgr.forceUserDefinedCompaction(arguments.toArray(new String[]{}));
        }
    }

}
