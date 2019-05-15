/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Spinner;

/**
 *
 * @author Axel Reyes
 */
public class Validador {

    public String validarTF(JFXTextField jf) {
        if (jf.getText().length() > 0) {
            return jf.getText();
        } else {
            return "";
        }
    }
    public Double validarTFtoDouble(JFXTextField jf) {
        if (jf.getText().length() > 0) {
            return Double.valueOf(jf.getText());
        } else {
            return 0.0;
        }
    }
    public String validars(String text) {
        if (text.length() > 0) {
            return text;
        } else {
            return "";
        }
    }

    public String validarDP(JFXDatePicker dp) {
        if (dp.getValue() != null) {
            LocalDate ld=dp.getValue();
            String cadena=ld.format(DateTimeFormatter.ISO_DATE);
            return cadena;
        } else {
            return "";
        }
    }
    public Double validarSp(Spinner sp)
    {
        Double d=new Double((Integer)sp.getValue());
        return d;
    }
    public String validarPf(JFXPasswordField  jx)
    {
        if(jx.getText().length()>0)
        {
            return jx.getText();
        }
        else
        {
            return "";
        }
    }

    public String validarCbx(JFXComboBox<?> cbxpregunta) {
        return (String) cbxpregunta.getValue();
    }
    
}
