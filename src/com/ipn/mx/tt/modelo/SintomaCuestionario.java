/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class SintomaCuestionario {

    private Double numCuestionario;
    private Double idTrastorno;
    private String sintomas;

    public SintomaCuestionario(Double numCuestionario, Double idTrastorno, String sintomas) {
        this.numCuestionario = numCuestionario;
        this.idTrastorno = idTrastorno;
        this.sintomas = sintomas;
    }
        public SintomaCuestionario() {
        this.numCuestionario = 0.0;
        this.idTrastorno = 0.0;
        this.sintomas = " ";
    }

    public SintomaCuestionario(DBObject dbo) {
        this.numCuestionario = (Double) dbo.get("_numCuestionario");
        this.idTrastorno = (Double) dbo.get("_idTrastorno");
        this.sintomas = (String) dbo.get("sintomas");
    }

    public Double getNumCuestionario() {
        return numCuestionario;
    }

    public void setNumCuestionario(Double numCuestionario) {
        this.numCuestionario = numCuestionario;
    }

    public Double getIdTrastorno() {
        return idTrastorno;
    }

    public void setIdTrastorno(Double idTrastorno) {
        this.idTrastorno = idTrastorno;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    @Override
    public String toString() {
        return "SintomaCuestionario{" + "numCuestionario=" + numCuestionario + ", idTrastorno=" + idTrastorno + ", sintomas=" + sintomas + '}';
    }
     public DBObject toDBObject()
     {
         return new BasicDBObject("_numCuestionario",numCuestionario)
                 .append("_idTrastorno", idTrastorno)
                 .append("sintomas", sintomas);
     }

}
