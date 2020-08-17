package com.lulu.main.java.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OtherMonitor extends MetricMonitor {

    public OtherMonitor(String name, int metricCheckingFreq, String command) {
        this.name = name;
        this.metricCheckingFrequency = metricCheckingFreq;
        this.sysCommandForMetric = command;
    }

    @Override
    public double monitor() {
        double retVal = 0;
        try {
            Process process = Runtime.getRuntime().exec(this.sysCommandForMetric);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                retVal = Double.parseDouble(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
