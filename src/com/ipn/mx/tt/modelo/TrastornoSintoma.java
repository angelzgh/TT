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
public class TrastornoSintoma {

    private int Trastorno;
    private int Sintoma;

    public TrastornoSintoma(int Trastorno, int Sintoma) {
        this.Trastorno = Trastorno;
        this.Sintoma = Sintoma;
    }

    TrastornoSintoma(DBObject dbObject) {

        this.Sintoma = ((Double) dbObject.get("_idSintoma")).intValue();
        this.Trastorno = ((Double) dbObject.get("_idTrastorno")).intValue();
    }

    public int getTrastorno() {
        return Trastorno;
    }

    public void setTrastorno(int Trastorno) {
        this.Trastorno = Trastorno;
    }

    public int getSintoma() {
        return Sintoma;
    }

    public void setSintoma(int Sintoma) {
        this.Sintoma = Sintoma;
    }

}
