package com.lulu.main.java.models.use_cases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class UseCase {
    public String name;
    public Path pathToScript;
    public String cmdToRunScript;
    public int numOfThreads;
    public ArrayList<RunnableUseCase> runnableUseCases = new ArrayList<>();

    public UseCase(String name, String pathToScript, String cmdToRunScript, int numOfThreads) {
        this.name = name;
        this.pathToScript = Paths.get(pathToScript);
        this.cmdToRunScript = cmdToRunScript;
        this.numOfThreads = numOfThreads;
    }

    public void run() {
        setRunnableUseCases();
        System.out.println("Preparing " + name + " threads");
        for (RunnableUseCase ruc : runnableUseCases) {
            Thread t = new Thread(ruc, ruc.name);
            t.start();
        }
    }

    public boolean isRunning() {
        for (RunnableUseCase ruc : runnableUseCases) if(!ruc.isRunning) return false;
        return true;
    }

    public static HashMap<String, BufferedReader> getStreams(Process p) {
        BufferedReader standardInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader standardError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        HashMap<String, BufferedReader> streamHash = new HashMap<>();
        streamHash.put("Input", standardInput);
        streamHash.put("Error", standardError);
        return streamHash;
    }

    public static void reportStreams(HashMap<String, BufferedReader> streamHash) throws IOException {
        String s;
        while ((s = streamHash.get("Input").readLine()) != null) {
            System.out.println("Output:");
            System.out.println(s);
        }
        while ((s = streamHash.get("Error").readLine()) != null) {
            System.out.println("Error:");
            System.out.println(s);
        }
    }

    private void setRunnableUseCases() {
        for (int i = 1; i <= numOfThreads; i++) {
            String threadName = name + i;
            RunnableUseCase ruc = new RunnableUseCase(threadName, pathToScript, cmdToRunScript);
            runnableUseCases.add(ruc);
        }
    }
}
