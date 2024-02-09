package com.example.urlshortener.common.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStringUtil {

    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789-";

    public static String generateRandomString(int length) {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}

