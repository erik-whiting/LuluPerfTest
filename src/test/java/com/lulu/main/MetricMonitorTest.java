package com.lulu.main;

import static org.junit.Assert.*;

import com.lulu.main.java.models.configurations.ReporterConfiguration;
import com.lulu.main.java.models.monitors.CpuMonitor;
import com.lulu.main.java.models.monitors.DiskSpaceMonitor;
import com.lulu.main.java.models.monitors.MemoryMonitor;
import com.lulu.main.java.models.monitors.OtherMonitor;
import org.junit.Test;

public class MetricMonitorTest {

    // These are mostly smoke tests to guard
    // against poor refactoring

    public ReporterConfiguration config = new ReporterConfiguration("json");

    @Test
    public void testCpuMonitor() {
        CpuMonitor monitor = new CpuMonitor("CPU", 100, config);
        assertEquals("CPU", monitor.name);
        assertEquals(100, monitor.metricCheckingFrequency);
        assertEquals(config, monitor.reporterConfiguration);
        double output = monitor.monitor();
        assertNotEquals(-1, output);
        assertFalse(output > 1.0);
    }

    @Test
    public void testDiskSpaceMonitor() {
        DiskSpaceMonitor monitor = new DiskSpaceMonitor("Disk", 100, config, "./");
        assertEquals("Disk", monitor.name);
        assertEquals(100, monitor.metricCheckingFrequency);
        assertEquals(config, monitor.reporterConfiguration);
        double output = monitor.monitor();
        assertFalse(output < 0);
        assertTrue(output > 0);
    }

    @Test
    public void testMemoryMonitor() {
        MemoryMonitor monitor = new MemoryMonitor("Memory", 100, config);
        assertEquals("Memory", monitor.name);
        assertEquals(100, monitor.metricCheckingFrequency);
        assertEquals(config, monitor.reporterConfiguration);
        double output = monitor.monitor();
        assertFalse(output < 0);
        assertTrue(output > 0);
    }

    @Test
    public void testOtherMonitor() {
        String command = "ls";
        OtherMonitor monitor = new OtherMonitor("Other", 100, config, command);
        assertEquals("Other", monitor.name);
        assertEquals(100, monitor.metricCheckingFrequency);
        assertEquals(config, monitor.reporterConfiguration);
    }
}
