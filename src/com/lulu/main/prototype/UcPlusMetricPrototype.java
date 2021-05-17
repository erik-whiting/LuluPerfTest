package com.lulu.main.prototype;

import com.lulu.main.java.models.configurations.ReporterConfiguration;
import com.lulu.main.java.models.monitors.CpuMonitor;
import com.lulu.main.java.models.monitors.MemoryMonitor;
import com.lulu.main.java.models.monitors.MetricMonitor;
import com.lulu.main.java.models.monitors.Monitors;
import com.lulu.main.java.models.use_cases.UseCase;
import com.lulu.main.java.models.use_cases.UseCases;

import java.util.ArrayList;

public class UcPlusMetricPrototype {
    public static void main(String[] args) throws InterruptedException {
        // Define Use cases
        String ucCustomerScriptRoot = "C:\\Users\\eedee\\Documents\\test_site_tests\\customer_user_group";
        String customerUcScript1 = ucCustomerScriptRoot + "\\explore_albums.py";
        String customerUcScript2 = ucCustomerScriptRoot + "\\explore_bands.py";

        UseCases useCases = new UseCases(new ArrayList<>() {
            {
                add(new UseCase(
                        "Explore Albums",
                        customerUcScript1,
                        "python",
                        2)
                );
                add(new UseCase(
                        "Explore Bands",
                        customerUcScript2,
                        "python",
                        2)
                );
            }
        });

        // Define monitors
        ReporterConfiguration reporterConfiguration = new ReporterConfiguration("String");
        MemoryMonitor memoryMonitor = new MemoryMonitor("Memory", 500, reporterConfiguration);
        CpuMonitor cpuMonitor = new CpuMonitor("CPU", 500, reporterConfiguration);
        ArrayList<MetricMonitor> monitorList = new ArrayList<>();
        monitorList.add(memoryMonitor);
        monitorList.add(cpuMonitor);
        Monitors monitors = new Monitors(monitorList);

        monitors.start();
        useCases.start();
        monitors.stopMonitoring();
    }
}
