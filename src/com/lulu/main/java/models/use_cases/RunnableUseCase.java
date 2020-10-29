package com.lulu.main.java.models.use_cases;

import java.io.InputStreamReader;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;

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
            try (BufferedReader standardError = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                String s;
                while ((s = standardError.readLine()) != null) {
                    System.out.println("Error:");
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            long threadId = Thread.currentThread().getId();
            System.out.println("Script " + name + ", thread ID: " + threadId + " has failed");
            e.printStackTrace();
        }
        isRunning = false;
    }
}
