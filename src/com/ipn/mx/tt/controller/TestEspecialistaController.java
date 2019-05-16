/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.modelo.SintomaPregunta;
import com.ipn.mx.tt.modelo.Test;
import com.ipn.mx.tt.modelo.TrastornoSintoma;
import com.ipn.mx.tt.util.ThreadPregunta;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class TestEspecialistaController implements Initializable {

    private cargadorVista cv;
    private int tipoCuestionario;
    private int instrumento, pregunta, puntaje;
    private LinkedList sintoma, trastorno, preguntas;
    private menuController mc;
    private Test test;
    private int contadorPregunta;
    private InfoCuestionario ic;

    public InfoCuestionario getIc() {
        return ic;
    }

    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }

    @FXML
    private TreeTableView<String> tablaRespuesta;

    @FXML
    private TreeTableColumn<String, String> columnaRespuesta;

    @FXML
    private BorderPane panelRight;

    @FXML
    private ProgressBar pbTEprogeso;

    @FXML
    private JFXRadioButton rbtnTEnunca;

    @FXML
    private ToggleGroup grupoPregunta;

    @FXML
    private JFXRadioButton rbtnTEoca;

    @FXML
    private JFXRadioButton rbtnTEavc;

    @FXML
    private JFXRadioButton rbtnTEcs;

    @FXML
    private JFXRadioButton rbtnTEsiempre;

    @FXML
    private JFXTextArea txtpregunta;

    @FXML
    private ImageView imgRegresar;

    @FXML
    private JFXButton regresar;

    @FXML
    private Label lblProgress;

    @FXML
    private JFXButton btnFinalizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        rbtnTEnunca.setOnAction((event) -> {
            contestarPregunta(1);
        });
        rbtnTEoca.setOnAction((event) -> {
            contestarPregunta(2);
        });
        rbtnTEavc.setOnAction((event) -> {
            contestarPregunta(3);
        });
        rbtnTEcs.setOnAction((event) -> {
            contestarPregunta(4);
        });
        rbtnTEsiempre.setOnAction((event) -> {
            contestarPregunta(5);
        });
        contadorPregunta = 1;

        btnFinalizar.setVisible(false);
        TreeItem<String> itemRaiz = new TreeItem<>("RESPUESTAS");
        itemRaiz.setExpanded(true);
        tablaRespuesta.setRoot(itemRaiz);

        columnaRespuesta.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) -> {
            return new SimpleStringProperty(param.getValue().getValue());
        });
    }

    @FXML
    private void mostrarPrediagnostico(ActionEvent ae) {
        cv = new cargadorVista();
        PrediagnosticoController pc = (PrediagnosticoController) cv.cambiarVista("/Center/Prediagnostico.fxml", mc.getPanelPrin());
        test.getFinCuestionario();
        test.getDuracion();
        pc.setTest(test);
        pc.setMc(mc);
        pc.setIc(ic);
        pc.habilitarBotonGuardar();

        pc.cargarResultados();
        pc.startgrafica();

    }

    public void cargarPregunta(Pregunta p) {

        if (p.getId() > 0 && p.getId() != 99) {
            txtpregunta.setText("(" + p.getId() + ")" + contadorPregunta + ".-" + p.getTexto());
            pregunta = p.getId();
            instrumento = test.getTipoCuestionario(pregunta);
            // int tipo=id.tipoCuestionario(pregunta);
            if (instrumento == 1) {
                rbtnTEavc.setText("Aplicable a veces");
                rbtnTEcs.setText("Generalmente aplicable");
                rbtnTEnunca.setText("No aplicable en lo absoluto");
                rbtnTEoca.setText("Generalmente no aplicable");
                rbtnTEsiempre.setText("Totalmente aplicable");
                rbtnTEavc.setVisible(true);
            } else {

                rbtnTEcs.setText("Bastante");
                rbtnTEnunca.setText("Nada");
                rbtnTEoca.setText("Algo");
                rbtnTEsiempre.setText("Mucho");
                rbtnTEavc.setVisible(false);
            }
            contadorPregunta++;
        }
    }

    public int getTipoCuestionario() {
        return tipoCuestionario;
    }

    public void setTipoCuestionario(int tipoCuestionario) {
        this.tipoCuestionario = tipoCuestionario;
    }

    void setMc(menuController c) {
        mc = c;
    }

    void iniciarTest() {
        sintoma = new LinkedList();
        trastorno = new LinkedList();
        test = new Test(tipoCuestionario);
        cargarPregunta(test.getPregunta(test.getSigPregunta()));
        //pbTPprogeso.setProgress(0.001);
        pbTEprogeso.setProgress(0.001);
    }

    public void limpiarVista() {
        rbtnTEavc.setSelected(false);
        rbtnTEnunca.setSelected(false);
        rbtnTEcs.setSelected(false);
        rbtnTEoca.setSelected(false);
        rbtnTEsiempre.setSelected(false);
    }

    void contestarPregunta(int valor) {

        while (!test.cuestionarioCompletado()) {
            ThreadPregunta tp = new ThreadPregunta(3, rbtnTEcs, rbtnTEavc, rbtnTEnunca, rbtnTEoca, rbtnTEsiempre, regresar);
            //tp.runClock();
            //AGREGAR A LA VISTA

            registroPregunta(txtpregunta.getText(), getRespuesta(valor));

            limpiarVista();
            //SUMAR AL CUESTIONARIO 
            sumarATrastorno();
            //TRAER NUEVA PREGUNTA

            cargarPregunta(test.getPregunta(test.getSigPregunta()));
        }
        if (test.cuestionarioCompletado()) {
            lblProgress.setText("100%");
            pbTEprogeso.setProgress(1.0);
            test.getFinCuestionario();
            test.getDuracion();
            txtpregunta.setText("FIN DEL CUESTIONARIO");
            rbtnTEavc.setDisable(true);
            rbtnTEcs.setDisable(true);
            rbtnTEnunca.setDisable(true);
            rbtnTEoca.setDisable(true);
            rbtnTEsiempre.setDisable(true);
            btnFinalizar.setVisible(true);
        }
    }

    String getRespuesta(int valor) {
        String resp;
        switch (valor) {
            case 1:
                resp = rbtnTEnunca.getText();
                puntaje = 1;
                break;
            case 2:
                resp = rbtnTEoca.getText();
                puntaje = 2;
                break;
            case 3:
                resp = rbtnTEavc.getText();
                puntaje = 3;
                break;
            case 4:
                resp = rbtnTEcs.getText();
                if (instrumento == 1) {
                    puntaje = 4;
                } else {
                    puntaje = 3;
                }
                break;
            case 5:
                resp = rbtnTEsiempre.getText();
                if (instrumento == 1) {
                    puntaje = 5;
                } else {
                    puntaje = 4;
                }
                break;

            default:
                resp = "";

        }
        return resp;
    }

    public void sumarAPregunta(int preguntaContestada, Double valor) {
        //Obtener instrumendo de pregunta
        int instrumentoC = test.getTipoCuestionario(preguntaContestada);
        //Obtener sintomas
        sintoma = test.getSintoma(preguntaContestada);
        //for cada sintoma encontrado
        sintoma.forEach((sintomaLoop) -> {
            SintomaPregunta sp = (SintomaPregunta) sintomaLoop;
            int numeroSintoma = sp.getSintoma();
            trastorno = test.getTrastorno(numeroSintoma);
            trastorno.forEach((trastornoLoop) -> {
                TrastornoSintoma ts = (TrastornoSintoma) trastornoLoop;

                if (test.banderaLevantada(ts.getTrastorno())) {
                    //System.out.println("YA SUMADO:" + preguntaC);
                } else {
                    if ((!test.respuestaContestada(preguntaContestada))
                            || ((test.respuestaContestada(preguntaContestada)) && (preguntaContestada == 16 || preguntaContestada == 17))) {
                        test.levantarBandera(ts.getTrastorno());
                        System.out.println("/Instrumento:/" + instrumentoC + "/Sintoma/" + numeroSintoma + "/Trastorno/" + ts.getTrastorno()
                                + "/Pregunta:/" + preguntaContestada + "/Valor:/" + valor);
                        test.calificarPregunta(instrumentoC, ts.getTrastorno(), valor);
                        test.agregarRespuesta(preguntaContestada, valor.intValue());
                    }
                }
            });
        });
        test.reiniciarBanderas();
    }

    public void registroPregunta(String t, String r) {

        TreeItem<String> itemPregunta = new TreeItem<>(t);
        TreeItem<String> itemRespuesta = new TreeItem<>(r);
        itemPregunta.getChildren().addAll(itemRespuesta);
        itemPregunta.setExpanded(false);
        tablaRespuesta.getRoot().getChildren().addAll(itemPregunta);

    }

    private void sumarATrastorno() {

        sumarAPregunta(pregunta, new Double(puntaje));
        preguntas = test.obtenerEquivalente(pregunta);
        if (preguntas.size() > 0) {
            System.out.println("EQUIVALENCIA ENCONTRADA: " + preguntas.size());
        }
        preguntas.forEach((preguntaLoop) -> {
            int preguntaC = (int) preguntaLoop;

            sumarAPregunta(preguntaC, test.puntajeEquivalente(test.getTipoCuestionario(pregunta),
                    test.getTipoCuestionario(preguntaC), new Double(puntaje)));
        });
        test.sumarContadorPregunta();
        aumentarProgreso();
        //System.out.println(preguntas.size());

    }

    @FXML
    private void regresarPregunta(ActionEvent ae) {

//        if (contadorPreguntas > 1) {
//
//            cargarPregunta(test.getPregunta(contadorPreguntas - 1));
//            restarATrastorno();
//        } else {
//            CustomMessage cm = new CustomMessage("ERROR", "No hay pregunta Anterior...", 2);
//
//        }
    }

    public void restarATrastorno() {
//        sintoma = spd.buscarSintoma(pregunta);
        //      trastorno = tsd.buscarTrastorno(sintoma);
//        cuestionario.quitarPregunta(instrumento, trastorno, puntaje);
        //      contadorPreguntas--;
    }

    @FXML
    void regresarImg(MouseEvent event) {
//
//        if (contadorPreguntas > 1) {
//
//            cargarPregunta(test.getPregunta(contadorPreguntas - 1));
//            restarATrastorno();
//        } else {
//            CustomMessage cm = new CustomMessage("ERROR", "No hay pregunta Anterior...", 2);
//
//        }
    }

    private void aumentarProgreso() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        Double avance = test.getContadorPreguntas() * 0.01449;
        pbTEprogeso.setProgress(avance);
        Double porcentaje = avance * 100;
        lblProgress.setText(df2.format(porcentaje) + "%");
    }
}
