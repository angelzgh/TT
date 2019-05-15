/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class InstruccionesTestController implements Initializable {

    private cargadorVista cv;
    private InfoCuestionario ic;
    private menuController mc;
    private int tipoCuestionario;
    private Paciente paciente;
    private Boolean datosPaciente;
    
    @FXML
    private AnchorPane panelVista;

    @FXML
    private JFXButton btncomenzar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
    }

 

    public InfoCuestionario getIc() {
        return ic;
    }

    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    public int getTipoCuestionario() {
        return tipoCuestionario;
    }

    public void setTipoCuestionario(int tipoCuestionario) {
        this.tipoCuestionario = tipoCuestionario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Boolean getDatosPaciente() {
        return datosPaciente;
    }

    public void setDatosPaciente(Boolean datosPaciente) {
        this.datosPaciente = datosPaciente;
    }

    @FXML
    public void comenzarTest(ActionEvent event) {
        cargarTest();
    }

    public AnchorPane getPanelVista() {
        return panelVista;
    }

    public void setPanelVista(AnchorPane panelVista) {
        this.panelVista = panelVista;
    }
    

    public void cargarTest() {
        PacienteNuevo2Controller pnc = (PacienteNuevo2Controller) cv.cambiarVista("/Center/PacienteNuevo2.fxml", panelVista);
        pnc.setPaciente(paciente);
        System.out.println(ic.toString());
        pnc.setIc(ic);
        pnc.setMc(mc);
        pnc.setPanel(panelVista);
        pnc.setDatosPaciente(datosPaciente);
        pnc.setTipoCuestionario(tipoCuestionario);
    }

}
