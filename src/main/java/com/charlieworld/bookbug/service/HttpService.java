package com.charlieworld.bookbug.service;

import org.springframework.web.client.RestTemplate;

public interface HttpService {
    RestTemplate restTemplate = new RestTemplate();
}
