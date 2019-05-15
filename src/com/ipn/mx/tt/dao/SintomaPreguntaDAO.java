/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.util.ConexionJavaMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class SintomaPreguntaDAO {

    ConexionJavaMongo cjm;
    String base, coleccion;

    public SintomaPreguntaDAO(String base, String coleccion) {
        this.base = base;
        this.coleccion = coleccion;
        cjm = new ConexionJavaMongo(base, coleccion);
    }

    public SintomaPreguntaDAO() {
        this.base = "TT";
        this.coleccion = "SintomaXPregunta";
        cjm = new ConexionJavaMongo(base, coleccion);

    }

    public void conectar() {
        this.cjm.conectar();
    }

    public LinkedList buscarSintoma(int id) {
        LinkedList ls = new LinkedList();
        Double resp;
        DBObject query = new BasicDBObject("_idPregunta", Double.valueOf(id));
        DBCursor cursor = cjm.getMongoCollection().find(query);

        for (int i = 0; i < cursor.size(); i++) {

            DBObject jo = cursor.next();

            resp = (Double) jo.get("_idSintoma");
            ls.add(resp.intValue());
        }
        return ls;
    }

    public List traerSintomas() {

        DBCursor cursor = cjm.getMongoCollection().find();

        return cursor.toArray();
    }

}
