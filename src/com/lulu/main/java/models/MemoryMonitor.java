package com.lulu.main.java.models;

public class MemoryMonitor extends MetricMonitor {

    public MemoryMonitor(String name, int metricCheckingFreq) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
    }

    @Override
    public double monitor() {
        return Runtime.getRuntime().totalMemory();
    }
}
