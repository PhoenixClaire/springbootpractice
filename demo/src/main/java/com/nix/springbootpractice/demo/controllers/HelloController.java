package com.nix.springbootpractice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*this tells spring that this class 
handles web requets but whatever is sent 
should be returned as response body */
@RestController 

/*This is the controller class */
public class HelloController {

    /* When someone visits the endpoint /hello using GET, run this method */
    @GetMapping("/hello")

    /*when visiting the endpoint /hello, user will see this message */
    public String hello(){
        return "Hello, Spring Boot!";
    }

    /*when you visit /motivation endpoint, user will see this message */
    @GetMapping("/motivation")
    public String motivation(){
        return "One small step still counts!";
    }
}

