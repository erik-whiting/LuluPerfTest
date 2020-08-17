package com.lulu.main.java.models;

public abstract class MetricMonitor implements Monitoring {
    public String name;
    public int metricCheckingFrequency;
    public String directory;
    public String sysCommandForMetric;
}
