/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import com.ipn.mx.tt.controller.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class movEscena {

    public Object cambiarEscena(Event event, String file) {
        FXMLLoader root;
        Object o = null;
        try {
            root = new FXMLLoader(getClass().getResource("/Center/"+file));
            Parent t = root.load();
            Scene s = new Scene(t);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(s);
            window.centerOnScreen();
            window.show();
            o = root.getController();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
}
