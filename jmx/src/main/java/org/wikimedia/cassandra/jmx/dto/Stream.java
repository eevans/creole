package org.wikimedia.cassandra.jmx.dto;

public class Stream {
    private String planId;
    private String description;
    // private CompositeData[] sessions;
    private Long currentRxBytes;
    private Long totalRxBytes;
    private Double rxPercentage;
    private Long currentTxBytes;
    private Long totalTxBytes;
    private Double txPercentage;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCurrentRxBytes() {
        return currentRxBytes;
    }

    public void setCurrentRxBytes(Long currentRxBytes) {
        this.currentRxBytes = currentRxBytes;
    }

    public Long getTotalRxBytes() {
        return totalRxBytes;
    }

    public void setTotalRxBytes(Long totalRxBytes) {
        this.totalRxBytes = totalRxBytes;
    }

    public Double getRxPercentage() {
        return rxPercentage;
    }

    public void setRxPercentage(Double rxPercentage) {
        this.rxPercentage = rxPercentage;
    }

    public Long getCurrentTxBytes() {
        return currentTxBytes;
    }

    public void setCurrentTxBytes(Long currentTxBytes) {
        this.currentTxBytes = currentTxBytes;
    }

    public Long getTotalTxBytes() {
        return totalTxBytes;
    }

    public void setTotalTxBytes(Long totalTxBytes) {
        this.totalTxBytes = totalTxBytes;
    }

    public Double getTxPercentage() {
        return txPercentage;
    }

    public void setTxPercentage(Double txPercentage) {
        this.txPercentage = txPercentage;
    }

}
