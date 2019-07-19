package com.charlieworld.bookbug.service;

import com.charlieworld.bookbug.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepository userRepository;


}
