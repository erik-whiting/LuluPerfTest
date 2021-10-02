package com.lulu.main.java.models.monitors;

import com.lulu.main.java.models.configurations.ReporterConfiguration;

import java.math.BigDecimal;

public class CpuMonitor extends MetricMonitor {
    public CpuMonitor(String name, int metricCheckingFreq, ReporterConfiguration reporterConfig) {
        super(name, metricCheckingFreq, reporterConfig);
        this.metric = Metric.CPU;
    }

    @Override
    public double monitor() {
        double ratio = os.getSystemCpuLoad();
        return BigDecimal.valueOf(ratio*100).round(this.scale).doubleValue();
    }
}
