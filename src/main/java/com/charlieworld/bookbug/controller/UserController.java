package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.service.UserService;
import com.charlieworld.bookbug.vo.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired(required = true)
    private UserService userService;

    @PostMapping("/v1/users/join")
    public String join(@RequestBody UserAuth userAuth) {

        return userAuth.getId();
    }

    @PostMapping("/v1/users/login")
    public String login(@RequestBody UserAuth userAuth) {
        // login(userAuth);
        return "some token";
    }
}
