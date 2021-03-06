package com.lulu.main.java.models.dsl;

public class DslRunner {
    public DslParser parser;
    public DslRunner(String pathToScript) {
        this.parser = new DslParser(pathToScript);
        try {
            this.parser.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
