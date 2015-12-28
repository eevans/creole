package org.wikimedia.caizen.cli;

public interface Command {
    void execute(Args args) throws Exception;
}
