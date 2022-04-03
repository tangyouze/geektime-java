package com.example.sprintboot01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Entry {
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

}
