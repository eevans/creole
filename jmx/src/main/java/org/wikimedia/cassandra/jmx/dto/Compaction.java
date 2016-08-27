package org.wikimedia.cassandra.jmx.dto;

import java.util.Map;
import java.util.UUID;

public class Compaction {
    private UUID id;
    private UUID compactionId;
    private String keyspace;
    private String columnFamily;
    private String taskType;
    private long total;
    private long completed;
    private String unit;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCompactionId() {
        return compactionId;
    }

    public void setCompactionId(UUID compactionId) {
        this.compactionId = compactionId;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    public String getColumnFamily() {
        return columnFamily;
    }

    public void setColumnFamily(String columnFamily) {
        this.columnFamily = columnFamily;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static Compaction create(Map<String, String> compaction) {
        Compaction obj = new Compaction();
        obj.setColumnFamily(compaction.get("columnfamily"));
        obj.setCompactionId(UUID.fromString(compaction.get("compactionId")));
        obj.setCompleted(Long.parseLong(compaction.get("completed")));
        obj.setId(UUID.fromString(compaction.get("id")));
        obj.setKeyspace(compaction.get("keyspace"));
        obj.setTaskType(compaction.get("taskType"));
        obj.setTotal(Long.parseLong(compaction.get("total")));
        obj.setUnit(compaction.get("unit"));
        return obj;
    }

}
