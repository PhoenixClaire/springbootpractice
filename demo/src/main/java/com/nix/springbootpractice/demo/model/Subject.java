package com.nix.springbootpractice.demo.model;

public class Subject {
    
    //a subject has an id and a name
    private Long id;
    private String name;

    //constructor
    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //getters for id and name to allow spring to access these properties 
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
