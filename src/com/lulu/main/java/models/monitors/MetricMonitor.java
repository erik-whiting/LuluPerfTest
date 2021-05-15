package com.lulu.main.java.models.monitors;

import com.lulu.main.java.models.reporters.MonitorOutputReceiver;
import com.lulu.main.java.models.reporters.MonitorOutputTransmitter;

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
    public MathContext scale = new MathContext(5);
    public com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public void run() {
        isMonitoring = true;
        long monitorIteration = 1;
        while (isMonitoring) {
            try {
                Thread.sleep(metricCheckingFrequency);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            MonitorOutputReceiver mor = new MonitorOutputReceiver(
                    Thread.currentThread().getId(),
                    monitorIteration,
                    this.threadName(),
                    monitor()
            );
            MonitorOutputTransmitter transmitter = new MonitorOutputTransmitter(mor);
            monitorIteration++;
            System.out.println(transmitter.transmitAsJSON());
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
