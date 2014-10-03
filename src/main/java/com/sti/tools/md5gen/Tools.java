package com.sti.tools.md5gen;


import java.io.*;
import java.util.Properties;

public class Tools {

    static String generateMd5FromFile(File file) {

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

    public static String insertMd5ToFilename(String name, String md5) {
        String newName;
        int nameLen = name.length();

        // new filename start with original filename
        int dotPosition = name.lastIndexOf('.');
        if (dotPosition < 0) {
            newName = name + ".";
        } else {
            newName = name.substring(0, dotPosition + 1);
        }

        // insert first 7 characters of md5 hash
        int l = md5.length();
        l = l > 7 ? 7 : l;
        newName += md5.substring(0, l);

        // append file extension
        if (dotPosition >= 0 && dotPosition != nameLen - 1) { // dot is not last character
            newName += name.substring(dotPosition);
        }

        return newName;
    }

    public static void setPropertyToFile(File propFile, String key, String value) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            Properties properties = new Properties();
            if (propFile.exists()) {
                reader = new BufferedReader(new FileReader(propFile));
                properties.load(reader);
            } else {
                propFile.createNewFile();
            }

            properties.setProperty(key, value);

            writer = new BufferedWriter(new FileWriter(propFile));
            properties.store(writer, "File mapping");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (IOException ignore) {
            }
        }

    }

}
