package com.lulu.main.java.models;

import com.lulu.main.java.models.use_cases.UseCases;

public class UserGroup {
    public String name;
    public UseCases useCases;

    public UserGroup(String name, UseCases useCases) {
        this.name = name;
        this.useCases = useCases;
    }

    public void runUseCases() throws InterruptedException { useCases.start(); }
}
