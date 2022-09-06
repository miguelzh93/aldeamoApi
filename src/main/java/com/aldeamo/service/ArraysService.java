package com.aldeamo.service;

import com.aldeamo.entity.Arrays;

import java.util.List;

public interface ArraysService {
    void poblar();

    Arrays getArraysById(long id);

    List<Arrays> getAllArrays();
}
