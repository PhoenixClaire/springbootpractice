package com.nix.springbootpractice.demo.repositories;

//import the model
import com.nix.springbootpractice.demo.model.Subject;

//import the spring data jpa repository
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

//this tells spring that this is a repository class
@Repository
public class SubjectRepository {
    
    //this is the list of the subjects
    private final List<Subject> subjects = new ArrayList<>(
        List.of(
            new Subject(1L, "Mathematics"),
            new Subject(2L, "Physics"),
            new Subject(3L, "Chemistry")
        )
    );

    //return all the subjects in the list
    public List<Subject> findAll(){
        return subjects;
    }

    //return subject by id if found, otherwise return empty optional
    public Optional<Subject> findById(Long id){
        return subjects.stream()
                .filter(subject -> subject.getId().equals(id))
                .findFirst();
    }

    //add new subject
    public Subject save(Subject subject){
        subjects.add(subject);
        return subject;
    }

    //delete subject by id, return true if deleted, false if not found
    public boolean deleteById(Long id){
        return subjects.removeIf(subject -> subject.getId().equals(id));
    }

    //generate id
    public Long getNextId(){
        return (long) subjects.size() + 1;
    }
}
