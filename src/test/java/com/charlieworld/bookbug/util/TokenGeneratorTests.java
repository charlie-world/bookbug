package com.charlieworld.bookbug.util;

import org.junit.Test;

public class TokenGeneratorTests {

    @Test
    public void generateToken() {
        String token = TokenGenerator.generateToken();

        assert token != null;
        assert token.getClass() == String.class;
    }
}
