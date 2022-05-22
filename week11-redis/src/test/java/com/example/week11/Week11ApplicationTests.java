package com.example.week11;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;

@SpringBootTest
class Week11ApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Autowired
//    private RedisTemplate<String, String> template;


    @Test
    void testHelloWorld() {
        System.out.println("test good");
//        System.out.println(template);
    }
}
