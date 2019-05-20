/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class Resultados {

    private Double numCuestionario;
    private String sexo;
    private int edad;
    private Double t1;
    private Double t2;
    private Double t3;
    private Double t4;
    private Double t5;
    private Double t6;

    public Resultados(Double numCuestionario, String sexo, int edad, Double t1, Double t2, Double t3, Double t4, Double t5, Double t6) {
        this.numCuestionario = numCuestionario;
        this.sexo = sexo;
        this.edad = edad;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
    }

    public Resultados(DBObject dbo) {
        this.numCuestionario = (Double) dbo.get("numCuestionario");
        this.sexo = (String) dbo.get("sexo");
        this.edad = (int) dbo.get("edad");
        this.t1 = (Double) dbo.get("t1");
        this.t2 = (Double) dbo.get("t2");
        this.t3 = (Double) dbo.get("t3");
        this.t4 = (Double) dbo.get("t4");
        this.t5 = (Double) dbo.get("t5");
        this.t6 = (Double) dbo.get("t6");
    }

    public Double getNumCuestionario() {
        return numCuestionario;
    }

    public void setNumCuestionario(Double numCuestionario) {
        this.numCuestionario = numCuestionario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Double getT1() {
        return t1;
    }

    public void setT1(Double t1) {
        this.t1 = t1;
    }

    public Double getT2() {
        return t2;
    }

    public void setT2(Double t2) {
        this.t2 = t2;
    }

    public Double getT3() {
        return t3;
    }

    public void setT3(Double t3) {
        this.t3 = t3;
    }

    public Double getT4() {
        return t4;
    }

    public void setT4(Double t4) {
        this.t4 = t4;
    }

    public Double getT5() {
        return t5;
    }

    public void setT5(Double t5) {
        this.t5 = t5;
    }

    public Double getT6() {
        return t6;
    }

    public void setT6(Double t6) {
        this.t6 = t6;
    }

}
