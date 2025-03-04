package com.kt.blog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // get, post 등을 쓰겠다고 하는 것~
@RequestMapping("hello")
public class HelloController {

    @GetMapping("/main")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/api")
    public String api() {
        return "Hello, API!";
    }
}