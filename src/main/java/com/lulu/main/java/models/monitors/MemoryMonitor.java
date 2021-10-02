package com.lulu.main.java.models.monitors;

import com.lulu.main.java.models.configurations.ReporterConfiguration;

import java.math.BigDecimal;

public class MemoryMonitor extends MetricMonitor {
    public double osMemorySize;

    public MemoryMonitor(String name, int metricCheckingFreq, ReporterConfiguration reporterConfig) {
        super(name, metricCheckingFreq, reporterConfig);
        this.isMonitoring = false;
        this.metric = Metric.MEMORY;
        this.osMemorySize = os.getTotalPhysicalMemorySize();
    }

    @Override
    public double monitor() {
        double ratio = (osMemorySize - os.getFreePhysicalMemorySize())/osMemorySize;
        return BigDecimal.valueOf(ratio*100).round(this.scale).doubleValue();
    }
}
