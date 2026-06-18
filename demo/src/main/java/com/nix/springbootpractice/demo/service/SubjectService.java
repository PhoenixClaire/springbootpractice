package com.nix.springbootpractice.demo.service;

import com.nix.springbootpractice.demo.dto.SubjectRequest;
import com.nix.springbootpractice.demo.exception.SubjectNotFoundException;
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
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
            .orElseThrow(() -> new SubjectNotFoundException(id));
    }

    public Subject createSubject(SubjectRequest request){
        //create a new subject with the name 
        //db will generate the id
        Subject newSubject = new Subject(request.getName());
        return subjectRepository.save(newSubject);
    }

    //update subject by id and return the updated subject from db
    public Subject updateSubject(Long id, SubjectRequest request){
        Subject subject =  subjectRepository.findById(id)
            .orElseThrow(() -> new SubjectNotFoundException(id)); //subject not found error

        subject.setName(request.getName());

        return subjectRepository.save(subject);
    }

    //display subject not found if successful
    public void deleteSubject(Long id){
        if(!subjectRepository.existsById(id)){
           throw new SubjectNotFoundException(id);
        }

        subjectRepository.deleteById(id);
    }

}
