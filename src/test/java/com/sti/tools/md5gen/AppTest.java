package com.sti.tools.md5gen;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.net.URL;

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
    public void testApp() {
        assertTrue(true);
        String name = "/min.js";

        URL resource = AppTest.class.getResource(name);
        assertNotNull("Resource " + name + " not found", resource);
        File file = new File(resource.getPath());
        assertNotNull("File " + name + " not found", file);

        String md5 = Md5FromFile.generate(file);
        assertEquals("8c4d5f072e57cf2eb384dee9b403ac5f", md5);
    }
}
