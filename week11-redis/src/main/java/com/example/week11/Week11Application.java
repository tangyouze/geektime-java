package com.example.week11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class Week11Application implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(Week11Application.class, args);
    }

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");
        template.opsForValue().set("foo", "bar");
        String foo = template.opsForValue().get("foo");
        System.out.println(foo);
    }

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }
}
