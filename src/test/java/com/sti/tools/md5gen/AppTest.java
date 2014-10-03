package com.sti.tools.md5gen;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void _testApp() {
        String name = "/min.js";

        URL resource = AppTest.class.getResource(name);
        assertNotNull("Resource " + name + " not found", resource);
        File file = new File(resource.getPath());
        assertNotNull("File " + name + " not found", file);

        String md5 = Tools.generateMd5FromFile(file);
        assertEquals("8c4d5f072e57cf2eb384dee9b403ac5f", md5);
    }

    public void testPropExist() {
        URL src = AppTest.class.getResource("/min.js");
        URL src1 = AppTest.class.getResource("/same-content-min.js");
        URL src2 = AppTest.class.getResource("/file");
        URL prop = AppTest.class.getResource("/var.properties");

        Main.execute(new String[]{src.getPath(), prop.getPath()});
        Main.execute(new String[]{src1.getPath(), prop.getPath()});
        Main.execute(new String[]{src2.getPath(), prop.getPath()});

        BufferedReader reader = null;
        try {
            Properties properties = new Properties();
            reader = new BufferedReader(new FileReader(new File(prop.getPath())));
            properties.load(reader);

            assertEquals("file.1234567.txt", properties.getProperty("file1.txt"));
            assertEquals("file.1234567.txt", properties.getProperty("file2.txt"));
            assertEquals("file.1234567.txt", properties.getProperty("file3.txt"));
            assertEquals("file.1234567.txt", properties.getProperty("file4.txt"));

            assertEquals("file.3348b56", properties.getProperty("file"));
            assertEquals("min.8c4d5f0.js", properties.getProperty("min.js"));
            assertEquals("same-content-min.8c4d5f0.js", properties.getProperty("same-content-min.js"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignore) {
            }
        }

    }

    public void testPropNotExist() {
        URL src = AppTest.class.getResource("/min.js");
        String prop = AppTest.class.getResource("/").getPath() + "var_.properties";

        File propFile = new File(prop);
        if (propFile.exists()) {
            propFile.delete();
        }

        Main.execute(new String[]{src.getPath(), prop});

        BufferedReader reader = null;
        try {
            Properties properties = new Properties();
            reader = new BufferedReader(new FileReader(propFile));
            properties.load(reader);

            assertEquals("min.8c4d5f0.js", properties.getProperty("min.js"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignore) {
            }
        }

    }
}
