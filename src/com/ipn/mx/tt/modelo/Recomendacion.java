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
public class Recomendacion {

private String recomendacion;

    public Recomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
    public Recomendacion(DBObject dbo) {
        this.recomendacion = (String) dbo.get("texto");
    }


    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

}
