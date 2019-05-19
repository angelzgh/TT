/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class CuestionarioAplicadoDAO extends DocumentoDAO {

    public CuestionarioAplicadoDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public CuestionarioAplicadoDAO() {
        super("TT", "CuestionarioAplicado");
    }

    public List traerInformacion() {
        DBObject dbo = new BasicDBObject("status", 2.0);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);

        return cursor.toArray();
    }

    public void insertarInfoCuestionario(InfoCuestionario info) {
        cjm.getMongoCollection().insert(convertirInfoCuestionario(info));

    }

    public DBObject convertirInfoCuestionario(InfoCuestionario info) {
        return new BasicDBObject("_numCuestionario", info.getIdCuestionario())
                .append("status", info.getStatus())
                .append("Paciente", info.getPaciente())
                .append("Especialista", info.getEspecialista())
                .append("Registro", info.getRegistro())
                .append("TiempoInicio", info.getTiempoInicio())
                .append("TiempoFin", info.getTiempoFin())
                .append("TiempoDuracion", info.getTiempoDuracion());
    }

    public Double buscarSiguiente() {
        DBObject dbo = new BasicDBObject("_numCuestionario", -1);
        DBCursor cursor = cjm.getMongoCollection().find().sort(dbo).limit(1);
        if (cursor.hasNext()) {
            return (Double) cursor.one().get("_numCuestionario");
        } else {
            return 0.0;
        }

    }

    public InfoCuestionario traerInfo(Double numCuestionario) {
        InfoCuestionario ic;
        DBObject dbo = new BasicDBObject("_numCuestionario", numCuestionario);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);

        if (cursor.hasNext()) {
            DBObject jo = cursor.one();
            ic = new InfoCuestionario(jo);
        } else {
            ic = new InfoCuestionario();
        }
        System.out.println(ic.toString());
        return ic;
    }

    public List traerCuestionariosContestados() {
        DBObject dbo = new BasicDBObject("status", 2.0);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);

        return cursor.toArray();
    }

    public Double statusCuestionario(Double cuestionario) {
        DBObject dbo = new BasicDBObject("_numCuestionario", cuestionario);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);
        if (cursor.hasNext()) {
            return (Double) cursor.one().get("status");
        } else {
            return 0.0;
        }

    }

    public Double numCuestionario(String curp) {
        DBObject dbo = new BasicDBObject("Paciente", curp);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);
        if (cursor.hasNext()) {
            return (Double) cursor.one().get("_numCuestionario");
        } else {
            return 0.0;
        }

    }

    public boolean cuestionarioPrevio(String curp) {
        DBObject dbo = new BasicDBObject("Paciente", curp);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);
        return cursor.hasNext();// return (String) cursor.one().get("_numCuestionario");

    }

    public Double cuestionarioPrevioStatus(String curp) {
        DBObject dbo = new BasicDBObject("Paciente", curp);
        DBCursor cursor = cjm.getMongoCollection().find(dbo);
        return (Double) cursor.one().get("status");

    }

    public boolean actualizarDatos(InfoCuestionario ic) {

        if (cuestionarioExiste(ic.getIdCuestionario())) {
            DBObject query = new BasicDBObject("_numCuestionario", ic.getIdCuestionario());
            cjm.getMongoCollection().update(query, new BasicDBObject("$set",
                    new BasicDBObject("status",2.0)
                            .append("Registro", ic.getRegistro())
                            .append("TiempoInicio", ic.getTiempoInicio())
                            .append("TiempoFin", ic.getTiempoFin())
                            .append("TiempoDuracion", ic.getTiempoDuracion())
            ));
            return true;
        } else {
            return false;
        }
    }

    public boolean actualizarComentarios(InfoCuestionario ic) {

        if (cuestionarioExiste(ic.getIdCuestionario())) {
            DBObject query = new BasicDBObject("_numCuestionario", ic.getIdCuestionario());
            cjm.getMongoCollection().update(query, new BasicDBObject("$set",
                    new BasicDBObject("Comentarios", ic.getComentarios())
            ));
            return true;
        } else {
            return false;
        }
    }

    public boolean cuestionarioExiste(Double numCuestionario) {
        DBObject query = new BasicDBObject("_numCuestionario", numCuestionario);
        DBCursor cursor = cjm.getMongoCollection().find(query);
        return cursor.hasNext();
    }

}
