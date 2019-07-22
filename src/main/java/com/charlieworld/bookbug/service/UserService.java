package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.Token;
import com.charlieworld.bookbug.entity.User;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.repository.UserRepository;
import com.charlieworld.bookbug.repository.UserTokenRepository;
import com.charlieworld.bookbug.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public static final String AUTH_KEY = "X-USER-AUTH";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    public void join(String id, String password) throws CustomException {
        Optional<User> userData = userRepository.findByRealId(id);
        if (userData.isPresent()) {
            throw new CustomException(HttpStatus.CONFLICT, "이미 가입된 회원 입니다.");
        }
        AES256CryptoService aes256CryptoService = new AES256CryptoService();
        String encryptedPassword = aes256CryptoService.encryptB64(password);
        User user = User
                .builder()
                .realId(id)
                .encryptedPassword(encryptedPassword)
                .encryptKey(aes256CryptoService.getKey())
                .build();
        userRepository.save(user);
    }

    public Token login(String id, String password) throws CustomException {
        Optional<User> user = userRepository.findByRealId(id);
        if (!user.isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "회원정보가 없습니다. 회원가입 해주시기 바랍니다.");
        }
        AES256CryptoService aes256CryptoService = new AES256CryptoService(user.get().getEncryptKey());

        if (!aes256CryptoService.encryptB64(password).equals(user.get().getEncryptedPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }
        String token = TokenGenerator.generateToken();
        userTokenRepository.save(user.get().getUserId(), token);
        return Token.builder().token(token).build();
    }

    public Long authenticate(String token) throws CustomException {
        Optional<Long> userIdOpt = userTokenRepository.get(token);
        if (!userIdOpt.isPresent()) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "사용자 인증에 실패 하였습니다.");
        } else {
            return userIdOpt.get();
        }
    }
}
