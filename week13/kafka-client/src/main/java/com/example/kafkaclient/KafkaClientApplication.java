package com.example.kafkaclient;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author tyz
 */
@SpringBootApplication
public class KafkaClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("init");

    }

//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("topic1")
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }
//
//    @KafkaListener(id = "myId", topics = "topic1")
//    public void listen(String in) {
//        System.out.println(in);
//    }
}
