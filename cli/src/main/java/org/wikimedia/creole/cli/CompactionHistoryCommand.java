package org.wikimedia.creole.cli;

import java.io.IOException;
import java.util.Collection;

import org.wikimedia.cassandra.jmx.dto.CompactionHistoryItem;

import com.github.rvesse.airline.annotations.Command;

@Command(name="compaction-history")
public class CompactionHistoryCommand extends JsonCommand<Collection<CompactionHistoryItem>> {

    /***
     * {@inheritDoc}
     */
    @Override
    public Collection<CompactionHistoryItem> get() throws IOException {
        return getProbe().getCompactionHistory();
    }

}
