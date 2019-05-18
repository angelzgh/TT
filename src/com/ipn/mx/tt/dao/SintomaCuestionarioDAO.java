/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.SintomaCuestionario;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class SintomaCuestionarioDAO extends DocumentoDAO {

    public SintomaCuestionarioDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public SintomaCuestionarioDAO() {
        super("TT", "SintomaCuestionario");
    }

    public SintomaCuestionario traerSintoma(Double numCuestionario, Double idTrastorno) {
        
        DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario);
        DBObject dbo1 = new BasicDBObject("_idTrastorno", idTrastorno);
        BasicDBList or = new BasicDBList();
        or.add(dbo);
        or.add(dbo1);
        DBObject query = new BasicDBObject("$and", or);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        System.out.println("TAM:" + cursor.size() + numCuestionario + "--" + idTrastorno);
        SintomaCuestionario sc;
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            sc = new SintomaCuestionario(jo);
            System.out.println(sc.toString());
        } else {
            sc = new SintomaCuestionario();
        }
        return sc;
    }

    public void insertarSintomaCuestionario(DBObject dbo) {
        cjm.getMongoCollection().insert(dbo);
    }

}
