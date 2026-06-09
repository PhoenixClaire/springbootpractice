package com.nix.springbootpractice.demo.dto;

//import for validation
//you need to add the dependency for validation in your pom.xml file to use this
import jakarta.validation.constraints.NotBlank;

public class SubjectRequest {

    //this means the field cannot be blank or empty --> show error message
    @NotBlank(message = "Subject name is required")
    private String name;

    public String getName(){
        return name;
    }
}
