package com.sti.tools.md5gen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Md5FromFile {

    static String generate(File file) {

        if (!file.isFile()) {
            throw new RuntimeException(file.getName() + " is not a file.");
        }

        String generate = null;

        try {
            FileInputStream in = new FileInputStream(file);

            int length = (int) file.length();
            byte[] data = new byte[length];
            if (in.read(data) != -1) {
                generate = Md5Generator.generate(data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generate;

    }

}
