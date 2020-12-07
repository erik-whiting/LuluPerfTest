package com.lulu.main.java.models.monitors;

import java.math.BigDecimal;

public class CpuMonitor extends MetricMonitor {
    public CpuMonitor(String name, int metricCheckingFreq) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.metric = Metric.CPU;
    }

    @Override
    public double monitor() {
        double ratio = os.getSystemCpuLoad();
        return BigDecimal.valueOf(ratio*100).round(this.scale).doubleValue();
    }
}
