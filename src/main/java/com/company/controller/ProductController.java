package com.company.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/public")
    public String sayHello() {
        return "<h1>Hello</h1>";
    }
    @GetMapping("/get")
    public String say() {
        return "<h1>say</h1>";
    }

}
