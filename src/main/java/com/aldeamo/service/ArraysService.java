package com.aldeamo.service;

import com.aldeamo.entity.Arrays;

import java.util.ArrayList;
import java.util.List;

public interface ArraysService {
    void poblar();

    Arrays getArraysById(long id);

    List<Arrays> getAllArrays();

    ArrayList<Integer> getVasos(int numIteraciones, int idPila);
}
