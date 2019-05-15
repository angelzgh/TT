/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import com.ipn.mx.tt.controller.PacienteNuevoController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Axel Reyes
 */
public class cargadorVista {

    public Object cambiarVista(String archivo, AnchorPane bp) {
        Object o = null;
        try {
            FXMLLoader fx = new FXMLLoader(getClass().getResource(archivo));

            AnchorPane ap = fx.load();

            o = fx.getController();
            bp.getChildren().setAll(ap);

        } catch (IOException ex) {
            Logger.getLogger(PacienteNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    public Object cambiarVista(String archivo, BorderPane bp) {
        Object o = null;
        try {
            FXMLLoader fx = new FXMLLoader(getClass().getResource(archivo));

            AnchorPane ap = fx.load();
            o = fx.getController();
            bp.setLeft(null);
            bp.setCenter(ap);
            bp.setRight(null);
        } catch (IOException ex) {
            Logger.getLogger(PacienteNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    public Object cambiarVistaIzq(String archivo, BorderPane bp) {
        Object o = null;
        try {
            FXMLLoader fx = new FXMLLoader(getClass().getResource(archivo));

            AnchorPane ap = fx.load();
            o = fx.getController();
            bp.setLeft(ap);
            bp.setCenter(null);
            bp.setRight(null);

        } catch (IOException ex) {
            Logger.getLogger(PacienteNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
}
