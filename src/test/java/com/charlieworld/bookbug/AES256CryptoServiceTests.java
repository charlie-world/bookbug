package com.charlieworld.bookbug;

import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.service.AES256CryptoService;
import org.junit.Test;

public class AES256CryptoServiceTests {

    @Test
    public void cryptoTest() {
        String plainText = "TOY STORY 4";
        AES256CryptoService aes256CryptoService = new AES256CryptoService();

        try {
            String cipher = aes256CryptoService.encryptB64(plainText);
            String plain = aes256CryptoService.decryptB64(cipher);
            assert plainText.equals(plain);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
