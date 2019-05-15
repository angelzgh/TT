/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

/**
 *
 * @author Axel Reyes
 */
public class Respuesta {

    int numeroPregunta;
    int respuesta;

    public Respuesta(int numeroPregunta, int respuesta) {
        this.numeroPregunta = numeroPregunta;
        this.respuesta = respuesta;
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "numeroPregunta=" + numeroPregunta + ", respuesta=" + respuesta + '}';
    }
    
    

}
