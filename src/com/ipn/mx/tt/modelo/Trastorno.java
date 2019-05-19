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
public class Trastorno {

    private int preguntas;
    private Double puntaje;

    public Trastorno() {
        puntaje = 0.0;
        preguntas = 0;
    }
    public Trastorno (Trastorno t)
    {
                puntaje = t.puntaje;
        preguntas =  t.preguntas;
    }

    public int getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(int preguntas) {
        this.preguntas = preguntas;
    }

    public void aumentarPregunta() {
        preguntas++;
    }

    public void sumar(Double i) {
        puntaje += i;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    void restar(int puntaje) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.puntaje += puntaje;
    }

}
