/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.modelo.Usuario;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Reloj;
import com.ipn.mx.tt.util.cargadorVista;
import com.ipn.mx.tt.util.movEscena;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class menuController implements Initializable {

    private movEscena mov;
    private Usuario usuario;
    private cargadorVista cv;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String val;
    @FXML
    private Tab TabInicio;

    @FXML
    private JFXButton btnCerrarSesion;
    @FXML
    private BorderPane PanelPrin;
    @FXML
    private Label lblHora;

    @FXML
    private Label lblFecha;

    @FXML
    private Tab TabConfig;

    @FXML
    private Tab TabTest;
    @FXML
    private Tab TabPaciente;

    @FXML
    private Tab TabPrediagnostico;

    @FXML
    private Tab TabReporte;

    @FXML
    private Tab TabAyuda;

    @FXML
    void cerrarSesion(Event event) {
        int resp = 1; //alertMessage.confirm(0, "¿Cerrar Sesión?", "Desea cerrar sesión");
        CustomMessage cm = new CustomMessage("Cerrar Sesión", "¿Desea cerrar sesión?", 1);
        if (cm.getMessage().equals(ButtonType.OK)) {
            mov.cambiarEscena(event, "Login.fxml");
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mov = new movEscena();
        cv = new cargadorVista();
        Reloj r = new Reloj(lblHora, lblFecha);
        r.runClock();
        cv.cambiarVista("/Center/Inicio.fxml", PanelPrin);
        TabInicio.setOnSelectionChanged((Event event) -> {

            //CustomMessage cm = new CustomMessage("Advertencia", "¿Salir sin guardar?", 3);
            //if (cm.getMessage().getButtonData().equals(ButtonType.OK.getButtonData())) {
            cv.cambiarVista("/Center/Inicio.fxml", PanelPrin);

        });

        TabConfig.setOnSelectionChanged((Event event) -> {

            ConfiguracionesController cc = (ConfiguracionesController) cv.cambiarVista("/Center/Configuraciones.fxml", PanelPrin);
            cc.setUsuario(usuario);
            cc.setMc(this);
            cc.clickInicial();
            System.out.println(usuario.toString());

        });
        TabPaciente.setOnSelectionChanged((Event event) -> {

            PacienteController tc = (PacienteController) cv.cambiarVista("/Center/Paciente.fxml", PanelPrin);
            tc.setC(this);
            tc.clickMenu();

        });
        TabTest.setOnSelectionChanged((Event event) -> {
            ComenzarTestController tc = (ComenzarTestController) cv.cambiarVista("/Center/ComenzarTest.fxml", PanelPrin);
            tc.setC(this);

        });
        TabPrediagnostico.setOnSelectionChanged((Event event) -> {
            PrediagnosticosController pc = (PrediagnosticosController) cv.cambiarVista("/Center/Prediagnosticos.fxml", PanelPrin);
            pc.setMc(this);
            pc.abrirHistorial();
            

        });
        TabReporte.setOnSelectionChanged((Event event) -> {
            cv.cambiarVista("/Center/Reportes.fxml", PanelPrin);

        });

        TabAyuda.setOnSelectionChanged((Event event) -> {
            cv.cambiarVista("/Center/Ayuda.fxml", PanelPrin);

        });

    }

    @FXML
    void onEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            cerrarSesion(event);
        } else {
        }
    }

    public BorderPane getPanelPrin() {
        return PanelPrin;
    }

    public void setPanelPrin(BorderPane PanelPrin) {
        this.PanelPrin = PanelPrin;
    }

}
