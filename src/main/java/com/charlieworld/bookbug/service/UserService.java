package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.dto.Token;
import com.charlieworld.bookbug.entity.User;
import com.charlieworld.bookbug.entity.UserToken;
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

    @Autowired(required = true)
    private UserRepository userRepository;

    @Autowired(required = true)
    private UserTokenRepository userTokenRepository;

    public Token join(String id, String password) throws CustomException {
        AES256CryptoService aes256CryptoService = new AES256CryptoService();
        String encryptedPassword = aes256CryptoService.encryptB64(password);
        User user = User
                .builder()
                .realId(id)
                .encryptedPassword(encryptedPassword)
                .encryptKey(aes256CryptoService.getKey())
                .build();
        user = userRepository.save(user);
        String token = TokenGenerator.generateToken();
        UserToken userToken = UserToken.builder().token(token).userId(user.getUserId()).build();
        userTokenRepository.save(userToken);
        return Token.builder().token(token).build();
    }

    public Token login(String id, String password) throws CustomException {
        Token result = null;
        Optional<User> user = userRepository.findByRealId(id);
        if (!user.isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "회원정보가 없습니다. 회원가입 해주시기 바랍니다.");
        }
        AES256CryptoService aes256CryptoService = new AES256CryptoService(user.get().getEncryptKey());

        if (!aes256CryptoService.encryptB64(password).equals(user.get().getEncryptedPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }

        Optional<UserToken> userToken = userTokenRepository.findByUserId(user.get().getUserId());

        if (!userToken.isPresent()) {
            String token = TokenGenerator.generateToken();
            UserToken newUserToken = UserToken.builder().token(token).userId(user.get().getUserId()).build();
            userTokenRepository.save(newUserToken);
            result = Token.builder().token(token).build();
        } else {
            result = Token.builder().token(userToken.get().getToken()).build();
        }

        return result;
    }
}
