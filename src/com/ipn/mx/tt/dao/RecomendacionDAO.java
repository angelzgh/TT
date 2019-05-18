/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class RecomendacionDAO extends DocumentoDAO {

    public RecomendacionDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public RecomendacionDAO() {
        super("TT", "Recomendacion");
    }

    public List traerRecomendacion(Double idTrastorno) {

        BasicDBObject trastorno = new BasicDBObject("_idTrastorno", idTrastorno);

        DBCursor cursor = cjm.getMongoCollection().find(trastorno);
        
    return cursor.toArray();
}
    
}
