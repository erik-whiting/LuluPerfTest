package com.lulu.main.java.models.monitors;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class CpuMonitor extends MetricMonitor {

    public CpuMonitor(String name, int metricCheckingFreq) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.metric = Metric.CPU;
    }

    @Override
    public double monitor() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getProcessCpuLoad();
    }
}
