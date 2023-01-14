package com.mail.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {

    public static void run() {
        boolean flag = true;

        while(flag) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Read User command
            try {
                String command = reader.readLine();
            }
            catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}
