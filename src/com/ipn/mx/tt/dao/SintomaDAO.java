/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.modelo.Sintoma;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class SintomaDAO extends DocumentoDAO {

    public SintomaDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public SintomaDAO() {
        super("TT", "Sintoma");
    }

    public Sintoma getSintoma(Double i) {

        DBObject query = new BasicDBObject("_idSintoma", i);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        Sintoma s;
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            s = new Sintoma(jo);
        } else {
            s = new Sintoma();
        }

        return s;
    }

}
