package com.aldeamo.rest;

import com.aldeamo.entity.Arrays;
import com.aldeamo.exception.NegocioException;
import com.aldeamo.service.ArraysService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.aldeamo.pojo.Respuesta;

@RestController
public class ServicioBar {
    @Autowired
    private ArraysService arraysService;

    @CrossOrigin(origins = "*" )
    @GetMapping("/bar/{id}")
    public ResponseEntity getArrayByiD(@PathVariable int id) {
        Respuesta respuesta = new Respuesta();
        int status = 200;
        try {
            arraysService.poblar();
            Arrays data = arraysService.getArraysById(id);
            if (data.getInput_array() == null) {
                status = 400;
                respuesta.setError("No existen datos con el id :" + id);
            }
            respuesta.setCuerpo(data);
        } catch (NegocioException e) {
            respuesta.setError(e.getMessage());
            status = 400;
        }

        return new ResponseEntity(respuesta, HttpStatus.resolve(status));
    }

    @CrossOrigin(origins = "*" )
    @GetMapping("/bar/")
    public ResponseEntity getAllArrays() {
        Respuesta respuesta = new Respuesta();
        int status = 200;
        try {
            respuesta.setCuerpo(arraysService.getAllArrays());
        } catch (NegocioException e) {
            respuesta.setError(e.getMessage());
            status = 400;
        }
        return new ResponseEntity(respuesta, HttpStatus.resolve(status));
    }
}
