package org.wikimedia.cassandra.jmx.dto;

import java.util.List;

public class Node {

    private String id;
    private String version;
    private String mode;
    private boolean gossipActive;
    private boolean thriftActive;
    private String load;
    private int generationNo;
    private long upTime;
    private double heapSize;
    private double heapUsed;
    private String datacenter;
    private String rack;
    private List<String> tokens;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isGossipActive() {
        return gossipActive;
    }

    public void setGossipActive(boolean gossipActive) {
        this.gossipActive = gossipActive;
    }

    public boolean isThriftActive() {
        return thriftActive;
    }

    public void setThriftActive(boolean thriftActive) {
        this.thriftActive = thriftActive;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public int getGenerationNo() {
        return generationNo;
    }

    public void setGenerationNo(int generationNo) {
        this.generationNo = generationNo;
    }

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public double getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(double heapSize) {
        this.heapSize = heapSize;
    }

    public double getHeapUsed() {
        return heapUsed;
    }

    public void setHeapUsed(double heapUsed) {
        this.heapUsed = heapUsed;
    }

    public String getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

}
