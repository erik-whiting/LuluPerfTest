package com.lulu.main.java.models.monitors;

import java.util.HashMap;

public abstract class MetricMonitor implements Monitoring, Runnable {
    public String name;
    public int metricCheckingFrequency;
    public String directory;
    public String sysCommandForMetric;
    public volatile boolean isMonitoring;
    public Metric metric;

    public void run() {
        isMonitoring = true;
        while (isMonitoring) {
            try {
                Thread.sleep(metricCheckingFrequency);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println(monitor());
        }
    }

    public void stopMonitoring() {
        isMonitoring = false;
        System.out.println(this.name + " Received stop command");
    }

    public String threadName() {
        HashMap<Metric, String> metricStringMap = new HashMap<>();
        metricStringMap.put(Metric.MEMORY, "Memory");
        metricStringMap.put(Metric.CPU, "CPU");
        metricStringMap.put(Metric.DISKSPACE, "Disk Space");
        metricStringMap.put(Metric.OTHER, "Other");
        return metricStringMap.get(this.metric);
    }
}
