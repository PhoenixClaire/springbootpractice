package com.nix.springbootpractice.demo.service;

import com.nix.springbootpractice.demo.dto.SubjectRequest;
import com.nix.springbootpractice.demo.model.Subject;
import com.nix.springbootpractice.demo.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//handles the business logic of the applcation

//lets Spring know that this is a service class
@Service
public class SubjectService {
    
    //inject the subject repository into the service
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    //returns the list of subjects from db
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    //find subject by id and return from db
    //Optional --> can return empty if subject is not found 
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subject createSubject(SubjectRequest request){

        //generate a new id
        Long newId = subjectRepository.getNextId();

        //create a new subject into the db
        Subject newSubject = new Subject(newId, request.getName());
        return subjectRepository.save(newSubject);
    }

    //update subject by id and return the updated subject from db
    //Optional --> can return empty if updating a subject that does not exist
    public Optional<Subject> updateSubject(Long id, SubjectRequest request){
        return subjectRepository.findById(id)
                .map(subject -> {
                    subject.setName(request.getName());
                    return subject;
                });
    }

    //return true if successfully deleted from db
    public boolean deleteSubject(Long id){
        return subjectRepository.deleteById(id);
    }

}
