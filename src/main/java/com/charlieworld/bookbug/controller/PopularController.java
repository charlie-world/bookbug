package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.dto.BaseResponse;
import com.charlieworld.bookbug.dto.Meta;
import com.charlieworld.bookbug.dto.PopularKeyword;
import com.charlieworld.bookbug.exception.CustomException;
import com.charlieworld.bookbug.service.PopularService;
import com.charlieworld.bookbug.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PopularController {

    @Autowired
    private PopularService popularService;

    @Autowired
    private UserService userService;

    @GetMapping("/v1/populars-keyword")
    public BaseResponse getTop10Keyword(@RequestHeader(value = UserService.AUTH_KEY) String token) {
        BaseResponse<List<PopularKeyword>> response;
        try {
            userService.authenticate(token);
            List<PopularKeyword> popularKeywords = popularService.findPopularkeyword();
            response = new BaseResponse<>(popularKeywords);
        } catch (CustomException e) {
            Meta meta = new Meta(e.getStatusCode(), e.getMessage());
            response = new BaseResponse<>(meta);
        }
        return response;
    }
}
