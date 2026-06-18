package com.nix.springbootpractice.demo.controllers;

import com.nix.springbootpractice.demo.dto.SubjectRequest;
import com.nix.springbootpractice.demo.model.Subject;
import com.nix.springbootpractice.demo.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    //inject the subject service into the controller
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //get all subjects
    @GetMapping("/api/subjects")

    //returns the list of subjects from the service
    public List<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }

    //find subject by id
    @GetMapping("/api/subjects/{id}")

    //returns the subject if found
    //no need to return the http response since the exception handlers already handle errors
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id); 
    }


    //create a new subject
    @PostMapping("/api/subjects")

    //return the created subject with 201 created status
    public Subject createSubject(@Valid @RequestBody SubjectRequest request) {
        return subjectService.createSubject(request);
    }

    //update subject by id
    @PutMapping("/api/subjects/{id}")

    //return the update subject if found, otherwise, 404
    public ResponseEntity<Subject> updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody SubjectRequest request
    ) {
        return subjectService.updateSubject(id, request)
                .map(subject -> ResponseEntity.ok(subject))
                .orElse(ResponseEntity.notFound().build());
    }

    //delete subject by id
    @DeleteMapping("/api/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        boolean deleted = subjectService.deleteSubject(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}