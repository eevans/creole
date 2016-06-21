package org.wikimedia.creole.agent;

import java.io.IOException;

import org.wikimedia.cassandra.jmx.Probe;

import spark.Route;
import spark.Spark;

public class Runner {

    private static Route wrapContentType(Route route) {
        return (request, response) -> {
            response.type("application/json; charset=utf-8");
            return route.handle(request, response);
        };
    }

    // Wraps Spark#get to side-step some of the boilerplate associated with regular JSON responses.
    private static void get(String path, Route route) {
        Spark.get(path, "application/json", wrapContentType(route), new JsonTransformer());
    }

    public static void main(String... args) throws IOException {

        if (args.length != 2) {
            System.err.println("Exactly two arguments required (host and port)");
            System.exit(1);
        }

        final Probe probe = new Probe(args[0], Integer.parseInt(args[1]));

        get("/v1/info",    (req, res) -> probe.getNode());
        get("/v1/streams", (req, res) -> probe.getStreams());
        get("/v1/metrics", (req, res) -> probe.getMetrics());

    }

}
