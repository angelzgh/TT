/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

import com.mongodb.DBCursor;
import java.util.List;

/**
 *
 * @author Axel Reyes
 */
public class CutoffDAO extends DocumentoDAO {

    public CutoffDAO(String base, String coleccion) {
        super(base, coleccion);
    }

    public CutoffDAO() {
        super("TT", "Cutoff");
    }

    public List getCutOff() {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DBCursor cursor = cjm.getMongoCollection().find();

        return cursor.toArray();
    }
}
