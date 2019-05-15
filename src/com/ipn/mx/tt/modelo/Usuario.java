
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.mongodb.DBObject;

/**
 *
 * @author User
 */
public class Usuario {

    String Id;
    String Contraseña;
    String Nombre;
    String Apellido;
    String Correo;
    Double numEmpleado;
    String Telefono;
    String Horario;

    public Usuario(String Id, String Contraseña, String Nombre, String Apellido, String Correo, Double numEmpleado, String Telefono, String Horario) {
        this.Id = Id;
        this.Contraseña = Contraseña;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.numEmpleado = numEmpleado;
        this.Telefono = Telefono;
        this.Horario = Horario;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public Usuario(String Id, String Contraseña) {
        this.Id = Id;
        this.Contraseña = Contraseña;
        this.Apellido = "";
        this.Correo = "";
        this.Nombre = "";
    }

    public Usuario(String Id, String Nombre, String Apellido, String Correo) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
    }

    public Usuario(String Id, String Nombre, String Apellido, String Correo, String Telefono, String Horario) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.Horario = Horario;
    }

    public Usuario() {
        Contraseña = "";
        Id = "";
    }

    public Usuario(DBObject dbo) {
        Id = (String) dbo.get("_id");
        Contraseña = (String) dbo.get("contraseña");
        Apellido = (String) dbo.get("Apellido");
        Nombre = (String) dbo.get("Nombre");
        Correo = (String) dbo.get("Correo");
        numEmpleado = (Double) dbo.get("numEmpleado");
        Telefono = (String) dbo.get("Telefono");
        Horario = (String) dbo.get("Horario");
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public Double getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(Double numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Id=" + Id + ", Contrase\u00f1a=" + Contraseña + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Correo=" + Correo + ", numEmpleado=" + numEmpleado + ", Telefono=" + Telefono + ", Horario=" + Horario + '}';
    }

}
