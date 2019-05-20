/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class ResultadoDAO extends DocumentoDAO {

    public ResultadoDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public ResultadoDAO() {
        super("TT", "Resultado");
    }

    public void insertarResultado(DBObject dbo) {
        cjm.getMongoCollection().insert(dbo);
    }

    public List obtenerTodo() {
        DBCursor cursor = cjm.getMongoCollection().find();
        return cursor.toArray();
    }

    public List obtenerHombre() {
        DBObject dbo = new BasicDBObject("sexo", "H");
        DBCursor cursor = cjm.getMongoCollection().find();
        return cursor.toArray();
    }

    public List obtenerMujer() {
        DBObject dbo = new BasicDBObject("sexo", "M");
        DBCursor cursor = cjm.getMongoCollection().find();
        return cursor.toArray();
    }
}
