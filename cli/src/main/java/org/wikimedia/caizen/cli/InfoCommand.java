package org.wikimedia.caizen.cli;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class InfoCommand extends Command {

    @Override
    public void execute(Args args) throws JsonProcessingException, IOException {
        writeJson(System.out, getProbe(args).getNode());
    }

}
