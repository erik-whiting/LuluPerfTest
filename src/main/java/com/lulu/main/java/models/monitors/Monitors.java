package com.lulu.main.java.models.monitors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Monitors {
    public Collection<MetricMonitor> metricMonitors;
    public ArrayList<Thread> runnableMonitors = new ArrayList<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Monitors(Collection<MetricMonitor> metricMonitors) {
        this.metricMonitors = metricMonitors;
        setRunnableMonitors();
    }

    public void start() {
        for (Thread thread : runnableMonitors) {
            executorService.execute(thread);
        }
        executorService.shutdown();
    }

    public void stopMonitoring() {
        executorService.shutdownNow();
        System.out.println("All monitors have stopped");
    }

    private void setRunnableMonitors() {
        for (MetricMonitor monitor : metricMonitors) {
            Thread thread = new Thread(monitor, monitor.threadName());
            runnableMonitors.add(thread);
        }
    }
}
