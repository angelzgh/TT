/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import com.ipn.mx.tt.controller.LoadingController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Axel Reyes
 */
public class Loader extends Thread {

    Stage st;
    public int Time;
    Stage stage;

    public Loader(Stage st, int Time) {
        this.st = st;
        this.Time = Time;
        stage = new Stage();
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            
            try {
                FXMLLoader fx = new FXMLLoader(getClass().getResource("/Center/Loading.fxml"));
                AnchorPane ap = fx.load();
                stage.initOwner(st);
                System.out.println("si existo");
                Scene scene = new Scene(ap, Color.TRANSPARENT);
                
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
                
                stage.centerOnScreen();
                Thread.sleep(Time * 1000);
                stage.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
