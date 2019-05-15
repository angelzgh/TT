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
public class Pregunta {

    private int Id;
    private String Texto;
    private String Ayuda;

    public Pregunta(int Id, String Texto, String Ayuda) {
        this.Id = Id;
        this.Texto = Texto;
        this.Ayuda = Ayuda;
    }
        public Pregunta(Double Id, String Texto, String Ayuda) {
        this.Id =Id.intValue();
        this.Texto = Texto;
        this.Ayuda = Ayuda;
    }
    

    
    
    public Pregunta() {
        Texto = "";
        Id = 0;
    }

    public Pregunta(DBObject dbo, int tipo) {
        Double x= (Double) dbo.get("_idPregunta");
        Id = x.intValue();
        if (tipo == 1) {
            Texto = (String) dbo.get("texto");
        } else {

            Texto = (String) dbo.get("texto_2");
        }
        Ayuda=(String) dbo.get("ayuda");
    }

    public String getAyuda() {
        return Ayuda;
    }

    public void setAyuda(String Ayuda) {
        this.Ayuda = Ayuda;
    }

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }
}
