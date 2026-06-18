package com.nix.springbootpractice.demo.model;

//turn this class into an entity that can be stored in the database
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//this tells spring that this class is an entity --> database table
@Entity
public class Subject {

    @Id //this field is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db will generate the ID
    private Long id;

    private String name;

    //constructors needed by jpa
    public Subject(){

    }

    public Subject(String name){
        this.name = name;
    }

    public Subject(Long id, String name){
        this.id = id;
        this.name = name;
    }

    //getters and setters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name; 
    }
}
