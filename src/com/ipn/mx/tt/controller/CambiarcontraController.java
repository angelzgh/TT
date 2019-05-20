/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.UsuarioDAO;
import com.ipn.mx.tt.modelo.Usuario;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class CambiarcontraController implements Initializable {

    private Usuario usuario;
    private menuController mc;
    private cargadorVista cv;
    private UsuarioDAO ud;

    @FXML
    private JFXPasswordField txtCpass;

    @FXML
    private JFXPasswordField txtCnpass;

    @FXML
    private JFXPasswordField txtCrnpass;

    @FXML
    private JFXButton btnCcguardar;

    @FXML
    private JFXButton btnCcancelar;

    @FXML
    private AnchorPane panelP;
      @FXML
    private JFXComboBox<?> cbxpregunta;

    @FXML
    private JFXTextField txtrespuesta;
    
    @FXML
    private JFXCheckBox chbsi;

    @FXML
    private JFXCheckBox chbno;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void CambiarContraseña(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            cambiarContraseña();
        } else {
        }
    }

    @FXML
    void cambiarContraseña(ActionEvent event) {
        cambiarContraseña();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    public void cambiarContraseña() {
        if (txtCnpass.getText().length() > 0 && txtCpass.getText().length() > 0 && txtCrnpass.getText().length() > 0) {
            if (txtCpass.getText().equals(usuario.getContraseña())) {

                if (txtCnpass.getText().equals(txtCrnpass.getText())) {
                    usuario.setContraseña(txtCrnpass.getText());
                    if (ud.actualizarContraseña(usuario)) {
                        CustomMessage cm = new CustomMessage("AVISO", "Se actualizó la contraseña.", 0);
                    } else {

                        CustomMessage cm = new CustomMessage("ERROR", "No se pudo actualizar la contraseña...", 0);
                    }
                } else {

                    CustomMessage cm = new CustomMessage("ERROR", "Las contraseña no coinciden.", 0);
                }
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Hay un error en tu contraseña.", 0);
            }
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "Los campos no pueden estar vacíos.", 0);
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        CuentaEspecialistaController cec = (CuentaEspecialistaController) cv.cambiarVista("/Center/CuentaEspecialista.fxml", panelP);
        System.out.println(usuario.toString());
        cec.colocarDatos(usuario);
        cec.setMc(mc);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
        ud = new UsuarioDAO();
    }

}
