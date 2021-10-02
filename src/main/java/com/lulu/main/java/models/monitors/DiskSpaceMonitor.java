package com.lulu.main.java.models.monitors;

import com.lulu.main.java.models.configurations.ReporterConfiguration;

import java.io.File;

public class DiskSpaceMonitor extends MetricMonitor {

    public DiskSpaceMonitor(String name, int metricCheckingFreq, ReporterConfiguration reporterConfig, String dirToMonitor) {
        super(name, metricCheckingFreq, reporterConfig);
        this.directory = dirToMonitor;
        this.metric = Metric.DISKSPACE;
    }

    @Override
    public double monitor() {
        File directoryToMonitor = new File(this.directory);
        return directoryToMonitor.getTotalSpace() - directoryToMonitor.getFreeSpace();
    }
}
