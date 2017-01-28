package org.wikimedia.creole.cli;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class ArgsTest {

    @Test(expected = CmdLineException.class)
    public void testEmptyArgsShouldThrow() throws CmdLineException {
        Args a = new Args();
        new CmdLineParser(a).parseArgument(new String[] {});
    }

    @Test
    public void testHelp() throws CmdLineException {
        Args a = new Args();
        new CmdLineParser(a).parseArgument(new String[] { "--help" });
        assertTrue(a.needsHelp());
    }

    @Test
    public void testOptions() throws CmdLineException {
        Args a = new Args();
        new CmdLineParser(a).parseArgument(withRequired("info"));
        assertEquals(a.getJmxHost(), "localhost");
        assertEquals(a.getJmxPort(), 1234);
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

    private void assertSubcommand(final Class<? extends Command> commandClazz, final String... commands) {
        try {
            Args a = new Args();
            new CmdLineParser(a).parseArgument(withRequired(commands));
            assertTrue(a.getCommand().getClass().equals(commandClazz));

        } catch (CmdLineException e) {
            fail(e.getMessage());
        }
    }

    private static String[] withRequired(String... args) {
        List<String> arguments = new ArrayList<>();
        arguments.add("-H");
        arguments.add("localhost");
        arguments.add("-P");
        arguments.add("1234");
        arguments.addAll(Arrays.asList(args));
        return arguments.toArray(args);
    }

}
