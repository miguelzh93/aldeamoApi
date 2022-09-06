package com.aldeamo.service;

import com.aldeamo.entity.Arrays;
import com.aldeamo.exception.NegocioException;
import com.aldeamo.repository.ArraysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Arraysmpl implements ArraysService {
    @Autowired
    private ArraysRepository arrayRepository;

    @Override
    public void poblar() {
        if (getArraysById(1) != null) {
            saveData(1,"2,4,5,6,7,8");
            saveData(2,"3,7,9,5,4,2");
            saveData(3,"5,7,9,11,13");
            saveData(4,"6,4,2,12,15");
            saveData(5,"7,10,15,11,9");
        }
    }

    private void saveData(int id, String imputArray) {
        try {
            Arrays data = new Arrays();
            data.setId(id);
            data.setInput_array(imputArray);
            this.arrayRepository.save(data);
        } catch (Exception e) {
            throw new NegocioException("Se presentaron inconvenientes en poblar los datos");
        }
    }

    @Override
    public Arrays getArraysById(long id) {
        Optional<Arrays> data = this.arrayRepository.findById(id);
        return data.isPresent() ? data.get() : new Arrays();
    }

    @Override
    public List<Arrays> getAllArrays() {
        return this.arrayRepository.findAll();
    }
}
