package com.aldeamo.rest;

import com.aldeamo.exception.NegocioException;
import com.aldeamo.service.ArraysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.aldeamo.pojo.Respuesta;

@RestController
public class ServicioBar {
    @Autowired
    private ArraysService arraysService;

    private Respuesta respuesta = new Respuesta();

    @CrossOrigin(origins = "*" )
    @GetMapping("/bar/{id}")
    public Respuesta poblar(@PathVariable int id) {
        try {
            arraysService.poblar();
            respuesta.setCuerpo(arraysService.getArraysById(id));
        } catch (NegocioException e) {
            respuesta.setError(e.getMessage());
        }
        return respuesta;
    }
}
