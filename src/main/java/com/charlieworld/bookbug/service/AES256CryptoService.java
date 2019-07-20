package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.exception.CustomException;
import lombok.Getter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

@Getter
public class AES256CryptoService {

    private String KEYSPEC_METHOD = "AES";
    private int KEY_LENGTH = 16;
    private String METHOD = "AES/CBC/PKCS5Padding";
    private String CHARSET = "UTF-8";

    private String key;
    private String iv = "0000000000000000";
    private SecretKeySpec keySpec;

    public AES256CryptoService() {
        this.keySpec = new SecretKeySpec(new byte[KEY_LENGTH], "AES");
    }

    public AES256CryptoService(String key) {
        this.key = key;
        this.keySpec = new SecretKeySpec(new byte[KEY_LENGTH], "AES");
    }

    private String generateKey() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
    }

    public String encryptB64(String row) throws CustomException {
        String b64Encrypted = null;
        try {
            Cipher c = Cipher.getInstance(this.METHOD);
            c.init(Cipher.ENCRYPT_MODE, this.keySpec, new IvParameterSpec(this.iv.getBytes()));
            byte[] encrypted = c.doFinal(row.getBytes(CHARSET));
            b64Encrypted = new String(Base64.encodeBase64(encrypted));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "암호화 도중 실패 하였습니다. 에러 전문: " + e.getMessage());
        }
        return b64Encrypted;
    }

    public String decryptB64(String cipher) throws CustomException {
        String b64Decrypted = null;
        try {
            Cipher c = Cipher.getInstance(this.METHOD);
            c.init(Cipher.DECRYPT_MODE, this.keySpec, new IvParameterSpec(this.iv.getBytes(CHARSET)));
            byte[] byteStr = Base64.decodeBase64(cipher.getBytes());
            b64Decrypted = new String(c.doFinal(byteStr), CHARSET);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "복호화 도중 실패 하였습니다. 에러 전문: " + e.getMessage());
        }
        return b64Decrypted;
    }
}
