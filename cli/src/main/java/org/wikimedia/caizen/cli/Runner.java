package org.wikimedia.caizen.cli;

import java.io.PrintStream;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Runner {

    private static void usage(CmdLineParser parser, PrintStream writer) {
        writer.println(String.format("java %s [options...] <sub-command>", Runner.class.getName()));
        parser.printUsage(writer);
        writer.println();
    }

    public static void main(String... args) throws Exception {
        Args cmdArgs = new Args();
        CmdLineParser parser = new CmdLineParser(cmdArgs);

        try {
            parser.parseArgument(args);
        }
        catch (CmdLineException e) {
            System.err.println(e.getLocalizedMessage());
            usage(parser, System.err);
            System.exit(1);
        }

        if (cmdArgs.needsHelp()) {
            usage(parser, System.out);
            System.exit(0);
        }

        cmdArgs.getCommand().execute(cmdArgs);
    }

}
