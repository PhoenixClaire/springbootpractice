package com.nix.springbootpractice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import model
import com.nix.springbootpractice.model.Subject;

import java.util.List;

@RestController
public class SubjectController {
    
    //added /api to the path to follow RESTful API conventions
    @GetMapping("/api/subjects")

    // this way, the method will return a list of subject objects as JSON
    public List<Subject> getSubjects(){
        return List.of(
            
            //create new subject objects with id and name
            new Subject(1l, "Math"),
            new Subject(2l, "Science"),
            new Subject(3L, "English")
        );
    }
}
