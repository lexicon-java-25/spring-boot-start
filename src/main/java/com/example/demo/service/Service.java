package com.example.demo.service;


import com.example.demo.model.Greeting;

@org.springframework.stereotype.Service
public class Service {


    public Greeting greeting(){
        return new Greeting("Banarne");
    }
}


// controller <-> service <-> repository <-> database

//configuration, entities, advice