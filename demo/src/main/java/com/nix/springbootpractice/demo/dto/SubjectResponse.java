package com.nix.springbootpractice.demo.dto;

//so we can return only the fields needed for the response
public class SubjectResponse {
    
    private Long id;
    private String name;

    public SubjectResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
