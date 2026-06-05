package com.nix.springbootpractice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {
    
    @GetMapping("/subjects")
    public String getSubjects(){
        return "Math, Science, English";
    }
}
