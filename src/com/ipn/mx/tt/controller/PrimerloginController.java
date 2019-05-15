package com.ipn.mx.tt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author garci
 */
public class PrimerloginController implements Initializable {
 @FXML
    private Button btnEnviar;

    @FXML
    private Label lblStatus1;

    @FXML
    private MenuButton cmbpreguntaseguridad;

    @FXML
    private TextField txtResseguridad;

    @FXML
    private PasswordField txtPassconf;

    @FXML
    private PasswordField txtPassnueva;
    

    @FXML
    void handleButtonAction(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
