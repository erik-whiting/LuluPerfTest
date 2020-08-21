package com.lulu.main.java.models.monitors;

public class MemoryMonitor extends MetricMonitor {

    public MemoryMonitor(String name, int metricCheckingFreq) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.isMonitoring = false;
        this.metric = Metric.MEMORY;
    }

    @Override
    public double monitor() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        return totalMemory - freeMemory;
    }
}
