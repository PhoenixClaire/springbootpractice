package com.nix.springbootpractice.demo.exception;

public class SubjectNotFoundException extends RuntimeException {

    //let user know subject is not found
    public SubjectNotFoundException(Long id){
        super("Subject not found with id: " + id);
    }
    
}
