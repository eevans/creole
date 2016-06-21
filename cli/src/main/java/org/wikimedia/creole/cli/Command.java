package org.wikimedia.creole.cli;

import java.io.IOException;
import java.io.PrintStream;

import org.wikimedia.cassandra.jmx.Probe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class Command {

    protected static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    abstract void execute(Args args) throws Exception;

    protected Probe getProbe(Args args) throws IOException {
        return new Probe(args.getJmxHost(), args.getJmxPort());
    }

    protected String mapObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    protected void writeJson(PrintStream out, Object obj) throws JsonProcessingException {
        out.println(mapObject(obj));
    }

}
