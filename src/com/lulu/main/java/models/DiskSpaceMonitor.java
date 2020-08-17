package com.lulu.main.java.models;

import java.io.File;

public class DiskSpaceMonitor extends MetricMonitor {

    public DiskSpaceMonitor(String name, int metricCheckingFreq, String dirToMonitor) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.directory = dirToMonitor;
    }

    @Override
    public double monitor() {
        File directoryToMonitor = new File(this.directory);
        return directoryToMonitor.getTotalSpace() - directoryToMonitor.getFreeSpace();
    }
}
