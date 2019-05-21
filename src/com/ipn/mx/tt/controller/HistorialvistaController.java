/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.PrediagnosticoDAO;
import com.ipn.mx.tt.dao.PreguntaContestadaDAO;
import com.ipn.mx.tt.dao.PreguntaDAO;
import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.modelo.PreguntaTabla;
import com.ipn.mx.tt.modelo.TrastornoGuardado;
import com.ipn.mx.tt.modelo.TrastornoTabla;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.mongodb.DBObject;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Temp
 */
public class HistorialvistaController implements Initializable {

    private menuController mc;
    private cargadorVista cv;
    private Double numPaciente;
    private PreguntaContestadaDAO pcd;
    private PrediagnosticoDAO pred;
    private PreguntaDAO pd;
    private List preguntas, trastornos;
    private LinkedList puntajes;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private TableView<TrastornoTabla> tbltrastornos;

    @FXML
    private TableColumn<TrastornoTabla, String> columnaTrastorno;

    @FXML
    private TableColumn<TrastornoTabla, String> columnaHsdq;

    @FXML
    private TableColumn<TrastornoTabla, String> columnaHsdqM;

    @FXML
    private TableColumn<TrastornoTabla, String> columnaS50;

    @FXML
    private TableColumn<TrastornoTabla, String> columnaS50M;

    @FXML
    private TableView<PreguntaTabla> tbltest;

    @FXML
    private TableColumn<PreguntaTabla, String> columnaPregunta;

    @FXML
    private TableColumn<PreguntaTabla, String> columnaRespuesta;

    private ObservableList<PreguntaTabla> ptol;
    private ObservableList<TrastornoTabla> ttol;

    public Double getNumPaciente() {
        return numPaciente;
    }

    public void setNumPaciente(Double numPaciente) {
        this.numPaciente = numPaciente;
    }

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pcd = new PreguntaContestadaDAO();
        pd = new PreguntaDAO();
        pred = new PrediagnosticoDAO();
        pd.conectar();
        pcd.conectar();
        pred.conectar();
        preguntas = pd.getPreguntas(2);
        cv = new cargadorVista();
        ptol = FXCollections.observableArrayList();
        ttol = FXCollections.observableArrayList();
        puntajes = new LinkedList();

        columnaPregunta.setCellValueFactory(cellData -> cellData.getValue().getPregunta());
        columnaRespuesta.setCellValueFactory(cellData -> cellData.getValue().getRespuesta());

        columnaTrastorno.setCellValueFactory(cellData -> cellData.getValue().getTrastorno());
        columnaHsdq.setCellValueFactory(cellData -> cellData.getValue().getHsdq());
        columnaHsdqM.setCellValueFactory(cellData -> cellData.getValue().getHsdqM());
        columnaS50.setCellValueFactory(cellData -> cellData.getValue().getS50());
        columnaS50M.setCellValueFactory(cellData -> cellData.getValue().getS50M());

        tbltest.setItems(ptol);
        tbltrastornos.setItems(ttol);
        trastornos = pred.traerTodo();

    }

    @FXML
    private void volverAMenu(ActionEvent event) {
        PrediagnosticosController pc
                = (PrediagnosticosController) cv.cambiarVista("/Center/Prediagnosticos.fxml", mc.getPanelPrin());
        pc.setMc(mc);
        pc.abrirHistorial();
        pd.desconectar();
        pcd.desconectar();
        pred.desconectar();
    }

    public void cargarTablas() {
        cargarTablaPreguntas();
        System.out.println("dfsdfsdfds" + numPaciente);
        cargarTablaTrastornos();
    }

    private void cargarTablaPreguntas() {
        for (int i = 1; i < 70; i++) {

            PreguntaTabla pt = pcd.getPregunta(i, numPaciente.intValue());
            Pregunta p = new Pregunta((DBObject) preguntas.get(i - 1), 1);
            pt.setPregunta(new SimpleStringProperty(p.getTexto()));
            ptol.add(pt);

        }
    }

    private void cargarTablaTrastornos() {

        if (trastornos.size() > 0) {
            trastornos.forEach((t) -> {
                TrastornoGuardado tg = new TrastornoGuardado((DBObject) t);
                System.out.println(tg.toString());
                if (tg.getNumCuestionario().equals(numPaciente)) {
                    puntajes.add(tg);
                }

            });
            TrastornoGuardado t1 = (TrastornoGuardado) puntajes.get(0);
            TrastornoGuardado t2 = (TrastornoGuardado) puntajes.get(1);
            TrastornoTabla tt = new TrastornoTabla("Insomnio", String.valueOf(t1.getInsomnio()),
                    "29.44", String.valueOf(t2.getInsomnio()), "19");
            TrastornoTabla tt1 = new TrastornoTabla("Ritmo Circadiano", String.valueOf(t1.getRc()),
                    "20.46", String.valueOf(t2.getRc()), "8");
            TrastornoTabla tt2 = new TrastornoTabla("RLS/PLMD", String.valueOf(t1.getRls()),
                    "13.5", String.valueOf(t2.getRls()), "7");
            TrastornoTabla tt3 = new TrastornoTabla("Apnea", String.valueOf(t1.getApnea()),
                    "11.48", String.valueOf(t2.getApnea()), "15");
            TrastornoTabla tt4 = new TrastornoTabla("Hipersomnia", String.valueOf(t1.getHipersomnia()),
                    "14.5", String.valueOf(t2.getHipersomnia()), "0");
            TrastornoTabla tt5 = new TrastornoTabla("Narcolepsia", String.valueOf(t1.getNarcolepsia()),
                    "0", String.valueOf(t2.getNarcolepsia()), "7");
            TrastornoTabla tt6 = new TrastornoTabla("Impacto", String.valueOf(t1.getOtro()),
                    "0", String.valueOf(t2.getOtro()), "15");
            ttol.add(tt);
            ttol.add(tt1);
            ttol.add(tt2);
            ttol.add(tt3);
            ttol.add(tt4);
            ttol.add(tt5);
            ttol.add(tt6);
        }
    }

}
