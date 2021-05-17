package com.lulu.main.prototype;

import com.lulu.main.java.models.configurations.ReporterConfiguration;
import com.lulu.main.java.models.monitors.*;

import java.util.ArrayList;

public class MetricPrototype {
    public static void main(String[] args) throws InterruptedException {
        ReporterConfiguration reporterConfiguration = new ReporterConfiguration("string");
        MemoryMonitor memoryMonitor = new MemoryMonitor("Memory", 500, reporterConfiguration);
        DiskSpaceMonitor diskSpaceMonitor = new DiskSpaceMonitor("Disk Space", 500, reporterConfiguration, "C:\\");
        CpuMonitor cpuMonitor = new CpuMonitor("CPU", 500, reporterConfiguration);

        ArrayList<MetricMonitor> monitorList = new ArrayList<>();
        monitorList.add(memoryMonitor);
        monitorList.add(diskSpaceMonitor);
        monitorList.add(cpuMonitor);
        Monitors monitors = new Monitors(monitorList);

        monitors.start();
        Thread.sleep(10000);
        System.out.println("Stopping thread");
        monitors.stopMonitoring();
    }
}
