package com.ipn.mx.tt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ipn.mx.tt.dao.PreguntaSeguridadDAO;
import com.ipn.mx.tt.dao.UsuarioDAO;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class RecuperarcontraController implements Initializable {

    private PreguntaSeguridadDAO psd;
    private UsuarioDAO ud;
    private cargadorVista cv;
    private Validador v;
    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private Label lblStatus1;

    @FXML
    private JFXButton btnEnviar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXTextField txtCorreo;

    @FXML
    private JFXTextField txtResseguridad;

    @FXML
    private JFXComboBox<String> cmbpreguntaseguridad;

    @FXML
    void cambiarContra(ActionEvent event) {
        String pregunta = v.validarCbx(cmbpreguntaseguridad), respuesta = v.validarTF(txtResseguridad),
                correo = v.validarTF(txtCorreo);
        if (ud.correoExiste(correo)) {
            System.out.println(pregunta + respuesta);

        }

    }

    @FXML
    void cancelarCambio(ActionEvent event) {
        LoginController lc = (LoginController) cv.cambiarVista("/Center/Login.fxml", panelPrincipal);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v = new Validador();
        psd = new PreguntaSeguridadDAO();
        ud = new UsuarioDAO();
        psd.conectar();
        ud.conectar();

        cv = new cargadorVista();
        ObservableList<String> itemsP = FXCollections.observableArrayList(
                "Lugar de nacimiento de la madre",
                "Mejor amigo de la infancia",
                "Nombre de la primera mascota",
                "Profesor favorito",
                "Personaje histórico favorito",
                "Ocupación del abuelo",
                "Nombre de la calle en la que vivías de niño",
                "Libro favorito",
                "Segundo nombre del padre",
                "Canción favorita", "Apodo que tenías de niño",
                "Película favorita");
        cmbpreguntaseguridad.setItems(itemsP);
    }

}
