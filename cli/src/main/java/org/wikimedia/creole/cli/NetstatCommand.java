package org.wikimedia.creole.cli;

public class NetstatCommand extends Command {

    @Override
    // XXX: Untested
    public void execute(Args args) throws Exception {
        writeJson(System.out, getProbe(args).getStreams());
    }

}
