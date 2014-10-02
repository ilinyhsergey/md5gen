package com.sti.tools.md5gen;


import java.io.File;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 1 || args[0] == null || args[0].trim().isEmpty()) {
            return;
        }

        String srcPath = args[0];
        String dstPath = srcPath + ".MD5";

        String md5 = Md5FromFile.generate(new File(srcPath));
        ToFile.write(new File(dstPath), md5);
        System.out.println(md5);
    }
}
