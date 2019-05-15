/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.dao;

/**
 *
 * @author Axel Reyes
 */
public class CuestionarioDAO extends DocumentoDAO{
    
    public CuestionarioDAO(String base, String coleccion) {
        super(base, coleccion);
    }
    public CuestionarioDAO()
    {
        super("TT", "Cuestionario");
    }
    
    
}
