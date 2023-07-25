package com.example.demo.service;

import com.example.demo.service.inteface.ExternalService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExternalMockService implements ExternalService {
    Random rand = new Random();
        public Double getTax(){
            return rand.nextDouble(); //TODO MOCKEAR BIEN PARA QUE DEVUELVA ENTITY RESPONSE Y DE VEZ EN CUANDO FALLE
        }

}
