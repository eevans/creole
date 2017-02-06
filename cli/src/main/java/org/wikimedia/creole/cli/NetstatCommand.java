package org.wikimedia.creole.cli;

import java.util.Collection;

import org.wikimedia.cassandra.jmx.dto.Stream;

import com.github.rvesse.airline.annotations.Command;

@Command(name="netstat")
public class NetstatCommand extends JsonCommand<Collection<Stream>> {

    /***
     * {@inheritDoc}
     */
    @Override
    // XXX: Untested
    public Collection<Stream> get() throws Exception {
        return getProbe().getStreams();
    }

}
