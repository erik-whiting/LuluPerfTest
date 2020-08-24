package com.lulu.main.java.models.use_cases;

import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class RunnableUseCase implements Runnable {
    String name;
    String executionScript;
    volatile boolean isRunning = false;

    public RunnableUseCase(String name, Path pathToScript, String cmdToRunScript) {
        this.name = name;
        this.executionScript = cmdToRunScript + " " + pathToScript;
    }

    public void run() {
        isRunning = true;
        System.out.println("Running \"" + executionScript + "\"");
        try {
            Process p = Runtime.getRuntime().exec(executionScript);
            HashMap<String, BufferedReader> streamHash = UseCase.getStreams(p);
            UseCase.reportStreams(streamHash);
        } catch (IOException e) {
            long threadId = Thread.currentThread().getId();
            System.out.println("Script " + name + ", thread ID: " + threadId + " has failed");
            e.printStackTrace();
        }
        isRunning = false;
    }
}
