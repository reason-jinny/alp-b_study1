package com.kt.blog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController // get, post 등을 쓰겠다고 하는 것~
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }
}
