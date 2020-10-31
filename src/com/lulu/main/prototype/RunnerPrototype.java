package com.lulu.main.prototype;

import com.lulu.main.java.models.dsl.DslRunner;

public class RunnerPrototype {
    public static void main(String[] args) {
        String pathToJSON = "C:\\Users\\eedee\\technicalstuff\\javastuff\\LuluPerfTest\\src\\com\\lulu\\main\\prototype\\DslPrototype.json";
        new DslRunner(pathToJSON);
    }
}
