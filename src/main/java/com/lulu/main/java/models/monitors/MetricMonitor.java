package com.lulu.main.java.models.monitors;

import com.lulu.main.java.models.configurations.ReporterConfiguration;
import com.lulu.main.java.models.reporters.MonitorOutputTransceiver;

import java.lang.management.ManagementFactory;
import java.math.MathContext;
import java.util.HashMap;

public abstract class MetricMonitor implements Monitoring, Runnable {
    public String name;
    public int metricCheckingFrequency;
    public String directory;
    public String sysCommandForMetric;
    public volatile boolean isMonitoring;
    public Metric metric;
    public ReporterConfiguration reporterConfiguration;
    public MathContext scale = new MathContext(5);
    public volatile MonitorOutputTransceiver transceiver;
    public com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public MetricMonitor(String name, int metricCheckingFrequency, ReporterConfiguration reporterConfig) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFrequency;
        this.reporterConfiguration = reporterConfig;
    }

    public void run() {
        isMonitoring = true;
        long monitorIteration = 1;
        prepareTransceiver();
        while (isMonitoring) {
            try {
                runningMethod();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            this.transceiver.receiveSignal(
                    Thread.currentThread().getId(),
                    monitorIteration,
                    this.threadName(),
                    monitor()
            );
            monitorIteration++;
        }
    }

    private void runningMethod() throws InterruptedException {
        try {
            if (Thread.currentThread().isInterrupted()) {
                stopMonitoring();
            } else {
                Thread.sleep(metricCheckingFrequency);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void prepareTransceiver() {
        this.transceiver = new MonitorOutputTransceiver(this.reporterConfiguration);
    }

    public void stopMonitoring() {
        isMonitoring = false;
        System.out.println(this.name + " Received stop command");
        this.transceiver.transmit();
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
