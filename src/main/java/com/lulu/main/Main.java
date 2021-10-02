package com.lulu.main;

import com.lulu.main.java.models.dsl.DslRunner;

public class Main {

    public static void main(String[] args)  {
        for (String s : args) {
            new DslRunner(s);
        }
    }
}
