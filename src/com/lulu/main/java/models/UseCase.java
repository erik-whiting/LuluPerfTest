package com.lulu.main.java.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class UseCase extends Thread {
    public String name;
    public Path pathToScript;
    public String cmdToRunScript;

    public UseCase(String name, String pathToScript, String cmdToRunScript) {
        this.name = name;
        this.pathToScript = Paths.get(pathToScript);
        this.cmdToRunScript = cmdToRunScript;
    }

    public void run() {
        String execution_script = cmdToRunScript + " " + pathToScript;
        try {
            Process p = Runtime.getRuntime().exec(execution_script);
            HashMap<String, BufferedReader> streamHash = getStreams(p);
            reportStreams(streamHash);
        } catch (IOException e) {
            long threadId = Thread.currentThread().getId();
            System.out.println("Script " + name + ", thread ID: " + threadId + " has failed");
            e.printStackTrace();
        }
    }

    private static HashMap<String, BufferedReader> getStreams(Process p) {
        BufferedReader standardInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader standardError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        HashMap<String, BufferedReader> streamHash = new HashMap<>();
        streamHash.put("Input", standardInput);
        streamHash.put("Error", standardError);
        return streamHash;
    }

    private static void reportStreams(HashMap<String, BufferedReader> streamHash) throws IOException {
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
}
