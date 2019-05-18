/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Usuario;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 *
 * @author User
 */
public class UsuarioDAO extends DocumentoDAO {

    public UsuarioDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public UsuarioDAO() {
        super("TT", "User");
    }

    public DBObject convertirUser(Usuario u) {

        return new BasicDBObject("_id", u.getId())
                .append("contraseña", u.getContraseña())
                .append("Nombre", u.getNombre())
                .append("Apellido", u.getApellido())
                .append("Correo", u.getCorreo())
                .append("numEmpleado", u.getNumEmpleado())
                .append("Telefono", u.getTelefono())
                .append("Horario", u.getHorario());

    }

    public void insertarUsuario(Usuario u) {

        cjm.getMongoCollection().insert(convertirUser(u));
        System.out.println("Registro Agregado con éxito");
    }

    public Usuario buscarUsuario(String id) {

        DBObject query = new BasicDBObject("_id", id);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        Usuario user;
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            user = new Usuario(jo);
        } else {
            user = new Usuario();
        }
        return user;
    }

    public boolean usuarioExiste(String usuario) {
        DBObject query = new BasicDBObject("_id", usuario);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        return cursor.hasNext();
    }

    public boolean correoExiste(String correo) {
        DBObject query = new BasicDBObject("Correo", correo);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        return cursor.hasNext();
    }

    public String correoDeUsuario(String correo) {
        DBObject query = new BasicDBObject("Correo", correo);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        return (String) cursor.one().get("_id");
    }

    public boolean actualizarDatos(Usuario text) {

        if (usuarioExiste(text.getId())) {
            System.out.println(text.getId());
            DBObject query = new BasicDBObject("_id", text.getId());
            cjm.getMongoCollection().update(query, new BasicDBObject("$set",
                    new BasicDBObject("Nombre", text.getNombre())
                            .append("Apellido", text.getApellido())
                            .append("Correo", text.getCorreo())
                            .append("Telefono", text.getTelefono())
                            .append("Horario", text.getHorario())
            ));
            return true;
        } else {
            return false;
        }
    }

    public boolean actualizarContraseña(Usuario text) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (usuarioExiste(text.getId())) {
            DBObject query = new BasicDBObject("_id", text.getId());
            cjm.getMongoCollection().update(query, new BasicDBObject("$set",
                    new BasicDBObject("contraseña", text.getContraseña())
            ));
            return true;
        } else {
            return false;
        }
    }

    public DBObject getUsuario(String usuario) {
        DBObject query = new BasicDBObject("_id", usuario);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        return cursor.one();
    }

}
