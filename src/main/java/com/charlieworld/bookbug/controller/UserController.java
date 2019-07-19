package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.vo.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

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
