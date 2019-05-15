/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AlertMessage {

    public int ALERT_MESSAGE = 0;
    public int ERROR_MESSAGE = 1;
    public int HELP_MESSAGE = 2;
    public int LADO = 45;
    public int respuesta;

    public AlertMessage() {

    }

    public void alert(int code, String Titulo, String Mensaje) {
        String codigo;
        switch (code) {
            case 0:
                codigo = "advertencia.png";
                break;
            case 1:
                codigo = "error.png";
                break;
            case 2:
                codigo = "ayuda.png";
                break;
            default:
                codigo = "default.png";

        }
        BorderPane border = new BorderPane();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Titulo);
        window.setMinWidth(400);
        window.setMinHeight(130);
        window.setMaxHeight(130);
        window.setMaxWidth(400);
        Label label = new Label(Mensaje);
        ImageView image = new ImageView("/iconos/" + codigo);
        image.setFitHeight(LADO);
        image.setFitWidth(LADO);
        Button b = new Button("Aceptar");
        b.setOnAction(e -> window.close());
        b.setOnKeyReleased(e -> {

            if (e.getCode().equals(KeyCode.ENTER)) {
                window.close();
                respuesta = 1;
            }
        });
        HBox layoutIzq = new HBox(10);
        HBox layoutCentro = new HBox(5);
        VBox layoutV = new VBox(10);
        layoutIzq.getChildren().addAll(image);
        layoutCentro.getChildren().addAll(label);
        border.setCenter(layoutCentro);
        border.setLeft(layoutIzq);
        border.setRight(layoutV);
        layoutIzq.setAlignment(Pos.BASELINE_LEFT);
        layoutV.getChildren().addAll(b);
        layoutV.setAlignment(Pos.BOTTOM_RIGHT);
        b.setAlignment(Pos.BOTTOM_RIGHT);
        layoutCentro.setAlignment(Pos.CENTER);
        layoutIzq.setAlignment(Pos.CENTER);

        layoutIzq.setPadding(new Insets(15));
        layoutV.setPadding(new Insets(5));
        Image icon = new Image("/imagenes/brain.png");
        window.getIcons().add(icon);
        Scene scene = new Scene(border);
        window.setScene(scene);
        window.showAndWait();

    }

    public int confirm(int code, String Titulo, String Mensaje) {
        String codigo;
        switch (code) {
            case 0:
                codigo = "advertencia.png";
                break;
            case 1:
                codigo = "error.png";
                break;
            case 2:
                codigo = "ayuda.png";
                break;
            default:
                codigo = "default.png";

        }
        BorderPane border = new BorderPane();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Titulo);
        window.setMinWidth(400);
        window.setMinHeight(130);
        window.setMaxHeight(130);
        window.setMaxWidth(400);
        Label label = new Label(Mensaje);
        ImageView image = new ImageView("/iconos/" + codigo);
        image.setFitHeight(LADO);
        image.setFitWidth(LADO);
        Button b = new Button("Aceptar");
        Button bc = new Button("Cancelar");
        b.setOnAction(e -> {
            window.close();
            respuesta = 1;
        });
        b.setOnKeyReleased(e -> {

            if (e.getCode().equals(KeyCode.ENTER)) {
                window.close();
                respuesta = 1;
            }
        });
        bc.setOnAction(e -> {
            window.close();
            respuesta = 2;
        });

        bc.setOnKeyReleased(e -> {

            if (e.getCode().equals(KeyCode.ENTER)) {
                window.close();
                respuesta = 2;
            }
        });
        HBox layoutIzq = new HBox(10);
        HBox layoutCentro = new HBox(5);
        HBox layoutV = new HBox(10);
        layoutIzq.getChildren().addAll(image);
        layoutCentro.getChildren().addAll(label);
        border.setCenter(layoutCentro);
        border.setLeft(layoutIzq);
        border.setRight(layoutV);
        layoutIzq.setAlignment(Pos.BASELINE_LEFT);
        layoutV.getChildren().addAll(b, bc);
        layoutV.setAlignment(Pos.BOTTOM_RIGHT);
        b.setAlignment(Pos.BOTTOM_RIGHT);
        layoutCentro.setAlignment(Pos.CENTER);
        layoutIzq.setAlignment(Pos.CENTER);

        layoutIzq.setPadding(new Insets(15));
        layoutV.setPadding(new Insets(5));
        Image icon = new Image("/imagenes/brain.png");
        window.getIcons().add(icon);
        Scene scene = new Scene(border);
        window.setScene(scene);
        window.showAndWait();

        return respuesta;
    }

}
