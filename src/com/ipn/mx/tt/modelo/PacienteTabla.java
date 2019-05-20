/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axel Reyes
 */
public class PacienteTabla {

    private StringProperty nombre;
    private StringProperty edad;
    private StringProperty CURP;
    private StringProperty num;
    private Paciente origen;

    public PacienteTabla(String CURP, String nombre, String edad, Paciente origen, Double num) {
        this.CURP = new SimpleStringProperty(CURP);
        this.nombre = new SimpleStringProperty(nombre);
        this.edad = new SimpleStringProperty(edad);
        this.num = new SimpleStringProperty("" + num);
        this.origen = origen;
    }

    public StringProperty getNum() {
        return num;
    }

    public void setNum(StringProperty num) {
        this.num = num;
    }

    public Paciente getOrigen() {
        return origen;
    }

    public void setOrigen(Paciente origen) {
        this.origen = origen;
    }

    public PacienteTabla(Paciente paciente) {
        this.CURP = new SimpleStringProperty(paciente.getCURP());
        this.nombre = new SimpleStringProperty(paciente.getNombre());
        try {
            this.edad = new SimpleStringProperty("" + paciente.getEdad());
        } catch (ParseException ex) {
            Logger.getLogger(PacienteTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public StringProperty getEdad() {
        return edad;
    }

    public void setEdad(StringProperty edad) {
        this.edad = edad;
    }

    public StringProperty getCURP() {
        return CURP;
    }

    public void setCURP(StringProperty CURP) {
        this.CURP = CURP;
    }

}
