package com.nix.springbootpractice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import model
import com.nix.springbootpractice.model.Subject;

import java.util.List;

//import for response entity
import org.springframework.http.ResponseEntity;

@RestController
public class SubjectController {

    private final List<Subject> subjects = List.of(
            new Subject(1L, "Mathematics"),
            new Subject(2L, "Physics"),
            new Subject(3L, "Chemistry")
    );

    //when you visit this endpoint, it will return the list of subjects
    @GetMapping("/api/subjects")
    public List<Subject> getSubjects(){
        return subjects;
    }

    //when you visit this endpoint, specifying the subject id, it will return the corresponding subject
    @GetMapping("/api/subjects/{id}")

    //@PathVariable means get the id from the URL path and use it as a parameter
    //return a ResponseEntity --> return a subject with the HTTP response
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id){

        //uses stream to filter subjects by id and return the first match
        return subjects.stream()
                .filter(subject -> subject.getId().equals(id))
                .findFirst()
                .map(subject -> ResponseEntity.ok(subject)) //ok response if the subject is found
                .orElse(ResponseEntity.notFound().build()); //error 404
    }
}
