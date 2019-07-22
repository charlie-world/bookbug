package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.dto.BaseResponse;
import com.charlieworld.bookbug.dto.Meta;
import com.charlieworld.bookbug.dto.Token;
import com.charlieworld.bookbug.dto.UserHistory;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.service.HistoryService;
import com.charlieworld.bookbug.service.UserService;
import com.charlieworld.bookbug.vo.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryService historyService;

    @PostMapping("/v1/users/join")
    public BaseResponse join(@RequestBody UserAuth userAuth) {
        BaseResponse<Token> response;
        try {
            userService.join(userAuth.getId(), userAuth.getPassword());
            Meta meta = new Meta(HttpStatus.CREATED, "회원가입 성공");
            response = new BaseResponse<>(meta);
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<>(meta);
        }
        return response;
    }

    @PostMapping("/v1/users/login")
    public BaseResponse login(@RequestBody UserAuth userAuth) {
        Token token;
        BaseResponse<Token> response;
        try {
            token = userService.login(userAuth.getId(), userAuth.getPassword());
            response = new BaseResponse<>(token);
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<>(meta);
        }
        return response;
    }

    @GetMapping("/v1/users/history")
    public BaseResponse getHistory(@RequestHeader(value = UserService.AUTH_KEY) String token) {
        BaseResponse<List<UserHistory>> response;
        try {
            Long userId = userService.authenticate(token);
            response = new BaseResponse<>(historyService.findByUserId(userId));
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<>(meta);
        }
        return response;
    }
}
