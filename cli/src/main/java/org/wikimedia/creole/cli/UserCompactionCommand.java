package org.wikimedia.creole.cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.wikimedia.cassandra.jmx.CompactionManager;
import org.wikimedia.cassandra.jmx.Connection;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.restrictions.Required;

@Command(name="user-compaction")
public class UserCompactionCommand extends BaseCommand {

    @Required
    @Arguments
    private List<String> arguments = new ArrayList<String>();

    /***
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try (Connection conn = new Connection(getJmxHost(), getJmxPort())) {
            CompactionManager mgr = new CompactionManager(conn);
            mgr.forceUserDefinedCompaction(arguments.toArray(new String[]{}));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
