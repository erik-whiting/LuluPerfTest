package com.lulu.main.java.models;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class CpuMonitor extends MetricMonitor {

    public CpuMonitor(String name, int metricCheckingFreq) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
    }

    @Override
    public double monitor() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return osBean.getProcessCpuLoad();
    }
}
