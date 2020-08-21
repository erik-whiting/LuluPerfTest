package com.lulu.main.java.models.monitors;

import java.io.File;

public class DiskSpaceMonitor extends MetricMonitor {

    public DiskSpaceMonitor(String name, int metricCheckingFreq, String dirToMonitor) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.directory = dirToMonitor;
        this.metric = Metric.DISKSPACE;
    }

    @Override
    public double monitor() {
        File directoryToMonitor = new File(this.directory);
        return directoryToMonitor.getTotalSpace() - directoryToMonitor.getFreeSpace();
    }
}
