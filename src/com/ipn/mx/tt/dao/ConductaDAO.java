/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;


import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.Pregunta;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class ConductaDAO extends DocumentoDAO{
    
    public ConductaDAO(String base, String coleccion) {
        super(base, coleccion);
    }
    public ConductaDAO()
    {
        super("TT", "Conducta");
    }
    
    public void insertar(Conducta cd)
    {
        DBObject dbo=new BasicDBObject("_numCuestionario",cd.getNumCuestionario())
                .append("Trabaja", cd.isTrabaja())
                .append("HorarioTrabajo", cd.getHorarioTrabajo())
                .append("JornadaLaboral", cd.getJornadaLaboral())
                .append("DiasDescanso", cd.getDiasDeDescanso())
                .append("HorasTrabajo", cd.getHorasDeTrabajo())
                .append("PromedioHoras", cd.getPromedioHoras())
                .append("PromedioHorasL", cd.getPromedioHorasLaborales())
                .append("PromedioHorasD", cd.getPromedioHorasDescanso());
        cjm.getMongoCollection().insert(dbo);
    }
    public Conducta buscarConducta(Double numPregunta)
    {
        Conducta c;
                DBObject query = new BasicDBObject("_numCuestionario", numPregunta);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            c = new Conducta(jo);
        } else {
            c = null;
        }
        return c;
    }
    
    
}
