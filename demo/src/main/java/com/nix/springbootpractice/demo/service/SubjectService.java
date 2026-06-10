package com.nix.springbootpractice.demo.service;

import com.nix.springbootpractice.demo.dto.SubjectRequest;
import com.nix.springbootpractice.demo.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

//handles the business logic of the applcation

//lets Spring know that this is a service class
@Service
public class SubjectService {
    
    //list of subjects to simulate a database
    private final List<Subject> subjects = new ArrayList<>(
        List.of( 
            new Subject(1L, "Mathematics"),
            new Subject(2L, "Physics"),
            new Subject(3L, "Chemistry")
        )
    );

    //returns the list of subjects
    public List<Subject> getAllSubjects() {
        return subjects;
    }

    //find subject by id and return
    //Optional --> can return empty if subject is not found 
    public Optional<Subject> getSubjectById(Long id) {
        return subjects.stream()
                .filter(subject -> subject.getId().equals(id))
                .findFirst();
    }

    public Subject createSubject(SubjectRequest request){

        //generate a new id for the subject
        //size + 1
        Long newId = (long) subjects.size() + 1; 

        //create a new subject with new name and id
        Subject newSubject = new Subject(newId, request.getName());

        //add the new subject to the list
        subjects.add(newSubject);

        return newSubject;
    }

    //update subject by id
    //Optional --> can return empty if updating a subject that does not exist
    public Optional<Subject> updateSubject(Long id, SubjectRequest request){

        //loop through the list of subjects
        for(Subject subject : subjects){

            //if the subject id matches the id in the request
            if(subject.getId().equals(id)){

                //update the name
                subject.setName(request.getName());

                //return the updated subject
                return Optional.of(subject);
            }
        }
        //if no subject is found with the given id, return empty
        return Optional.empty();
    }

    //return true if successfully deleted 
    public boolean deleteSubject(Long id){

        //remove the subject with the given id from the list
        return subjects.removeIf(subject -> subject.getId().equals(id));
    }

}
