package org.wikimedia.caizen.cli;

import java.util.Collection;

import org.wikimedia.cassandra.jmx.JmxClient;
import org.wikimedia.cassandra.jmx.Probe;
import org.wikimedia.cassandra.jmx.dto.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class NetstatCommand implements Command {

    @Override
    // XXX: Untested
    public void execute(Args args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Collection<Stream> streams = new Probe(new JmxClient(args.getJmxHost(), args.getJmxPort())).getStreams();

        System.out.println(mapper.writeValueAsString(streams));
    }

}
