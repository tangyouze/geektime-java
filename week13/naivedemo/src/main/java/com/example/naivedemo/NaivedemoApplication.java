package com.example.naivedemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.midi.Soundbank;

@SpringBootApplication
public class NaivedemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println("init");
        SpringApplication.run(NaivedemoApplication.class, args);
        System.out.println("run end");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run");

    }
}
