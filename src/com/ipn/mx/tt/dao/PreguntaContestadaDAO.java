/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.modelo.PreguntaTabla;
import com.ipn.mx.tt.modelo.Respuesta;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class PreguntaContestadaDAO extends DocumentoDAO {
    
    public PreguntaContestadaDAO(String base, String coleccion) {
        super(base, coleccion);
    }
    
    public PreguntaContestadaDAO() {
        super("TT", "PreguntaContestada");
    }
    
    public void guardarPreguntasContestadas(Double numCuestionario, List l) {
        List ln = new LinkedList();
        l.forEach((objectLista) -> {
            Respuesta r = (Respuesta) objectLista;
            System.out.println(r.toString());
            DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario)
                    .append("_idPregunta", r.getNumeroPregunta() + 0.0)
                    .append("respuesta", r.getRespuesta() + 0.0);
            ln.add(dbo);
        });
        cjm.getMongoCollection().insert(ln);
    }
    
    public PreguntaTabla getPregunta(int i, int idCuestionario) {
        PreguntaTabla pt = null;
        DBObject query = new BasicDBObject("_idPregunta", i);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            pt = new PreguntaTabla(String.valueOf((Double) jo.get("_idPregunta")), String.valueOf((Double) jo.get("respuesta")));
        }
        
        return pt;
    }
    
}
