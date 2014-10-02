package com.sti.tools.md5gen;


import java.io.*;

public class ToFile {
    static boolean write(File outFile, String md5) {
        Writer out = null;
        try {
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            out.write(md5);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ignore) {
            }
        }
        return false;
    }
}
