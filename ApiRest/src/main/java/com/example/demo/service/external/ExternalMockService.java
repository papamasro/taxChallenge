package com.example.demo.service.external;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Random;

@Service
public class ExternalMockService {
    Random rand = new Random();

    public Double getTax(){
            return  rand.nextDouble();
        }

}
