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

/**
 *
 * @author Axel Reyes
 */
public class ThreadPregunta {

    JFXRadioButton r1, r2, r3, r4, r5;
    JFXButton btn;
    int Time;

    public ThreadPregunta(int Time, JFXRadioButton r1, JFXRadioButton r2, JFXRadioButton r3, JFXRadioButton r4, JFXRadioButton r5,
             JFXButton btn) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.Time = Time;
        this.btn = btn;
    }

    public ThreadPregunta(int Time, JFXRadioButton r1, JFXRadioButton r2, JFXRadioButton r3, JFXRadioButton r4, JFXRadioButton r5
    ) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.Time = Time;
    }

    public void runClock() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                Platform.runLater(() -> {
                    r1.setDisable(true);
                    r2.setDisable(true);
                    r3.setDisable(true);
                    r4.setDisable(true);
                    r5.setDisable(true);
                });
                Thread.sleep( 1000);
                r1.setDisable(false);
                r2.setDisable(false);
                r3.setDisable(false);
                r4.setDisable(false);
                r5.setDisable(false);
                return null;

            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
