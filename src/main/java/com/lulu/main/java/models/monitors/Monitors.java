package com.lulu.main.java.models.monitors;

import java.util.ArrayList;
import java.util.Collection;

public class Monitors {
    public Collection<MetricMonitor> metricMonitors;
    public ArrayList<Thread> runnableMonitors = new ArrayList<>();

    public Monitors(Collection<MetricMonitor> metricMonitors) {
        this.metricMonitors = metricMonitors;
        setRunnableMonitors();
    }

    public void start() {
        for (Thread thread : runnableMonitors) {
            thread.start();
        }
    }

    public void stopMonitoring() {
        for (MetricMonitor monitor : metricMonitors) {
            monitor.stopMonitoring();
        }
    }

    private void setRunnableMonitors() {
        for (MetricMonitor monitor : metricMonitors) {
            Thread thread = new Thread(monitor, monitor.threadName());
            runnableMonitors.add(thread);
        }
    }
}
