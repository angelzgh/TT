/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Axel Reyes
 */
public class CustomMessage {

    Alert am;
    String texto;
    String titulo;
    int tipo;
    Optional<ButtonType> result;
    Image icon = new Image("/imagenes/brain.png");

    public CustomMessage(String titulo, String texto, int tipo) {
        this.texto = texto;
        this.tipo = tipo;
        this.titulo = titulo;
        switch (this.tipo) {
            case 0:
                //Mensaje
                am = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
                break;
            case 1:
                am = new Alert(Alert.AlertType.CONFIRMATION, "");

                break;
            case 2:
                am = new Alert(Alert.AlertType.ERROR, "");
                break;
            case 3:
                ButtonType YES = new ButtonType("SI", ButtonBar.ButtonData.OK_DONE);
                ButtonType NO = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                am = new Alert(Alert.AlertType.CONFIRMATION, "", YES, NO);
                break;
            case 4:
                ButtonType b1 = new ButtonType("AHORA", ButtonBar.ButtonData.OK_DONE);
                
                ButtonType b2 = new ButtonType("DESPUES", ButtonBar.ButtonData.CANCEL_CLOSE);
                am = new Alert(Alert.AlertType.CONFIRMATION, "", b1, b2);
                break;
            default:

        }
        Stage s = (Stage) am.getDialogPane().getScene().getWindow();
        s.getIcons().add(icon);
        am.setTitle(titulo);
        am.setHeaderText(texto);
        result = am.showAndWait();
    }

    public ButtonType getMessage() {

        return result.get();
    }
}
