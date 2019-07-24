package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.entity.User;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.repository.UserRepository;
import com.charlieworld.bookbug.repository.UserTokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserTokenRepository userTokenRepository;

    @Autowired
    private UserService userService;

    @Test
    public void authenticateTest() {
        String token = "token";
        Long userId = 1L;

        given(userTokenRepository.get(token))
                .willReturn(Optional.of(userId));

        try {
            Long result = userService.authenticate(token);
            assertEquals(result, userId);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void joinTest() {
        String id1 = "id1";
        String id2 = "id2";
        String encKey = "enckey";
        String encPassword = "password";
        User user = User.builder().encryptKey(encKey).encryptedPassword(encPassword).realId(id1).build();
        given(userRepository.findByRealId(id1))
                .willReturn(Optional.of(user));
        boolean result = true;
        try {
            userService.join(id1, encPassword);
        } catch (CustomException e) {
            result = false;
        }
        assertFalse(result);

        User user2 = User.builder().encryptKey(encKey).encryptedPassword(encPassword).realId(id2).build();
        given(userRepository.findByRealId(id2))
                .willReturn(Optional.empty());
        given(userRepository.save(user2))
                .willReturn(user2);

        result = true;
        try {
            userService.join(id2, encPassword);
        } catch (CustomException e) {
            result = false;
        }
        assertTrue(result);
    }
}
