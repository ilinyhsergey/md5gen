package com.sti.tools.md5gen;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            Main.execute(args);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
