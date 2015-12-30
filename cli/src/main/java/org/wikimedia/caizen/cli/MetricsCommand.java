package org.wikimedia.caizen.cli;

public class MetricsCommand extends Command {

    @Override
    public void execute(Args args) throws Exception {
        writeJson(System.out, getProbe(args).getMetrics());
    }

}
