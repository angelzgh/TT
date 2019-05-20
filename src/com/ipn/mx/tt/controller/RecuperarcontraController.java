package com.ipn.mx.tt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ipn.mx.tt.dao.PreguntaSeguridadDAO;
import com.ipn.mx.tt.dao.UsuarioDAO;
import com.ipn.mx.tt.modelo.PreguntaSeguridadRespondida;
import com.ipn.mx.tt.modelo.Usuario;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Email;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.mail.MessagingException;

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
            String usuario = ud.correoDeUsuario(correo);
            Usuario u = new Usuario(ud.getUsuario(usuario));
            System.out.println(u.toString());
            PreguntaSeguridadRespondida psr = psd.getPregunta(usuario);
            if (psr.getPregunta().equals(pregunta) && psr.getRespuesta().equals(respuesta)) {
                try {
                    CustomMessage cm = new CustomMessage("Aviso", "Se envío un correo a tu dirección registrada.", 0);
                    System.out.println("CONFIRMADO");
                    Email e = new Email(u.getNombre(), u.getApellido(), u.getId(), u.getCorreo(), u.getContraseña());
                    e.send();
                    LoginController lc = (LoginController) cv.cambiarVista("/Center/Login.fxml", panelPrincipal);
                    lc.setUdao(ud);
                } catch (MessagingException ex) {
                    Logger.getLogger(RecuperarcontraController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Verifica tu información", 2);
            }
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "El correo proporcionado no esta relacionado a ningún usuario registrado", 0);
        }

    }

    @FXML
    void cancelarCambio(ActionEvent event) {
        LoginController lc = (LoginController) cv.cambiarVista("/Center/Login.fxml", panelPrincipal);
        lc.setUdao(ud);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v = new Validador();
        psd = new PreguntaSeguridadDAO();
        psd.conectar();

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

    public UsuarioDAO getUd() {
        return ud;
    }

    public void setUd(UsuarioDAO ud) {
        this.ud = ud;
    }

}
