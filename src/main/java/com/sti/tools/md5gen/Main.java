package com.sti.tools.md5gen;

import java.io.File;

/**
 * Created by sergeyi on 03.10.2014.
 */
public class Main {

    /**
     * separate class for testing
     *
     * @param args = srcFileName propFileName
     */
    public static void execute(String[] args) {

        if (args.length < 2)
            return;

        for (String arg : args) {
            if (arg == null || arg.trim().isEmpty())
                return;
        }

        File srcFile = new File(args[0]);
        File propFile = new File(args[1]);

        // generate md5 from file
        String md5 = Tools.generateMd5FromFile(srcFile);
        System.out.println("md5( " + srcFile.getName() + " ) = " + md5);

        // make new filename
        String name = srcFile.getName();
        String newName = Tools.insertMd5ToFilename(name, md5);
        System.out.println(name + " => " + newName);

        // set file mapping in properties file
        Tools.setPropertyToFile(propFile, name, newName);
    }

}
