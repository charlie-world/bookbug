package com.charlieworld.bookbug.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    private static int TOKEN_LENGTH = 32;

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getEncoder();
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        random.nextBytes(tokenBytes);
        return base64Encoder.encodeToString(tokenBytes);
    }
}
