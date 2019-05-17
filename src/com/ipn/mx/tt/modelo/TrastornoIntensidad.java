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
public class TrastornoIntensidad {
    
    private String trastorno;
    private String intensidad;
    private Double minimo,maximo,media;

    public TrastornoIntensidad(String trastorno, String intensidad, Double minimo, Double maximo) {
        this.trastorno = trastorno;
        this.intensidad = intensidad;
        this.minimo = minimo;
        this.maximo = maximo;
        this.media =0.0;
    }

    public String getTrastorno() {
        return trastorno;
    }

    public void setTrastorno(String trastorno) {
        this.trastorno = trastorno;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }
    
    
}
