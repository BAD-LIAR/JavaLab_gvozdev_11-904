package ru.itis.javalab.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtils {
    public static String generatePassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder a = new StringBuilder();
            for (byte b : hash) {
                a.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return a.toString();
        } catch (NoSuchAlgorithmException e){
            throw new IllegalArgumentException(e);
        }
    }
}
