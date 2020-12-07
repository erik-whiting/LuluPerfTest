package com.lulu.main.java.models.monitors;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.MathContext;

public class MemoryMonitor extends MetricMonitor {
    public com.sun.management.OperatingSystemMXBean os;
    public double osMemorySize;
    public MathContext scale;

    public MemoryMonitor(String name, int metricCheckingFreq) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.isMonitoring = false;
        this.metric = Metric.MEMORY;

        this.os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        this.osMemorySize = os.getTotalPhysicalMemorySize();
        this.scale = new MathContext(4);
    }

    @Override
    public double monitor() {
        double ratio = (osMemorySize - os.getFreePhysicalMemorySize())/osMemorySize;
        BigDecimal bd = BigDecimal.valueOf(ratio*100).round(scale);
        return bd.doubleValue();
    }
}
