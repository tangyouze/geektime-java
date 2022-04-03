package com.example.sprintboot01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Entry {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/")
    public String index() {
        stringRedisTemplate.opsForValue().increment("key");
        String value = stringRedisTemplate.opsForValue().get("key");
        return value;
    }

}
