package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.UsuarioDAO;
import com.ipn.mx.tt.modelo.Usuario;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.cargadorVista;
import com.ipn.mx.tt.util.movEscena;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LoginController implements Initializable {

    private movEscena mov;
    private cargadorVista cv;
    private UsuarioDAO udao;

    
    @FXML
    private Label lblStatus;

    @FXML
    private Label lblRecuperar;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private JFXButton btnAcceder;
    @FXML
    private AnchorPane login;

    public UsuarioDAO getUdao() {
        return udao;
    }

    public void setUdao(UsuarioDAO udao) {
        this.udao = udao;
    }

    
    @FXML
    void btnActionPrueba(ActionEvent event) {
//        UsuarioDAO udao = new UsuarioDAO();
//        udao.insertarUsuario(new Usuario(txtUser.getText(), txtPass.getText()));
        lblStatus.setVisible(true);
        lblStatus.setText("----");
        txtPass.setText("");
        txtUser.setText("");
    }

    public void iniciarSesion(Event e) {

        Usuario user;
        if (txtPass.getText().length() > 0 && txtUser.getText().length() > 0) {

            user = udao.buscarUsuario(txtUser.getText());
            if (user.getContraseña().equals(txtPass.getText())) {
                lblStatus.setText("BIENVENIDO");
                menuController o = (menuController) mov.cambiarEscena(e, "Menu.fxml");
                o.setUsuario(user);

                CustomMessage cm = new CustomMessage("BIENVENIDO", "Bienvenido al sistema", 0);
                //alertMessage.alert(0, "BIENVENIDO AL SISTEMA", "BIENVENIDO: " + user.getId());
            } else {
                lblStatus.setText("Usuario o contraseña incorrecta");

            }
        } else {
            e.consume();
            CustomMessage cm = new CustomMessage("ERROR", "LOS CAMPOS NO PUEDEN ESTAR VACIOS", 2);

            // alertMessage.alert(0, "ERROR", "");
//                       Loader l=new Loader((Stage)login.getScene().getWindow(),5);
//            l.start();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        iniciarSesion(event);
    }

    @FXML
    private void handleButtonActionSound(ActionEvent event) {
        Media song = new Media(getClass().getResource("/sonidos/feel.mp3").toString());
        MediaPlayer media = new MediaPlayer(song);
        media.play();
    }

    @FXML
    private void onEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            iniciarSesion(event);
        } else {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // lblStatus.setVisible(false);
        mov = new movEscena();
        udao=new UsuarioDAO();
        udao.conectar();
        cv=new cargadorVista();
        
        // txtPass.getStyleClass().setAll("btn", "btn-primary");
    }

    @FXML
    void irMenuRecuperarContra(MouseEvent event) {
        RecuperarcontraController rcc=(RecuperarcontraController)cv.cambiarVista("/Center/Recuperarcontra.fxml",login);
        rcc.setUd(udao);
    }

}
