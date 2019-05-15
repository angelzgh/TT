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
public class PreguntaTabla {

    private StringProperty pregunta;
    private StringProperty respuesta;

    public PreguntaTabla(String pregunta, String respuesta) {
        this.pregunta = new SimpleStringProperty(pregunta);
        this.respuesta = new SimpleStringProperty(respuesta);
    }

    public PreguntaTabla(Respuesta r) {
        this.pregunta = new SimpleStringProperty(String.valueOf(r.getNumeroPregunta()));
        this.respuesta = new SimpleStringProperty(String.valueOf(r.getRespuesta()));
        System.out.println(r.toString());
    }

    public StringProperty getPregunta() {
        return pregunta;
    }

    public void setPregunta(StringProperty pregunta) {
        this.pregunta = pregunta;
    }

    public StringProperty getRespuesta() {
        if (respuesta.getValue().equals("1")) {
            respuesta.setValue("Nunca");
        } else if (respuesta.getValue().equals("2")) {
            respuesta.setValue("Casi nunca");
        } else if (respuesta.getValue().equals("3")) {
            respuesta.setValue("Casi siempre");
        } else {
            respuesta.setValue("Siempre");
        }

        return respuesta;
    }

    public void setRespuesta(StringProperty respuesta) {
        this.respuesta = respuesta;
    }

    public String getPreguntaString() {
        return pregunta.getValue();
    }

    public void setPregunta(String pregunta) {
        this.pregunta = new SimpleStringProperty(pregunta);
    }

    public String getRespuestaString() {
        return respuesta.getValue();
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = new SimpleStringProperty(respuesta);
    }

}
