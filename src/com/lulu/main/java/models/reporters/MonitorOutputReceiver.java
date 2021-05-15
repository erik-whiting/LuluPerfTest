package com.lulu.main.java.models.reporters;

public class MonitorOutputReceiver {
    public String threadName;
    public double data;
    public long threadId;
    public long monitorIteration;

    public MonitorOutputReceiver(long threadId, long monitorIteration, String threadName, double data) {
        this.threadId = threadId;
        this.monitorIteration = monitorIteration;
        this.threadName = threadName;
        this.data = data;
    }
}
