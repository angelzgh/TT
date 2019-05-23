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

/**
 * FXML Controller class
 *
 * @author garci
 */
public class AyudaController implements Initializable {

    private cargadorVista cv;
    @FXML
    private JFXButton btnAcerca;

    @FXML
    private JFXButton btnConfig;

    @FXML
    private JFXButton btnPaciente;

    @FXML
    private JFXButton btnTest;

    @FXML
    private JFXButton btnPrediagnostico;

    @FXML
    private JFXButton btnReportes;

    @FXML
    private BorderPane panelRight;

    @FXML
    private JFXButton btnsiguiente1;

    @FXML
    void verAcerca(ActionEvent event) {
        AcercadeController ac = (AcercadeController) cv.cambiarVista("/Center/Acercade.fxml", panelRight);
    }

    @FXML
    void verConfig(ActionEvent event) {
        AyudaConfController ac = (AyudaConfController) cv.cambiarVista("/Center/AyudaConf.fxml", panelRight);
    }

    @FXML
    void verPaciente(ActionEvent event) {
        AyudaPacController ac = (AyudaPacController) cv.cambiarVista("/Center/AyudaPac.fxml", panelRight);
    }

    @FXML
    void verPrediagnostico(ActionEvent event) {
        AyudaPredController ac = (AyudaPredController) cv.cambiarVista("/Center/AyudaPred.fxml", panelRight);
    }

    @FXML
    void verReporte(ActionEvent event) {
AyudaRepController ac=(AyudaRepController) cv.cambiarVista("/Center/AyudaRep.fxml", panelRight);
    }

    @FXML
    void verTest(ActionEvent event) {
AyudaTestController ac=(AyudaTestController) cv.cambiarVista("/Center/AyudaTest.fxml", panelRight);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
    }

}
