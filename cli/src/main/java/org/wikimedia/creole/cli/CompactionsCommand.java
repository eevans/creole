package org.wikimedia.creole.cli;

import java.io.IOException;
import java.util.List;

import org.wikimedia.cassandra.jmx.dto.Compaction;

import com.github.rvesse.airline.annotations.Command;

@Command(name="compactions")
public class CompactionsCommand extends JsonCommand<List<Compaction>> {

    /***
     * {@inheritDoc}
     */
    @Override
    public List<Compaction> get() throws IOException {
        return getProbe().getCompactions();
    }

}
