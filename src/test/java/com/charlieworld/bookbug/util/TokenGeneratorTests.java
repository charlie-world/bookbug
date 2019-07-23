package com.charlieworld.bookbug.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TokenGeneratorTests {

    @Test
    public void generateToken() {
        String token = TokenGenerator.generateToken();

        assertNotNull(token);
        assertEquals(token.getClass(), String.class);
    }
}
