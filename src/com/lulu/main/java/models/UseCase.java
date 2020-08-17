package com.lulu.main.java.models;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UseCase {
    public String name;
    public Path pathToScript;
    public String cmdToRunScript;

    public UseCase(String name, String pathToScript, String cmdToRunScript) {
        this.name = name;
        this.pathToScript = Paths.get(pathToScript);
        this.cmdToRunScript = cmdToRunScript;
    }
}
