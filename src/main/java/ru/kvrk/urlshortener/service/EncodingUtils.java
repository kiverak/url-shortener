package ru.kvrk.urlshortener.service;

import java.security.SecureRandom;

public class EncodingUtils {
    private static final short LENGTH_LIMIT = 7;
    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateShortKey() {
        return generate(LENGTH_LIMIT);
    }

    private static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(idx));
        }
        return sb.toString();
    }

}
