package com.nix.springbootpractice.demo.repositories;

//import the model
import com.nix.springbootpractice.demo.model.Subject;

//import the spring data jpa repository
import org.springframework.stereotype.Repository;

//import for the db
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//jpa automatically creates bean for interfaces that extend JpaRepository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
   
    //find subjects where name contains given text, ignore capitalization
    List<Subject> findByNameContainingIgnoreCase(String name);
}
