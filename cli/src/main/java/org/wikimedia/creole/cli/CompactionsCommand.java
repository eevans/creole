package org.wikimedia.creole.cli;

import java.io.IOException;

public class CompactionsCommand extends Command {

    @Override
    public void execute(Args args) throws IOException {
        writeJson(System.out, getProbe(args).getCompactions());
    }

}
