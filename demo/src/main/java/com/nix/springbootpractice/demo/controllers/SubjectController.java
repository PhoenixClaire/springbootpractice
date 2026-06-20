package com.nix.springbootpractice.demo.controllers;

import com.nix.springbootpractice.demo.dto.SubjectRequest;
import com.nix.springbootpractice.demo.model.Subject;
import com.nix.springbootpractice.demo.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import subject response
import com.nix.springbootpractice.demo.dto.SubjectResponse;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import for pagination
import org.springframework.data.domain.Page;

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
    public List<SubjectResponse> getSubjects() {
        return subjectService.getAllSubjects();
    }

    //paginated list of subjects
    @GetMapping("/api/subjects/paged")
    public Page<SubjectResponse> getSubjectsPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        return subjectService.getSubjectsPaginated(page, size, sortBy, direction);
    }
    

    //find subject by id
    @GetMapping("/api/subjects/{id}")

    //returns the subject if found
    //no need to return the http response since the exception handlers already handle errors
    public SubjectResponse getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id); 
    }


    //create a new subject
    @PostMapping("/api/subjects")

    //return the created subject with 201 created status
    public SubjectResponse createSubject(@Valid @RequestBody SubjectRequest request) {
        return subjectService.createSubject(request);
    }

    //update subject by id
    @PutMapping("/api/subjects/{id}")

    //return the update subject if found, otherwise, 404
    public SubjectResponse updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody SubjectRequest request
    ) {
        return subjectService.updateSubject(id, request);
    }

    //delete subject by id
    @DeleteMapping("/api/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/subjects/search")
    public List<SubjectResponse> seachSubjects (@RequestParam String name) {
        return subjectService.searchSubjectByName(name);
    }
    
}