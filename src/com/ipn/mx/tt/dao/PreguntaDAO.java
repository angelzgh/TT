/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.util.ConexionJavaMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class PreguntaDAO {

    private Charset UTF_8 = Charset.forName("UTF-8");
    private Charset ISO = Charset.forName("ISO-8859-1");
    private ConexionJavaMongo cjm;
    String base, coleccion;

    public PreguntaDAO(String base, String coleccion) {
        this.base = base;
        this.coleccion = coleccion;
        cjm = new ConexionJavaMongo(base, coleccion);

    }
    public void conectar()
    {
        this.cjm.conectar();
    }

    public PreguntaDAO() {
        this.base = "TT";
        this.coleccion = "Pregunta";
        cjm = new ConexionJavaMongo(base, coleccion);

    }

    public DBObject convertirPregunta(Pregunta p) {

        String pregunta = new String(p.getTexto().getBytes(ISO), UTF_8);
        return new BasicDBObject("_idPregunta", p.getId()).append("pregunta", pregunta);

    }

    public void insertarPregunta(Pregunta p) {

        cjm.getMongoCollection().insert(convertirPregunta(p));
        System.out.println("Registro Agregado con éxito");

    }

    public Pregunta getPregunta(int i,int tipo) {

        DBObject query = new BasicDBObject("_idPregunta", i);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        Pregunta p;
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            p = new Pregunta(jo,tipo);
        } else {
            p = new Pregunta();
        }

        return p;
    }

    public List getPreguntas(int tipo) {
        List ls;
        DBCursor cursor = cjm.getMongoCollection().find();
        ls=cursor.toArray();
        return ls;
    }
}
