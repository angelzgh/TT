/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class PrediagnosticosController implements Initializable {

    private cargadorVista cv;
    private menuController mc;
    @FXML
    private Pane panelLeft;

    @FXML
    private JFXButton btnPhistorial;

    @FXML
    private BorderPane panelRight;

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    @FXML
    void abrirHistorial(ActionEvent event) {
        HistorialController hc = (HistorialController) cv.cambiarVista("/Center/Historial.fxml", panelRight);
        hc.setMc(mc);
        btnPhistorial.setDisable(true);
        hc.cargarDatos();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
    }

    public void abrirHistorial() {
        this.btnPhistorial.fire();
    }
}
