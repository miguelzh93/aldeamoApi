package com.aldeamo.service;

import com.aldeamo.entity.Arrays;
import com.aldeamo.exception.NegocioException;
import com.aldeamo.repository.ArraysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class Arraysmpl implements ArraysService {
    @Autowired
    private ArraysRepository arrayRepository;

    @Override
    public void poblar() {
        try {
            Arrays data = new Arrays();
            data.setId(1);
            data.setInput_array("2,4,5,6,7,8");
            arrayRepository.save(data);
            /*rrayRepository.save(new Arrays(2, "3,7,9,5,4,2"));
            arrayRepository.save(new Arrays(3, "5,7,9,11,13"));
            arrayRepository.save(new Arrays(4, "6,4,2,12,15"));
            arrayRepository.save(new Arrays(5, "7,10,15,11,9"));*/
        }catch (Exception e){
            throw new NegocioException("Se presentaron inconvenientes en poblar los datos");
        }
    }

    @Override
    public Arrays getArraysById(long id) {
        Optional<Arrays> data = this.arrayRepository.findById(id);
        if (data.isPresent()) {
            return data.get();
        }else {
            throw new NegocioException("Registro no encontrado con id: " + id);
        }

    }
}
