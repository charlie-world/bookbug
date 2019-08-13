package com.charlieworld.bookbug.service;

import org.springframework.web.client.RestTemplate;

public class HttpService {

    public HttpService() {}

    public static class RestTemplateLazyHolder {
        private static final RestTemplate restTemplate = new RestTemplate();
    }

    public static RestTemplate getInstance() {
        return RestTemplateLazyHolder.restTemplate;
    }
}
