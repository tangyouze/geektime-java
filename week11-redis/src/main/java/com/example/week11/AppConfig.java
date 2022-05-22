package com.example.week11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
class AppConfig {


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
        return new LettuceConnectionFactory();
    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        // 这个地方不可使用 json 序列化，如果使用的是ObjectRecord传输对象时，可能会有问题，会出现一个 java.lang.IllegalArgumentException: Value must not be null! 错误
//        redisTemplate.setHashValueSerializer(RedisSerializer.string());
//        return redisTemplate;
//    }
}