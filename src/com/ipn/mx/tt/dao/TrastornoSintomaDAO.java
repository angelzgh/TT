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
public class TrastornoSintomaDAO {

    ConexionJavaMongo cjm;
    String base, coleccion;

    public TrastornoSintomaDAO(String base, String coleccion) {
        this.base = base;
        this.coleccion = coleccion;
        cjm = new ConexionJavaMongo(base, coleccion);
    }

    public TrastornoSintomaDAO() {
        this.base = "TT";
        this.coleccion = "TrastornoXSintoma";
        cjm = new ConexionJavaMongo(base, coleccion);

    }

    public TrastornoSintomaDAO(DBObject dbObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void conectar() {
        this.cjm.conectar();
    }

    public LinkedList buscarTrastorno(int id) {
        LinkedList ls = new LinkedList();
        Double resp;
        DBObject query = new BasicDBObject("_idSintoma", Double.valueOf(id));
        DBCursor cursor = cjm.getMongoCollection().find(query);
        for (int i = 0; i < cursor.size(); i++) {

            DBObject jo = cursor.next();
            resp = (Double) jo.get("_idTrastorno");
            ls.add(resp.intValue());
        }
        return ls;
    }

    public List traerTrastornos() {
        DBCursor cursor = cjm.getMongoCollection().find();

        return cursor.toArray();
    }
}
