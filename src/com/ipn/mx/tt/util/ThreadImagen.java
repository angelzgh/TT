/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

/**
 *
 * @author Axel Reyes
 */
public class ThreadImagen {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    int Time;
    private ImageView iv;
    
    public ThreadImagen(ImageView iv, int Time) {
        
        this.iv = iv;
        this.Time = Time;
    }
    
    public void runClock() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                
                Platform.runLater(() -> {
                    iv.setDisable(true);
                });
                Thread.sleep(1000);
                iv.setDisable(false);
                return null;
                
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}

