/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.modelo.PreguntaTabla;
import com.ipn.mx.tt.modelo.Respuesta;
import com.ipn.mx.tt.modelo.SintomaTrastornoTabla;
import com.ipn.mx.tt.modelo.Test;
import com.ipn.mx.tt.modelo.TrastornoTabla;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Temp
 */
public class Prediagnostico2Controller implements Initializable {
    
    private menuController mc;
    private cargadorVista cv;
    private Test test;
    private InfoCuestionario ic;
    private ObservableList<PreguntaTabla> ptol;
    private ObservableList<SintomaTrastornoTabla> sttol;
    private ObservableList<TrastornoTabla> ttol;
    private Paciente paciente;
    private Conducta conducta;
    @FXML
    private AnchorPane panelP;
    @FXML
    private TableColumn<PreguntaTabla, String> columnaPregunta;
    
    @FXML
    private TableColumn<PreguntaTabla, String> columnaRespuesta;
    
    @FXML
    private TableColumn<SintomaTrastornoTabla, String> columnaSintoma;
    
    @FXML
    private TableColumn<SintomaTrastornoTabla, String> columnaTrastorno;
    
    @FXML
    private TableColumn<TrastornoTabla, String> columnaTrastornoR;
    
    @FXML
    private TableColumn<TrastornoTabla, String> columnaHSDQ;
    
    @FXML
    private TableColumn<TrastornoTabla, String> columnaHM;
    
    @FXML
    private TableColumn<TrastornoTabla, String> columnaS50;
    
    @FXML
    private TableColumn<TrastornoTabla, String> columnaS50M;
    @FXML
    private TableView<PreguntaTabla> tbthabitos;
    
    @FXML
    private TableView<SintomaTrastornoTabla> tblsintomas;
    
    @FXML
    private TableView<TrastornoTabla> tbltrastornos;
    
    @FXML
    private JFXTextArea txtcomentarios;
    
    @FXML
    private JFXButton btnrecomendaciones;
    
    @FXML
    private JFXButton btnregresar;
    
    @FXML
    private JFXTextField txttrabaja;
    
    @FXML
    private JFXTextField txtlunesa;
    
    @FXML
    private JFXTextField txthorasps;
    
    @FXML
    private JFXTextField txtdiasd;
    
    @FXML
    private JFXTextField txtdriast;
    
    @FXML
    private JFXTextField txthorassd;
    
    @FXML
    private JFXTextField txthorasst;
    
    @FXML
    private JFXTextField txthorario;
    
    @FXML
    private void regresarDiagnostico(ActionEvent event) {
        PrediagnosticoController pc
                = (PrediagnosticoController) cv.cambiarVista("/Center/Prediagnostico.fxml", mc.getPanelPrin());
        pc.setTest(test);
        pc.setMc(mc);
        pc.setIc(ic);
        pc.setConducta(conducta);
        pc.setPaciente(paciente);
        pc.cargarResultados();
        pc.startgrafica();
    }
    
    @FXML
    private void irRecomendaciones(ActionEvent event) {
        RecomendacionesController rc
                = (RecomendacionesController) cv.cambiarVista("/Center/Recomendaciones.fxml", mc.getPanelPrin());
        rc.setMc(mc);
        rc.setTest(test);
        rc.setIc(ic);
        rc.setConducta(conducta);
        rc.setPaciente(paciente);
        
    }
    
    public InfoCuestionario getIc() {
        return ic;
    }
    
    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }
    
    public menuController getMc() {
        return mc;
    }
    
    public void setMc(menuController mc) {
        this.mc = mc;
    }
    
    public Test getTest() {
        return test;
        // TODO
    }
    
    public void setTest(Test test) {
        this.test = test;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
        
        ptol = FXCollections.observableArrayList();
        sttol = FXCollections.observableArrayList();
        ttol = FXCollections.observableArrayList();
        
        columnaPregunta.setCellValueFactory(cellData -> cellData.getValue().getPregunta());
        columnaRespuesta.setCellValueFactory(cellData -> cellData.getValue().getRespuesta());
        
        columnaTrastorno.setCellValueFactory(cellData -> cellData.getValue().getTrastorno());
        columnaSintoma.setCellValueFactory(cellData -> cellData.getValue().getSintoma());
        
        columnaTrastornoR.setCellValueFactory(cellData -> cellData.getValue().getTrastorno());
        columnaHSDQ.setCellValueFactory(cellData -> cellData.getValue().getHsdq());
        columnaHM.setCellValueFactory(cellData -> cellData.getValue().getHsdqM());
        columnaS50.setCellValueFactory(cellData -> cellData.getValue().getS50());
        columnaS50M.setCellValueFactory(cellData -> cellData.getValue().getS50M());
        
        sttol.add(new SintomaTrastornoTabla("prueba", "prueba"));
        
        tbthabitos.setItems(ptol);
        tbltrastornos.setItems(ttol);
        tblsintomas.setItems(sttol);
    }
    
    public void configurarVista() {
        ponerPreguntasHabitos();
        ponerPuntajes();
    }
    
    public void ponerPreguntasHabitos() {
        for (int i = 62; i < 70; i++) {
            Respuesta r = test.obtenerRespuesta(new Double(i));
            Pregunta p = test.getPregunta(i);
            PreguntaTabla pt = new PreguntaTabla(r);
            pt.setPregunta(new SimpleStringProperty(p.getTexto()));
            
            ptol.add(pt);
        }
        
    }
    
    public void ponerPuntajes() {
        
        ttol.add(traerTrastorno("Insomnio", 1.0));
        ttol.add(traerTrastorno("Ritmo Circadiano", 2.0));
        ttol.add(traerTrastorno("RLS/PLMD", 3.0));
        ttol.add(traerTrastorno("Apnea", 4.0));
        ttol.add(traerTrastorno("Hipersomnia", 5.0));
        ttol.add(traerTrastorno("Narcolepsia", 6.0));
        ttol.add(traerTrastorno("Impacto", 7.0));
        
    }
    
    public TrastornoTabla traerTrastorno(String trastorno, Double numTrastorno) {
        
        TrastornoTabla t = new TrastornoTabla(trastorno,
                formatearObtenido(1, numTrastorno),
                formatearCutoff(numTrastorno, 1.0),
                formatearObtenido(2, numTrastorno),
                formatearCutoff(numTrastorno, 2.0));
        System.out.println(t.toString());
        return t;
    }
    
    public String formatearObtenido(int numCuestionario, Double numTrastorno) {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (test.getNumPreguntas(numTrastorno, new Double(numCuestionario)) == 0) {
            return "0.0";
        } else {
            return df.format(test.getTrastorno(numCuestionario, numTrastorno.intValue()) / test.getNumPreguntas(numTrastorno, new Double(numCuestionario)));
            
        }
    }
    
    public String formatearCutoff(Double numTrastorno, Double numCuestionario) {
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println(test.getCutoff(numTrastorno, numCuestionario));
        return df.format(test.getCutoff(numTrastorno, numCuestionario));
    }
    
}
