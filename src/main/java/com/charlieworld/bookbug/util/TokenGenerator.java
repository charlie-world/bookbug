package com.charlieworld.bookbug.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getEncoder();
        byte[] tokenBytes = new byte[16];
        random.nextBytes(tokenBytes);
        return base64Encoder.encodeToString(tokenBytes);
    }
}
