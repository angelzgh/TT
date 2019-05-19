/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axel Reyes
 */
public class RecomendacionTabla {
    
    private StringProperty recomendacion;
    
    public StringProperty getRecomendacion() {
        return recomendacion;
    }
    
    public void setRecomendacion(StringProperty recomendacion) {
        this.recomendacion = recomendacion;
    }
        public void setRecomendacion(Recomendacion r) {
        this.recomendacion = new SimpleStringProperty(r.getRecomendacion());
        
    }
    
    public RecomendacionTabla(String recomendacion) {
        this.recomendacion = new SimpleStringProperty(recomendacion);
    }
        public RecomendacionTabla(Recomendacion recomendacion) {
        this.recomendacion = new SimpleStringProperty(recomendacion.getRecomendacion());
    }
    
}
