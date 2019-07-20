package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.dto.BaseResponse;
import com.charlieworld.bookbug.dto.Meta;
import com.charlieworld.bookbug.dto.Token;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.service.UserService;
import com.charlieworld.bookbug.vo.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired(required = true)
    private UserService userService;

    @PostMapping("/v1/users/join")
    public BaseResponse join(@RequestBody UserAuth userAuth) {
        Token token = null;
        BaseResponse<Token> response = null;
        try {
            token = userService.join(userAuth.getId(), userAuth.getPassword());
            response = new BaseResponse<Token>(token);
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<Token>(meta);
        }
        return response;
    }

//    @PostMapping("/v1/users/login")
//    public BaseResponse login(@RequestBody UserAuth userAuth) {
//        // login(userAuth);
//        return "some token";
//    }
}
