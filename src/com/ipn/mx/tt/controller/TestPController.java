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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Axel Reyes
 */
public class TestPController implements Initializable {

    @FXML
    private AnchorPane panelT;
    @FXML
    private Label lblPaciente;
    private cargadorVista cv;

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnCerrar;
    private menuController mc;

    @FXML
    void volverInicio(ActionEvent event) {
        InicioController ic=(InicioController) cv.cambiarVista("/Center/Menu.fxml", mc.getPanelPrin());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv=new cargadorVista();
    }

    void setMc(menuController mc) {
        this.mc=mc;
    }

}
