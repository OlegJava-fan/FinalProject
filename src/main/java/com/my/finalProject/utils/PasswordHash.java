package com.my.finalProject.utils;

import com.my.finalProject.builder.exception.BuildException;
import com.my.finalProject.db.exception.DBException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String hash(String input) throws BuildException {
        StringBuilder hex;
        final String algorithm = "MD5";
        final String HEXES = "0123456789ABCDEF";
        try {

            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(input.getBytes());
            byte[] hash = digest.digest();
            hex = new StringBuilder(2 * hash.length);

            for (final byte b : hash) {
                hex.append(HEXES.charAt((b & 0xF0) >> 4))
                        .append(HEXES.charAt((b & 0x0F)));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BuildException("can not be encrypt password", e);
        }
    }
}
