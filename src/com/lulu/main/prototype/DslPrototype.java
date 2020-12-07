package com.lulu.main.prototype;

import com.lulu.main.java.models.dsl.DslParser;
import com.lulu.main.java.models.monitors.Monitors;
import com.lulu.main.java.models.use_cases.UseCases;

import java.io.IOException;

public class DslPrototype {
    public static void main(String[] args) throws IOException, InterruptedException {
        String pathToJSON = "C:\\Users\\eedee\\technicalstuff\\javastuff\\LuluPerfTest\\src\\com\\lulu\\main\\prototype\\DslPrototype.json";
        DslParser parser = new DslParser(pathToJSON);
        Monitors monitors = parser.monitors;
        UseCases useCases = parser.useCases;

        monitors.start();
        useCases.start();
    }
}
