package com.nix.springbootpractice.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nix.springbootpractice.demo.model.Subject;

import jakarta.validation.Valid;

import java.util.List;
import java.util.ArrayList;

//import for response entity
import org.springframework.http.ResponseEntity;

//imports for POST endpoint
import com.nix.springbootpractice.demo.dto.SubjectRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import for PUT endpoint
import org.springframework.web.bind.annotation.PutMapping;

//import for delete endpoint
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class SubjectController {

    //changed List.of to ArrayList to make it modifiable so we can add more subjects 
    private final List<Subject> subjects = new ArrayList<>(
        List.of( 
            new Subject(1L, "Mathematics"),
            new Subject(2L, "Physics"),
            new Subject(3L, "Chemistry")
        )
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

    //this is for POST requests
    @PostMapping("/api/subjects")

    //@RequestBody means get the request body and convert it to Java object
    //@Valid means validate the request body based on SubjectRequest class
    public Subject createSubject(@Valid @RequestBody SubjectRequest request){

        //generate a new id for the subject
        //just +1 to the size of the list
        Long newid = (long) subjects.size() + 1;

        //create a new subject and add it to the list
        Subject newSubject = new Subject(newid, request.getName());
        subjects.add(newSubject);

        return newSubject;
    }

    //PUT for a specific subject by id
    @PutMapping("/api/subjects/{id}")

    //return the HTTP response with the updated subject
    public ResponseEntity<Subject> updateSubject(

        //find by id based on the path variable
        @PathVariable Long id,

        //ensure the name is not blank
        @Valid @RequestBody SubjectRequest request
    ){
        //check the subjects to find the one with the matching id
        for(Subject subject : subjects){

            //if the id matches, name will be updated
            if(subject.getId().equals(id)){

                //set the new name for the subject
                subject.setName(request.getName());

                //return status and updated name
                return ResponseEntity.ok(subject);
            }
        }

        //error message
        return ResponseEntity.notFound().build();
    }

    //delete the subject by id
    @DeleteMapping("/api/subjects/{id}")

    //will return void but still return HTTP response 
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id){

        //remove the subject with the matching id from the list
        boolean removed = subjects.removeIf(subject -> subject.getId().equals(id));

        if(removed){
            return ResponseEntity.noContent().build(); //204 No Content if deleted successfully
        }

        return ResponseEntity.notFound().build(); //404 Not Found if the subject is not found
    }


}
