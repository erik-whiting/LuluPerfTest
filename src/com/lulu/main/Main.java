package com.lulu.main;

import com.lulu.main.java.models.monitors.*;
import com.lulu.main.java.models.UseCase;
import com.lulu.main.java.models.UseCases;
import com.lulu.main.java.models.UserGroup;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // For future prototyping
        String sut_url = "http://127.0.0.1:5000/";
        UserGroup customers = makeCustomerUserGroup();
        UserGroup employees = makeEmployeeUserGroup();

        // Monitor Prototype
        MemoryMonitor memoryMonitor = new MemoryMonitor("Memory", 500);
        DiskSpaceMonitor diskSpaceMonitor = new DiskSpaceMonitor("Disk Space", 500, "C:\\");
        CpuMonitor cpuMonitor = new CpuMonitor("CPU", 500);

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

    public static UserGroup makeCustomerUserGroup() {
        String ucCustomerScriptRoot = "C:\\Users\\eedee\\Documents\\test_site_tests\\customer_user_group";
        String customerUcScript1 = ucCustomerScriptRoot + "\\explore_albums.py";
        String customerUcScript2 = ucCustomerScriptRoot + "\\explore_bands.py";

        UseCase customerUc1 = new UseCase("Explore Albums", customerUcScript1, "python");
        UseCase customerUc2 = new UseCase("Explore Bands", customerUcScript2, "python");

        UseCases useCases = new UseCases(
            new ArrayList<>() {
                { add(customerUc1); add(customerUc2); }
            }
        );
        return new UserGroup("Customers", useCases);
    }

    public static UserGroup makeEmployeeUserGroup() {
        String ucEmployeeScripRoot = "C:\\Users\\eedee\\Documents\\test_site_tests\\employee_user_group";
        String employeeUcScript = ucEmployeeScripRoot + "\\make_sale.py";

        UseCase employeeUc = new UseCase("Make Sale", employeeUcScript, "python");
        UseCases useCases = new UseCases(
            new ArrayList<>() {
                { add(employeeUc); }
            }
        );

        return new UserGroup("Employees", useCases);
    }
}
