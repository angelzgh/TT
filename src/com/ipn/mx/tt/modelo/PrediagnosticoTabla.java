/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.mongodb.DBObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axel Reyes
 */
public class PrediagnosticoTabla {

    private StringProperty Nombre;
    private StringProperty Edad;
    private StringProperty Fecha;
    private StringProperty NumeroCuestionario;
    private StringProperty Status;

    public PrediagnosticoTabla(String Nombre, String Edad, String Fecha, String NumeroCuestionario) {
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Edad = new SimpleStringProperty(Edad);
        this.Fecha = new SimpleStringProperty(Fecha);
        this.NumeroCuestionario = new SimpleStringProperty(NumeroCuestionario);
    }

    public PrediagnosticoTabla(DBObject dbo) {
        this.Nombre = new SimpleStringProperty((String) dbo.get("Paciente"));
        this.NumeroCuestionario = new SimpleStringProperty(String.valueOf((Double) dbo.get("_numCuestionario")));
        this.Status = new SimpleStringProperty(String.valueOf((Double) dbo.get("status")));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String[] date = dtf.format(now).split("-");
        this.Fecha = new SimpleStringProperty(date[0]);
        this.Edad = new SimpleStringProperty(String.valueOf("67"));
    }

    public StringProperty getStatus() {
        return Status;
    }

    public void setStatus(StringProperty Status) {
        this.Status = Status;
    }

    public StringProperty getNombre() {
        return Nombre;
    }

    public void setNombre(StringProperty Nombre) {
        this.Nombre = Nombre;
    }

    public StringProperty getEdad() {
        
        return Edad;
    }

    public void setEdad(StringProperty Edad) {
        this.Edad = Edad;
    }

    public StringProperty getFecha() {
        return Fecha;
    }

    public void setFecha(StringProperty Fecha) {
        this.Fecha = Fecha;
    }

    public StringProperty getNumeroCuestionario() {
        return NumeroCuestionario;
    }

    public void setNumeroCuestionario(StringProperty NumeroCuestionario) {
        this.NumeroCuestionario = NumeroCuestionario;
    }

}
