package com.lulu.main.java.models.use_cases;

import java.util.ArrayList;

public class UseCases {
    public ArrayList<UseCase> useCases;

    public UseCases(ArrayList<UseCase> useCases) {
        this.useCases = useCases;
    }

    public void start() throws InterruptedException {
        for (UseCase useCase : useCases) {
            System.out.println("Building " + useCase.name);
            useCase.run();
        }
    }

    public boolean doneRunning() {
        for (UseCase uc : useCases) if(!uc.isRunning()) return false;
        return true;
    }
}
