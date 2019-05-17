/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axel Reyes
 */
public class TrastornoIntensidadTabla {
    
    private StringProperty trastorno;
    private StringProperty intensidad;
    private DoubleProperty minimo, maximo, media;
    
    public TrastornoIntensidadTabla(String trastorno, String intensidad, Double minimo, Double maximo) {
        this.trastorno = new SimpleStringProperty(trastorno);
        this.intensidad = new SimpleStringProperty(intensidad);
        this.minimo = new SimpleDoubleProperty(minimo);
        this.maximo = new SimpleDoubleProperty(maximo);
    }
        public TrastornoIntensidadTabla(TrastornoIntensidad ti) {
        this.trastorno = new SimpleStringProperty(ti.getTrastorno());
        this.intensidad = new SimpleStringProperty(ti.getIntensidad());
        this.minimo = new SimpleDoubleProperty(ti.getMinimo());
        this.maximo = new SimpleDoubleProperty(ti.getMaximo());
        this.media = new SimpleDoubleProperty(ti.getMedia());
    }
    public StringProperty getTrastorno() {
        return trastorno;
    }
    
    public void setTrastorno(String trastorno) {
        this.trastorno = new SimpleStringProperty(trastorno);
    }

    public StringProperty getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(StringProperty intensidad) {
        this.intensidad = intensidad;
    }
    

    
    public DoubleProperty getMinimo() {
        return minimo;
    }
    
    public void setMinimo(DoubleProperty minimo) {
        this.minimo = minimo;
    }
    
    public DoubleProperty getMaximo() {
        return maximo;
    }
    
    public void setMaximo(DoubleProperty maximo) {
        this.maximo = maximo;
    }
    
    public DoubleProperty getMedia() {
        return media;
    }
    
    public void setMedia(DoubleProperty media) {
        this.media = media;
    }
    
}
