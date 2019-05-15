/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.util.ConexionJavaMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class PacienteDAO extends DocumentoDAO {

    public PacienteDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public PacienteDAO() {
        super("TT", "Paciente");
    }

    public DBObject convertirPaciente(Paciente p) {

        return new BasicDBObject("Direccion", p.getDireccion())
                .append("Fecha", p.getFecha())
                .append("Nombre", p.getNombre())
                .append("Apellido", p.getApellido())
                .append("Correo", p.getCorreo())
                .append("Sexo", p.getSexo())
                .append("Telefono", p.getTelefono())
                .append("_CURP", p.getCURP())
                .append("Escolaridad", p.getEscolaridad());
    }

    public void insertarPaciente(Paciente p) {

        cjm.getMongoCollection().insert(convertirPaciente(p));
        System.out.println("Registro Agregado con éxito");

    }

    public Paciente buscarPaciente(String id) {

        //db.users.find({name: /a/})
        DBObject query = new BasicDBObject("_CURP", id);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        Paciente paciente;
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            paciente = new Paciente(jo);
        } else {
            paciente = new Paciente();
        }
        
        return paciente;
    }

    public List buscarSimilar() {

        DBCursor cursor = cjm.getMongoCollection().find();
        System.out.println(cursor.toString());
        return cursor.toArray();
    }

    public boolean pacienteExiste(String curp) {
        DBObject query = new BasicDBObject("_CURP", curp);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        if (cursor.hasNext()) {
            return true;
        } else {
            return false;
        }
    }
}
