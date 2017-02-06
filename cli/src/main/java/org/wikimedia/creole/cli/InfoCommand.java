package org.wikimedia.creole.cli;

import java.io.IOException;

import org.wikimedia.cassandra.jmx.dto.Node;

import com.github.rvesse.airline.annotations.Command;

@Command(name="info")
public class InfoCommand extends JsonCommand<Node> {

    @Override
    public Node get() throws IOException {
        return getProbe().getNode();
    }

}
