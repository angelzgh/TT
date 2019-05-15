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
public class SintomaPregunta {

private int Sintoma;
private int Pregunta;

    public SintomaPregunta(int Sintoma, int Pregunta) {
        this.Sintoma = Sintoma;
        this.Pregunta = Pregunta;
    }

    SintomaPregunta(DBObject dbObject) {
            this.Sintoma =((Double) dbObject.get("_idSintoma")).intValue();
        this.Pregunta = ((Double) dbObject.get("_idPregunta")).intValue();
    }



    public int getSintoma() {
        return Sintoma;
    }

    public void setSintoma(int Sintoma) {
        this.Sintoma = Sintoma;
    }

    public int getPregunta() {
        return Pregunta;
    }

    public void setPregunta(int Pregunta) {
        this.Pregunta = Pregunta;
    }

    @Override
    public String toString() {
        return "SintomaPregunta{" + "Sintoma=" + Sintoma + ", Pregunta=" + Pregunta + '}';
    }


}
