/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Cuestionario;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class PrediagnosticoDAO extends DocumentoDAO {

    public PrediagnosticoDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public PrediagnosticoDAO() {
        super("TT", "Prediagnostico");
    }

    public void insertarTrastornos(Double idCuestionario, Double numCuestionario, Cuestionario c) {
        DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario)
                .append("_idCuestionario", idCuestionario)
                .append("insomnio", c.getTrastorno(idCuestionario.intValue(), 1))
                .append("rc", c.getTrastorno(idCuestionario.intValue(), 2))
                .append("rls", c.getTrastorno(idCuestionario.intValue(), 3))
                .append("apnea", c.getTrastorno(idCuestionario.intValue(), 4))
                .append("hipersomnia", c.getTrastorno(idCuestionario.intValue(), 5))
                .append("narcolepsia", c.getTrastorno(idCuestionario.intValue(), 6))
                .append("otro", c.getTrastorno(idCuestionario.intValue(), 7));

        cjm.getMongoCollection().insert(dbo);
    }

    public DBObject traerTrastorno(Double idCuestionario, Double numCuestionario) {
        DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario);
        DBObject dbo1 = new BasicDBObject("_idCuestionario", idCuestionario);

        BasicDBList or = new BasicDBList();
        or.add(dbo);
        or.add(dbo1);
        DBObject query = new BasicDBObject("$and", or);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        System.out.println(cursor.size());
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        return cursor.one();
    }

    public LinkedList traerTrastornos(Double numCuestionario) {
        LinkedList ls=new LinkedList();
        DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario);
        BasicDBList or = new BasicDBList();
        or.add(dbo);
        DBObject query = new BasicDBObject("$and", or);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        System.out.println(cursor.size());
        while (cursor.hasNext()) {
            ls.add(cursor.next());
        }
        return ls;
    }

}
