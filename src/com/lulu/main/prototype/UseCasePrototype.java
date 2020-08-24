package com.lulu.main.prototype;

import com.lulu.main.java.models.use_cases.UseCase;
import com.lulu.main.java.models.use_cases.UseCases;

import java.util.ArrayList;

public class UseCasePrototype {
    public static void main(String[] args) throws InterruptedException {
        String ucCustomerScriptRoot = "C:\\Users\\eedee\\Documents\\test_site_tests\\customer_user_group";
        String customerUcScript1 = ucCustomerScriptRoot + "\\explore_albums.py";
        String customerUcScript2 = ucCustomerScriptRoot + "\\explore_bands.py";

        UseCases useCases = new UseCases(new ArrayList<>() {
            {
                add(new UseCase("Explore Albums", customerUcScript1, "python", 2));
                add(new UseCase("Explore Bands", customerUcScript2, "python", 2));
            }
        });

        System.out.println("Starting use cases");
        useCases.start();
        System.out.println("Finishing");
        System.out.println(useCases.doneRunning());
    }
}
