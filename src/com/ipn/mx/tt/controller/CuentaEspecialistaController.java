/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.UsuarioDAO;
import com.ipn.mx.tt.modelo.Usuario;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class CuentaEspecialistaController implements Initializable {

    private UsuarioDAO ud;
    private cargadorVista cv;
    private Validador v;
    @FXML
    private JFXButton btnCguardar;
    @FXML
    private AnchorPane panelP;

    @FXML
    private JFXButton btnCambiarcontra;

    @FXML
    private JFXTextField txtCnombre;

    @FXML
    private JFXTextField txtCcorreo;

    @FXML
    private JFXTextField txtCusuario;
    @FXML
    private JFXTextField txtCtelefono;

    @FXML
    private JFXTextField txtChorario;
    @FXML
    private Pane panel;
    @FXML
    private JFXTextField txtCapellido;
    @FXML
    private JFXTextField txtCnoempleado;
    @FXML
    private JFXComboBox<?> cbxpregunta;

    @FXML
    private JFXTextField txtrespuesta;

    private Usuario u;
    private menuController mc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
        ud = new UsuarioDAO();
        v = new Validador();
        ud.conectar();

    }

    @FXML
    void cambiarContraseña(ActionEvent event) {

        CambiarcontraController cc = (CambiarcontraController) cv.cambiarVista("/Center/Cambiarcontra.fxml", panelP);
        cc.setUsuario(u);
        cc.setMc(mc);
    }

    @FXML
    void guardarDatos(ActionEvent event) {

        String nombre = v.validarTF(txtCnombre);
        String apellido = v.validarTF(txtCapellido);
        String correo = v.validarTF(txtCcorreo);
        String telefono = v.validarTF(txtCtelefono);
        String horario = v.validarTF(txtChorario);
        Usuario usu = new Usuario(u.getId(), nombre, apellido, correo, telefono, horario);

        if (ud.actualizarDatos(usu)) {

            CustomMessage cm = new CustomMessage("AVISO", "Se realizaron los cambios con exito", 0);
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "No pudimos actualizar los datos", 2);

        }

    }

    public void colocarDatos(Usuario u) {
        this.txtCapellido.setText(u.getApellido());
        this.txtCcorreo.setText(u.getCorreo());
        this.txtCnombre.setText(u.getNombre());
        this.txtCusuario.setText(u.getId());
        this.txtCnoempleado.setText("" + u.getNumEmpleado().intValue());
        this.txtCtelefono.setText(u.getTelefono());
        this.txtChorario.setText(u.getHorario());
        this.u = u;
    }

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

}
