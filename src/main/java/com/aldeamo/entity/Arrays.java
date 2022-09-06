package com.aldeamo.entity;

import javax.persistence.*;

@Entity
@Table(name = "arrays")
public class Arrays {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String input_array;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInput_array() {
        return input_array;
    }

    public void setInput_array(String input_array) {
        this.input_array = input_array;
    }

    /*public Arrays(int id, String input_array) {
        this.id = id;
        this.input_array = input_array;
    }*/



}
