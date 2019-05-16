/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Cuestionario;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

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
        DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario)
                .append("_idCuestionario", idCuestionario);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);
        System.out.println(cursor.toArray());
        //System.out.println(cursor.one().toString());
        return dbo;
    }

}
