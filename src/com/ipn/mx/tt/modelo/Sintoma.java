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
public class Sintoma {

    private String idSintoma;
    private String nombre;

    public Sintoma(String idSintoma, String nombre) {
        this.idSintoma = idSintoma;
        this.nombre = nombre;
    }

    public Sintoma(DBObject dbo) {
        this.idSintoma = String.valueOf((Double) dbo.get("_idSintoma"));
        this.nombre = (String) dbo.get("nombre");
    }

    public Sintoma() {
        this.idSintoma = "";
        this.nombre = "";

    }

    public String getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(String idSintoma) {
        this.idSintoma = idSintoma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
