/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axel Reyes
 */
public class TrastornoTabla {
    private StringProperty Trastorno;
    private StringProperty Hsdq;
    private StringProperty HsdqM;
    private StringProperty s50;
    private StringProperty s50M;
    private BooleanProperty tiene;

    public TrastornoTabla(String Trastorno, String Hsdq, String HsdqM, String s50, String s50M) {
        this.Trastorno = new SimpleStringProperty(Trastorno);
        this.Hsdq = new SimpleStringProperty(Hsdq);
        this.HsdqM = new SimpleStringProperty(HsdqM);
        this.s50 = new SimpleStringProperty(s50);
        this.s50M = new SimpleStringProperty(s50M);
    }

    
    public StringProperty getTrastorno() {
        return Trastorno;
    }

    public void setTrastorno(StringProperty Trastorno) {
        this.Trastorno = Trastorno;
    }

    public StringProperty getHsdq() {
        return Hsdq;
    }

    public void setHsdq(StringProperty Hsdq) {
        this.Hsdq = Hsdq;
    }

    public StringProperty getHsdqM() {
        return HsdqM;
    }

    public void setHsdqM(StringProperty HsdqM) {
        this.HsdqM = HsdqM;
    }

    public StringProperty getS50() {
        return s50;
    }

    public void setS50(StringProperty s50) {
        this.s50 = s50;
    }

    public StringProperty getS50M() {
        return s50M;
    }

    public void setS50M(StringProperty s50M) {
        this.s50M = s50M;
    }

    public String getTrastornoString() {
        return Trastorno.getValue();
    }

    public void setTrastorno(String Trastorno) {
        this.Trastorno = new SimpleStringProperty(Trastorno);
    }

    public String getHsdqString() {
        return Hsdq.getValue();
    }

    public void setHsdq(String Hsdq) {
        this.Hsdq = new SimpleStringProperty(Hsdq);
    }

    public String getHsdqMString() {
        return HsdqM.getValue();
    }

    public void setHsdqM(String HsdqM) {
        this.HsdqM = new SimpleStringProperty(HsdqM);
    }

    public String getS50String() {
        return s50.getValue();
    }

    public void setS50(String s50) {
        this.s50 = new SimpleStringProperty(s50);
    }

    public String getS50MString() {
        return s50M.getValue();
    }

    public void setS50M(String s50M) {
        this.s50M = new SimpleStringProperty(s50M);
    }

    public BooleanProperty getTiene() {
        return tiene;
    }

    public void setTiene(BooleanProperty tiene) {
        this.tiene = tiene;
    }

    
    @Override
    public String toString() {
        return "TrastornoTabla{" + "Trastorno=" + Trastorno + ", Hsdq=" + Hsdq + ", HsdqM=" + HsdqM + ", s50=" + s50 + ", s50M=" + s50M + '}';
    }
    
}
