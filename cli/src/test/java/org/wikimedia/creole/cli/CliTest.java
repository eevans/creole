package org.wikimedia.creole.cli;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.rvesse.airline.help.Help;

public class CliTest {

    @Test
    public void testEmptyArgsShouldHelp() {
        Runnable cmd = Runner.parse(new String[]{});
        assertTrue(cmd instanceof Help);
    }

    @Test
    public void testHelp() throws Exception {
        assertSubcommand(Help.class, "help");
        Runnable command = Runner.parse(new String[] { "help" });
        command.run();
    }

    @Test
    public void testOptions() {
        Runnable action = Runner.parse(withRequired("info"));
        BaseCommand command = (BaseCommand) action;
        assertEquals(command.getJmxHost(), "localhost");
        assertEquals(command.getJmxPort(), 1234);
    }

    @Test
    public void testUserCompaction() {
        assertSubcommand(UserCompactionCommand.class, "user-compaction", "foo");
    }

    @Test
    public void testTableInfo() {
        assertSubcommand(TableInfoCommand.class, "table-info", "-k", "foo", "-t", "bar");
    }

    @Test
    public void testCompactionHistory() {
        assertSubcommand(CompactionHistoryCommand.class, "compaction-history");
    }

    @Test
    public void testCompactions() {
        assertSubcommand(CompactionsCommand.class, "compactions");
    }

    @Test
    public void testInfo() {
        assertSubcommand(InfoCommand.class, "info");
    }

    @Test
    public void testMetrics() {
        assertSubcommand(MetricsCommand.class, "metrics-dump");
    }

    @Test
    public void testNetstat() {
        assertSubcommand(NetstatCommand.class, "netstat");
    }

    private void assertSubcommand(final Class<? extends Runnable> commandClazz, String command, final String... args) {
        Runnable action = Runner.parse(withRequired(command, args));
        assertEquals(commandClazz, action.getClass());
    }

    private static String[] withRequired(String command, String... args) {
        List<String> arguments = new ArrayList<>();
        arguments.add(command);
        arguments.add("-H");
        arguments.add("localhost");
        arguments.add("-P");
        arguments.add("1234");
        arguments.addAll(Arrays.asList(args));
        return arguments.toArray(args);
    }

}
