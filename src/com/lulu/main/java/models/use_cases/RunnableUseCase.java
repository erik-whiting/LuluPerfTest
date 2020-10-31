package com.lulu.main.java.models.use_cases;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunnableUseCase implements Runnable {
    String name;
    String executionScript;
    volatile boolean isRunning = true; // Has to be defaulted to true

    public RunnableUseCase(String name, Path pathToScript, String cmdToRunScript) {
        this.name = name;
        this.executionScript = cmdToRunScript + " " + pathToScript;
    }

    public void run() {
        isRunning = true;
        System.out.println("Running \"" + executionScript + "\"");
        try {
            Process p = Runtime.getRuntime().exec(executionScript);
            ProcessHandle ph = p.toHandle();
            CompletableFuture<ProcessHandle> onExit = ph.onExit();
            onExit.get();
            onExit.thenAccept(ph_ -> isRunning = false);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            isRunning = false;
        }
        isRunning = false;
    }
}
