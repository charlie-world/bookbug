package com.charlieworld.bookbug.controller;

import com.charlieworld.bookbug.dto.BaseResponse;
import com.charlieworld.bookbug.dto.PopularKeyword;
import com.charlieworld.bookbug.service.PopularService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PopularController {

    @Autowired
    private PopularService popularService;

    @GetMapping("/v1/populars")
    public BaseResponse getTop10Keyword() {
        List<PopularKeyword> popularKeywords = popularService.getPopularkeyword();
        return new BaseResponse<>(popularKeywords);
    }
}
