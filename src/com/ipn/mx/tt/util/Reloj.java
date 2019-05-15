/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 *
 * @author Axel Reyes
 */
public class Reloj {
    
    Label lblHora,lblFecha;

    public Reloj(Label lblHora, Label lblFecha) {
        this.lblHora = lblHora;
        this.lblFecha = lblFecha;
    }
    
        public void runClock() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while (true) {

                    Platform.runLater(() -> {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        String[] date = dtf.format(now).split("-");
                        lblHora.setText(date[1]);
                        lblFecha.setText(date[0]);
                    });
                    Thread.sleep(1000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
