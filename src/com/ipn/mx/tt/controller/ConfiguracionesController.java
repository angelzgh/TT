/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.UsuarioDAO;
import com.ipn.mx.tt.modelo.Usuario;
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
public class ConfiguracionesController implements Initializable {

    private Usuario usuario;
    private UsuarioDAO ud;
    private menuController mc;
    private cargadorVista cv;

    @FXML
    private JFXButton btnCcuenta;

    @FXML
    private JFXButton btnCañadir;

    @FXML
    private BorderPane panelRight;

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    @FXML
    void abrirCuenta(ActionEvent event) {
        CuentaEspecialistaController cec
                = (CuentaEspecialistaController) cv.cambiarVista("/Center/CuentaEspecialista.fxml", panelRight);

        usuario = ud.buscarUsuario(usuario.getId());
        System.out.println(usuario.toString());
        cec.colocarDatos(usuario);
        cec.setMc(mc);
        btnCcuenta.setDisable(true);
        btnCañadir.setDisable(false);
        ud.desconectar();
    }

    @FXML
    void abrirEspecialista(ActionEvent event) {
        cv.cambiarVista("/Center/AñadirEspecialista.fxml", panelRight);

        btnCcuenta.setDisable(false);
        btnCañadir.setDisable(true);
        ud.desconectar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
        ud = new UsuarioDAO();
        ud.conectar();
    }

    void clickInicial() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.btnCcuenta.fire();
    }

}
