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
public class InfoCuestionario {

    private Double idCuestionario;
    private Double status;
    private String paciente;
    private String Especialista;
    private String registro;
    private String tiempoInicio;
    private String tiempoFin;
    private String tiempoDuracion;
    private String comentarios;

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(String tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public String getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(String tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public String getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(String tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public InfoCuestionario(Double idCuestionario, Double status, String paciente, String Especialista, String registro) {
        this.idCuestionario = idCuestionario;
        this.status = status;
        this.paciente = paciente;
        this.Especialista = Especialista;
        this.registro = registro;
        this.comentarios = "";
        this.tiempoDuracion = "";
        this.tiempoFin = "";
        this.tiempoInicio = "";
    }

    public InfoCuestionario(DBObject dbo) {
        System.out.println(dbo.toString());
        this.idCuestionario = (Double) dbo.get("_numCuestionario");
        this.status = (Double) dbo.get("status");
        this.paciente = (String) dbo.get("Paciente");
        this.Especialista = (String) dbo.get("Especialista");
        this.registro = (String) dbo.get("Registro");
        this.tiempoInicio = (String) dbo.get("TiempoInicio");
        this.tiempoFin = (String) dbo.get("TiempoFin");
        this.tiempoDuracion = (String) dbo.get("TiempoDuracion");
        this.comentarios = (String) dbo.get("Comentarios");

        System.out.println(dbo);
    }

    public InfoCuestionario(Double idCuestionario, String Especialista, String registro) {
        this.idCuestionario = idCuestionario;
        status = 0.0;
        this.paciente = "XEXX010101HNEXXXA4";
        this.Especialista = Especialista;
        this.registro = registro;
        this.comentarios = "";
        this.tiempoDuracion = "";
        this.tiempoFin = "";
        this.tiempoInicio = "";
    }

    public InfoCuestionario() {
        this.idCuestionario = 00000.0;
        this.status = 2.0;
        this.paciente = "PRUEBA";
        this.Especialista = "NINGUNO";
        this.registro = "";
        this.comentarios = "";
        this.tiempoDuracion = "";
        this.tiempoFin = "";
        this.tiempoInicio = "";
    }

    public Double getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Double idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getEspecialista() {
        return Especialista;
    }

    public void setEspecialista(String Especialista) {
        this.Especialista = Especialista;
    }

    @Override
    public String toString() {
        return "InfoCuestionario{" + "idCuestionario=" + idCuestionario + ", status=" + status + ", paciente=" + paciente + ", Especialista=" + Especialista + '}';
    }

}
