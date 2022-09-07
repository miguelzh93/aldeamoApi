package com.aldeamo.service;

import com.aldeamo.entity.Arrays;
import com.aldeamo.exception.NegocioException;
import com.aldeamo.repository.ArraysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Arraysmpl implements ArraysService {
    @Autowired
    private ArraysRepository arrayRepository;

    /**
     * Pobla la bd h2 en base a la info dada
     */
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

    /**
     * Permite guardar datos en la tabla Arrays
     * @param id Id a guardar
     * @param imputArray Cadena a guardar
     */
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

    /**
     * Busca un registro por id de la tabla Arrays
     * @param id Id a buscar
     * @return Entidad de la tabla Arrays
     */
    @Override
    public Arrays getArraysById(long id) {
        Optional<Arrays> data = this.arrayRepository.findById(id);
        return data.isPresent() ? data.get() : new Arrays();
    }

    @Override
    public List<Arrays> getAllArrays() {
        return this.arrayRepository.findAll();
    }

    /**
     * Verifica los vasos de un var
     * @param numIteraciones Iteraciones a verificar
     * @param idPila Numero de pila a verificar
     * @return Array de vasos
     */
    @Override
    public ArrayList<Integer> getVasos(int numIteraciones, int idPila) throws NegocioException{
        poblar();
        Arrays dataPila = getArraysById(idPila);
        if (dataPila.getInput_array() == null) {
            throw new NegocioException("No existe el número de pila: " + idPila);
        }
        ArrayList<Integer> a = poblarArray(dataPila.getInput_array());
        ArrayList<Integer> p = poblarArray("2,3,5,7,11,13,17");

        if (p.size() < numIteraciones) {
            throw new NegocioException("El número de iteraciones es mayor al permitido");
        }
        return iterarVasos(numIteraciones, a, p);
    }

    /**
     * Pobla un ArrayList en base a una cadena separada por comas
     * @param cadena Cadena String
     * @return ArrayList poblado
     */
    private ArrayList<Integer> poblarArray(String cadena) {
        ArrayList<Integer>  rta = new ArrayList<Integer>();
        String[] cadenaSplit = cadena.split(",");
        for (int i = 0; i < cadenaSplit.length ; i++) {
            rta.add(Integer.parseInt(cadenaSplit[i]));
        }
        return rta;
    }

    /**
     * Verifica si un n&uacute;mero es divisible
     * @param dividendo
     * @param divisor
     * @return True si es divisible
     */
    private boolean numDivisible(int dividendo, int divisor) {
        return (dividendo % divisor == 0);
    }

    /**
     * Itera los vasos y devuelve los correctos
     * @param numIteraciones Numero de iteraciones a realizar
     * @return Array con los vasos correctos
     */
    private ArrayList<Integer> iterarVasos(int numIteraciones, ArrayList<Integer> a, ArrayList<Integer> p) {
        ArrayList<Integer> respuesta = new ArrayList<>();
        for (int i = 0; i < numIteraciones ; i++) {
            int numDivisor = p.get(i);
            ArrayList<Integer> a1 = new ArrayList<>();  //Num  no divisibles
            ArrayList<Integer> b1 = new ArrayList<>(); // Num divisibles
            for (int j = 0; j < a.size() ; j++) {
                int numero = Integer.valueOf(a.get(j));
                if (numDivisible(numero, numDivisor)){
                    b1.add(numero);
                }else {
                    a1.add(numero);
                }
            }
            respuesta.addAll(b1);
            if ((i + 1) == numIteraciones) {
                respuesta.addAll(a1);
            }
            a = a1;
        }
        return respuesta;
    }
}
