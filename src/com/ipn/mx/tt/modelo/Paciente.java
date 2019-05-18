/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.mongodb.DBObject;
import java.util.Date;

/**
 *
 * @author Axel Reyes
 */
public class Paciente {

    String Nombre;
    String Apellido;
    String Sexo;
    String Correo;
    String Fecha;
    String Direccion;
    String Telefono;
    String CURP;
    private String Escolaridad;

    public String getEscolaridad() {
        return Escolaridad;
    }

    public void setEscolaridad(String Escolaridad) {
        this.Escolaridad = Escolaridad;
    }
    

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public Paciente() {
                this.Nombre = "PACIENTE";
        this.Apellido = "GRAL";
        this.Sexo = "H";
        this.Correo = "gral@gmail.com";
        this.Fecha = "04/11/1996";
        this.Direccion = "-";
        this.Telefono = "-";
        this.CURP = "XEXX010101HNEXXXA4";
        this.Escolaridad="-";
    }

    @Override
    public String toString() {
        return "Paciente{" + "Nombre=" + Nombre + ", Apellido=" + Apellido + ", Sexo=" + Sexo + ", Correo=" + Correo + ", Fecha=" + Fecha + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", CURP=" + CURP + ", Escolaridad=" + Escolaridad + '}';
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getEdad() {
//        long ageInMillis = new Date().getTime() - new Date(Fecha).getTime();
//
//        Date age = new Date(ageInMillis);

        // return age.getYear();
        return 23;
    }

    public Paciente(String Nombre, String Apellido, String Sexo, String Correo, String Fecha, String Direccion, String Telefono, String CURP,String Escolaridad) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Sexo = Sexo;
        this.Correo = Correo;
        this.Fecha = Fecha;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.CURP = CURP;
        this.Escolaridad=Escolaridad;
    }

    public Paciente(DBObject dbo) {
        this.Nombre = (String) dbo.get("Nombre");
        this.Apellido = (String) dbo.get("Apellido");
        this.Sexo = (String) dbo.get("Sexo");
        this.Correo = (String) dbo.get("Correo");
        this.Fecha = (String) dbo.get("Fecha");
        this.Direccion = (String) dbo.get("Direccion");
        this.Telefono = (String) dbo.get("Telefono");
        this.CURP = (String) dbo.get("_CURP");
        this.Escolaridad = (String) dbo.get("Escolaridad");
    }

    public Paciente(PacienteTabla pt) {
        this.Nombre = pt.getNombre().getValue();
        this.CURP = pt.getCURP().getValue();
    }

}
