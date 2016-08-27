package org.wikimedia.cassandra.jmx.dto;

import java.util.Map;
import java.util.UUID;

import javax.management.openmbean.CompositeData;

import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
import com.google.common.collect.Maps;

public class CompactionHistoryItem {
    private static MapSplitter splitter = Splitter.on(',').omitEmptyStrings().trimResults().withKeyValueSeparator(':');

    private UUID id;
    private String keyspace;
    private String columnfamily;
    private long compactedAt;
    private long bytesIn;
    private long bytesOut;
    private Map<Long, Integer> rowsMerged;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    public String getColumnfamily() {
        return columnfamily;
    }

    public void setColumnfamily(String columnfamily) {
        this.columnfamily = columnfamily;
    }

    public long getCompactedAt() {
        return compactedAt;
    }

    public void setCompactedAt(long compactedAt) {
        this.compactedAt = compactedAt;
    }

    public long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(long bytesOut) {
        this.bytesOut = bytesOut;
    }

    public Map<Long, Integer> getRowsMerged() {
        return rowsMerged;
    }

    public void setRowsMerged(Map<Long, Integer> rowsMerged) {
        this.rowsMerged = rowsMerged;
    }

    public static CompactionHistoryItem create(CompositeData data) {
        CompactionHistoryItem item = new CompactionHistoryItem();
        item.setBytesIn((long) data.get("bytes_in"));
        item.setBytesOut((long) data.get("bytes_out"));
        item.setColumnfamily((String) data.get("columnfamily_name"));
        item.setCompactedAt((long) data.get("compacted_at"));
        item.setId(UUID.fromString((String) data.get("id")));
        item.setKeyspace((String) data.get("keyspace_name"));
        item.setRowsMerged(parseRowsMerged((String) data.get("rows_merged")));
        return item;
    }

    private static Map<Long, Integer> parseRowsMerged(String rows) {
        Map<Long, Integer> rowsMerged = Maps.newHashMap();

        rows = rows.replaceAll("\\{", "");
        rows = rows.replaceAll("\\}", "");
        rows = rows.trim();

        for (Map.Entry<String, String> entry : splitter.split(rows).entrySet())
            rowsMerged.put(Long.parseLong(entry.getKey()), Integer.parseInt(entry.getValue()));

        return rowsMerged;
    }

}
