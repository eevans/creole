package org.wikimedia.caizen.agent;

import static spark.Spark.get;

import java.io.IOException;

import org.wikimedia.cassandra.jmx.Probe;

public class Runner {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Exactly two arguments required (host and port)");
            System.exit(1);
        }

        Probe probe = new Probe(args[0], Integer.parseInt(args[1]));

        get("/v1/info", (request, response) -> {
            response.type("application/json; charset=utf-8");
            return probe.getNode();
        } , new JsonTransformer());

    }

}
