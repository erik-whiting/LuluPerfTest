package com.lulu.main.java.models.use_cases;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    private void setRunnableUseCases() {
        for (int i = 1; i <= numOfThreads; i++) {
            String threadName = name + i;
            RunnableUseCase ruc = new RunnableUseCase(threadName, pathToScript, cmdToRunScript);
            runnableUseCases.add(ruc);
        }
    }
}
