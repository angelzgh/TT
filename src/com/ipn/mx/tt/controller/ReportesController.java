/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.ResultadoDAO;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
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
public class ReportesController implements Initializable {

    private Validador v;
    private ResultadoDAO rd;
    @FXML
    private JFXRadioButton rbedad;

    @FXML
    private JFXRadioButton rbrabgo;

    @FXML
    private JFXRadioButton rbtodo;

    @FXML
    private JFXTextField txtedad;

    @FXML
    private JFXTextField txtrangode;

    @FXML
    private JFXTextField txtrangoa;

    @FXML
    private JFXCheckBox chbmuj;

    @FXML
    private JFXCheckBox chbhom;

    @FXML
    private JFXCheckBox chbhsdq;

    @FXML
    private JFXCheckBox chbs50;

    @FXML
    private JFXCheckBox chbinso;

    @FXML
    private JFXCheckBox chbritmo;

    @FXML
    private JFXCheckBox chbsdlp;

    @FXML
    private JFXCheckBox chbapnea;

    @FXML
    private JFXCheckBox chbnarc;

    @FXML
    private JFXCheckBox chbhiper;

    @FXML
    private BorderPane panelRight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v = new Validador();
        rd=new ResultadoDAO();
        rd.conectar();
    }

    public void obtenerPacientes() {
        if (rbtodo.isSelected()) {
            System.out.println("TODO");
            txtedad.setVisible(false);
            txtrangode.setVisible(false);
            txtrangoa.setVisible(false);
            buscarPacientes();
        } else if (rbedad.isSelected()) {

            txtedad.setVisible(true);
            txtrangode.setVisible(false);
            txtrangoa.setVisible(false);
            System.out.println("EDAD");
        } else {
            txtedad.setVisible(false);
            txtrangode.setVisible(true);
            txtrangoa.setVisible(true);
            System.out.println("RANGO");
        }
    }

    @FXML
    void obtenerPaciente(ActionEvent event) {
        obtenerPacientes();
        
    }

    @FXML
    void buscarPacientes(ActionEvent event) {
        buscarPacientes();
    }

    private int busquedaSexo() {
        if (chbhom.isSelected() && chbmuj.isSelected()) {
            return 0;
        } else if (chbhom.isSelected() && !chbmuj.isSelected()) {
            return 1;
        } else if (!chbhom.isSelected() && chbmuj.isSelected()) {
            return 2;
        } else {
            return 99;
        }

    }

    private void buscarPacientes() {
        int bs = busquedaSexo();
        System.out.println(bs);
        if (rbedad.isSelected()) {
            String edad = v.validarTF(txtedad);

            if (edad.length() > 0) {

            } else {
                CustomMessage cm = new CustomMessage("Error", "El campo no puede estar vacío", 2);
            }
        } else if (rbrabgo.isSelected()) {
            String desde = v.validarTF(txtrangode);
            String hasta = v.validarTF(txtrangoa);
            if (desde.length() > 0 && hasta.length() > 0) {

            }
            else {  
            CustomMessage cm = new CustomMessage("Error", "El campo no puede estar vacío", 2);
        }
        } else {
            List ls=rd.obtenerTodo();
            System.out.println(ls);
            
        } 
    }
}
