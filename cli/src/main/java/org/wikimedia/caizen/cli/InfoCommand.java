package org.wikimedia.caizen.cli;

import java.io.IOException;

import org.wikimedia.cassandra.jmx.JmxClient;
import org.wikimedia.cassandra.jmx.Probe;
import org.wikimedia.cassandra.jmx.dto.Node;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class InfoCommand implements Command {

    @Override
    public void execute(Args args) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Node info = new Probe(new JmxClient(args.getJmxHost(), args.getJmxPort())).getNode();
        System.out.println(mapper.writeValueAsString(info));
    }

}
