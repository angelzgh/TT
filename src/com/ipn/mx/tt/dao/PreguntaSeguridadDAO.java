/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.PreguntaSeguridadRespondida;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class PreguntaSeguridadDAO extends DocumentoDAO {

    public PreguntaSeguridadDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public PreguntaSeguridadDAO() {
        super("TT", "PreguntaSeguridad");
    }

    public void insertarPregunta(PreguntaSeguridadRespondida psr) {
        cjm.getMongoCollection().insert(psr.getDBObject());

    }

    public PreguntaSeguridadRespondida getPregunta(String usuario) {
        DBObject query = new BasicDBObject("Usuario", usuario);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        PreguntaSeguridadRespondida psr;
        if (cursor.hasNext()) {
            psr = new PreguntaSeguridadRespondida(cursor.one());
        } else {
            psr = new PreguntaSeguridadRespondida();
        }
        return psr;
    }

}
