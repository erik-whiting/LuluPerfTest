package com.lulu.main.java.models.dsl;

import com.lulu.main.java.models.monitors.*;
import com.lulu.main.java.models.use_cases.UseCase;
import com.lulu.main.java.models.use_cases.UseCases;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DslParser {
    public Monitors monitors;
    public UseCases useCases;
    public DslParser(String path) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONObject script = (JSONObject) obj;
            buildTest(script);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws InterruptedException {
        this.monitors.start();
        this.useCases.start();
        while (this.useCases.isRunning()) {
            Thread.sleep(100);
        }
        this.monitors.stopMonitoring();
    }

    private void buildTest(JSONObject script) {
        ArrayList<UseCase> useCases = new ArrayList<>();
        ArrayList<MetricMonitor> metricMonitors = new ArrayList<>();

        JSONObject performanceTest = (JSONObject) script.get("Performance Test");

        JSONArray useCasesJson = (JSONArray) performanceTest.get("useCases");
        useCasesJson.forEach(uc -> useCases.add(buildUseCase((JSONObject) uc)));

        JSONArray monitorsJson = (JSONArray) performanceTest.get("monitors");
        monitorsJson.forEach(m -> metricMonitors.add(buildMonitor((JSONObject) m)));

        this.useCases = new UseCases(useCases);
        this.monitors = new Monitors(metricMonitors);
    }

    private UseCase buildUseCase(JSONObject useCaseJson) {
        String name = (String) useCaseJson.get("name");
        String pathToScript = (String) useCaseJson.get("script");
        String command = (String) useCaseJson.get("command");
        String threadCount = (String) useCaseJson.get("threads");
        int intThreadCount = Integer.parseInt(threadCount);
        return new UseCase(name, pathToScript, command, intThreadCount);
    }

    private MetricMonitor buildMonitor(JSONObject monitorJson) {
        String name = (String) monitorJson.get("name");
        String stringInterval = (String) monitorJson.get("every");
        int interval = Integer.parseInt(stringInterval);
        switch(name) {
            case "memory":
                return new MemoryMonitor(name, interval);
            case "CPU":
                return new CpuMonitor(name, interval);
            case "disk":
                String dirToMonitor = (String) monitorJson.get("directory");
                return new DiskSpaceMonitor(name, interval, dirToMonitor);
            default:
                String command = (String) monitorJson.get("command");
                return new OtherMonitor(name, interval, command);
        }
    }
}
