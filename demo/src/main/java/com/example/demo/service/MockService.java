package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MockService {
    Random rand = new Random();
        public Double getTax(){
            return rand.nextDouble(); //TODO MOCKEAR BIEN PARA QUE DEVUELVA ENTITY RESPONSE Y DE VEZ EN CUANDO FALLE
        }

}
