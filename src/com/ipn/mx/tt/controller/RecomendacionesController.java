/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.Test;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class RecomendacionesController implements Initializable {

    private menuController mc;
    private cargadorVista cv;
    private Test test;
    private InfoCuestionario ic;
    private Paciente paciente;
    private Conducta conducta;

    public menuController getMc() {
        return mc;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Conducta getConducta() {
        return conducta;
    }

    public void setConducta(Conducta conducta) {
        this.conducta = conducta;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public InfoCuestionario getIc() {
        return ic;
    }

    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }

    @FXML
    private JFXButton btnVolver;

    @FXML
    private TableView<?> tblRtrastornos;

    @FXML
    private TableView<?> tblPtrastornos1;

    @FXML
    private TableColumn<?, ?> tblRrecomendaciones;

    @FXML
    private JFXButton btnRimprimir;

    @FXML
    private JFXButton btnRenviarcorreo;

    @FXML
    private JFXButton btnRcerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
    }

    @FXML
    void enviarEmailRecomendacion(ActionEvent event) {

    }

    @FXML
    void imprimirRecomendacion(ActionEvent event) {

    }
    @FXML
 void PDF(ActionEvent event) throws JRException {

String master=System.getProperty("user.dir")+"\\src\\Center\\Reporte.jasper";
System.out.println(master);
HashMap parametros=new HashMap();

parametros.put("nombre","");
parametros.put("apellidos","");
parametros.put("edad","23");
parametros.put("sexo","");
parametros.put("trabaja","Si");
parametros.put("diatrabaja","Viernes");
parametros.put("diast","5");
parametros.put("hpdiasd","8");
parametros.put("hpdiast","6");
parametros.put("diasd","2");
parametros.put("hpsueño","7");
JasperPrint informe=JasperFillManager.fillReport(master, parametros, new JREmptyDataSource());
JasperViewer.viewReport(informe,false);
    
}

    @FXML
    void regresarPrediagnostico(ActionEvent event) {
        Prediagnostico2Controller pc
                = (Prediagnostico2Controller) cv.cambiarVista("/Center/Prediagnostico2.fxml", mc.getPanelPrin());
        pc.setTest(test);
        pc.setIc(ic);
        pc.setMc(mc);
        pc.setConducta(conducta);
        pc.setPaciente(paciente);
        pc.configurarVista();
    }

    @FXML
    void verRecomendacion(ActionEvent event) {

    }
}
