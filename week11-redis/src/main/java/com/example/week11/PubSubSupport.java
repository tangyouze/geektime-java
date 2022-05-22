package com.example.week11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@SpringBootApplication
public class PubSubSupport implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(PubSubSupport.class, args);
    }

    @Autowired
    private RedisTemplate<String, String> template;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("pubsub support");
    }
}
