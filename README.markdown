Caizen
======

Quickstart
----------

Build:

    $ mvn install
    ...

Usage (CLI):

    $ java -jar cli/target/caizen-cli-1.0.0-SNAPSHOT-jar-with-dependencies.jar -H localhost -P 7100 info
    {
      "id" : "599dd96a-9325-4f62-bafd-c1731c5ddf9e",
      "version" : "2.1.12-SNAPSHOT",
      "mode" : "NORMAL",
      "gossipActive" : true,
      "thriftActive" : true,
      "load" : "260.12 KB",
      "generationNo" : 1450922283,
      "upTime" : 132302526,
      "heapSize" : 5.1904512E8,
      "heapUsed" : 1.66324744E8,
      "datacenter" : "datacenter1",
      "rack" : "rack1",
      "tokens" : [ "-9213735168323425775", "-9193985466760200741", ... ]
    }

Usage (REST API):

     $ java -jar agent/target/caizen-agent-1.0.0-SNAPSHOT-jar-with-dependencies.jar localhost 7100
     [Thread-1] INFO org.eclipse.jetty.util.log - Logging initialized @308ms
     [Thread-1] INFO spark.webserver.JettySparkServer - == Spark has ignited ...
     [Thread-1] INFO spark.webserver.JettySparkServer - >> Listening on 0.0.0.0:4567
     [Thread-1] INFO org.eclipse.jetty.server.Server - jetty-9.3.z-SNAPSHOT
     [Thread-1] INFO org.eclipse.jetty.server.ServerConnector - Started ServerConnector@7e047db4{HTTP/1.1,[http/1.1]}{0.0.0.0:4567}
     [Thread-1] INFO org.eclipse.jetty.server.Server - Started @372ms
     ...

...meanwhile, in another terminal:

    $ curl -D - http://localhost:4567/v1/info
    HTTP/1.1 200 OK
    Date: Mon, 28 Dec 2015 22:13:00 GMT
    Content-Type: application/json; charset=utf-8
    Transfer-Encoding: chunked
    Server: Jetty(9.3.z-SNAPSHOT)

    {"id":"599dd96a-9325-4f62-bafd-c1731c5ddf9e","version":"2.1.12-SNAPSHOT","mode":"NORMAL","gossipActive":true, ... }

