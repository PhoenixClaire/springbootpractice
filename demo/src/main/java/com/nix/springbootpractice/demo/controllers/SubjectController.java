package com.nix.springbootpractice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {
    
    //added /api to the path to follow RESTful API conventions
    @GetMapping("/api/subjects")

    // this way, the method will return a list of subjects in JSON format
    public List<String> getSubjects(){
        return List.of("Mathematics", "Science", "English");
    }
}
