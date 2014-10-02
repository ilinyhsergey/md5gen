package com.sti.tools.md5gen;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class Md5Generator {

    static String generate(byte[] data) {
        String hashtext = null;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5"); // "SHA-1", "SHA-256"
            m.reset();
            m.update(data);
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashtext;
    }
}
