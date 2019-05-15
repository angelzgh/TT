/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.util.ConexionJavaMongo;
import com.mongodb.DBCursor;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class PreguntaEquivalenciaDAO {

    ConexionJavaMongo cjm;
    String base, coleccion;

    public PreguntaEquivalenciaDAO(String base, String coleccion) {
        this.base = base;
        this.coleccion = coleccion;
        cjm = new ConexionJavaMongo(base, coleccion);
    }

    public PreguntaEquivalenciaDAO() {
        this.base = "TT";
        this.coleccion = "Equivalencias";
        cjm = new ConexionJavaMongo(base, coleccion);

    }

    public void conectar() {
        this.cjm.conectar();
    }

    public List getEquivalencia() {
        DBCursor cursor = cjm.getMongoCollection().find();

        return cursor.toArray();
    }
}
