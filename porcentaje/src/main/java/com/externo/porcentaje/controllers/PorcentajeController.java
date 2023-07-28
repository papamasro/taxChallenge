package com.externo.porcentaje.controllers;

import com.externo.porcentaje.dtos.PorcentajeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PorcentajeController {


    @RequestMapping(value = "/current-percentage", method = RequestMethod.GET)
    public ResponseEntity<?> obtenerPorcentaje() {
        Random random = new Random();
        PorcentajeDTO porcentajeDTO = PorcentajeDTO.builder()
                .porcentaje(0.0 + (100.0 - 0.0) * random.nextDouble())
                .build();
        return new ResponseEntity<>(porcentajeDTO, HttpStatus.OK);
    }
}
