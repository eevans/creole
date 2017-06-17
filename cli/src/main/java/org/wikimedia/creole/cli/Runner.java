package org.wikimedia.creole.cli;

import com.github.rvesse.airline.Cli;
import com.github.rvesse.airline.help.Help;

public class Runner {

    public static void main(String... args) throws Exception {
        parse(args).run();
    }

    public static Runnable parse(String... args) {
        Cli<Runnable> parser = buildParser();
        return parser.parse(args);
    }

    @SuppressWarnings("unchecked")
    public static Cli<Runnable> buildParser() {
        /*
         * The CLI parser is defined here.
         * 
         * To add a command to creole:
         * Add a class, implementing BaseCommand or JsonCommand,
         * depending on whether it should render output in json.
         * Annotate that class with @Command and any options it may need.
         * Add that class below to the withCommands(...) call.
         */
        
        return Cli.<Runnable>builder("java -jar creole.jar ")
                .withDescription("cassandra JMX client")
                .withDefaultCommand(Help.class) // Use the Airline built-in help system
                .withCommands(
                        InfoCommand.class,
                        NetstatCommand.class,
                        MetricsCommand.class,
                        CompactionsCommand.class,
                        TableInfoCommand.class,
                        UserCompactionCommand.class,
                        CompactionHistoryCommand.class)
                .build();
    }
}
