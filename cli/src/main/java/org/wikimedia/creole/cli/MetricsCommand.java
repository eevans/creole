package org.wikimedia.creole.cli;

import java.util.Collection;

import org.wikimedia.cassandra.jmx.dto.Metric;

import com.github.rvesse.airline.annotations.Command;

@Command(name="metrics-dump")
public class MetricsCommand extends JsonCommand<Collection<Metric>> {

    /***
     * {@inheritDoc}
     */
    @Override
    public Collection<Metric> get() throws Exception {
        return getProbe().getMetrics();
    }

}
