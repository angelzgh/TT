/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.PrediagnosticoDAO;
import com.ipn.mx.tt.dao.PreguntaContestadaDAO;
import com.ipn.mx.tt.dao.PreguntaDAO;
import com.ipn.mx.tt.dao.SintomaCuestionarioDAO;
import com.ipn.mx.tt.dao.SintomaDAO;
import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.Pregunta;
import com.ipn.mx.tt.modelo.PreguntaTabla;
import com.ipn.mx.tt.modelo.Respuesta;
import com.ipn.mx.tt.modelo.Sintoma;
import com.ipn.mx.tt.modelo.SintomaTrastornoTabla;
import com.ipn.mx.tt.modelo.Test;
import com.ipn.mx.tt.modelo.TrastornoGuardado;
import com.ipn.mx.tt.modelo.TrastornoTabla;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Temp
 */
public class Prediagnostico2Controller implements Initializable {

    private String[] sintomasDetectados;
    private menuController mc;
    private cargadorVista cv;
    private Test test;
    private InfoCuestionario ic;
    private ObservableList<PreguntaTabla> ptol;
    private ObservableList<TrastornoTabla> ttol;
    private Paciente paciente;
    private Conducta conducta;
    private Boolean testContestado;
    private PreguntaContestadaDAO pcd;
    private PreguntaDAO pd;
    private List trastornos;
    private LinkedList puntajes;
    private PrediagnosticoDAO pred;
    private SintomaDAO sd;
    private SintomaCuestionarioDAO scd;
    TreeItem<String> itemRaiz;

    TreeItem<String> itemInsomnio, itemRc, itemRls, itemApnea, itemHipersomnia, itemNarcolepsia;

    @FXML
    private TreeTableView<String> trastornoView;

    @FXML
    private TreeTableColumn<String, String> columnTrastorno;
    @FXML
    private AnchorPane panelP;
    @FXML
    private TableColumn<PreguntaTabla, String> columnaPregunta;

    @FXML
    private TableColumn<PreguntaTabla, String> columnaRespuesta;

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
    private TableColumn<TrastornoTabla, Boolean> columnaDetectado;
    @FXML
    private TableView<PreguntaTabla> tbthabitos;

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
        pc.setSintomasDetectados(sintomasDetectados);
        pc.habilitarBotonGuardar();

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
        rc.setSintomasDetectados(sintomasDetectados);
        rc.ponerRecomendaciones();
    }

    public String[] getSintomasDetectados() {
        return sintomasDetectados;
    }

    public void setSintomasDetectados(String[] sintomasDetectados) {
        this.sintomasDetectados = sintomasDetectados;
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
        pcd = new PreguntaContestadaDAO();
        pd = new PreguntaDAO();
        pred = new PrediagnosticoDAO();
        sd = new SintomaDAO();
        scd = new SintomaCuestionarioDAO();
        scd.conectar();
        sd.conectar();
        pd.conectar();
        pcd.conectar();
        pred.conectar();
        ptol = FXCollections.observableArrayList();
        ttol = FXCollections.observableArrayList();
        puntajes = new LinkedList();
        columnaPregunta.setCellValueFactory(cellData -> cellData.getValue().getPregunta());
        columnaRespuesta.setCellValueFactory(cellData -> cellData.getValue().getRespuesta());

        columnaTrastornoR.setCellValueFactory(cellData -> cellData.getValue().getTrastorno());
        columnaHSDQ.setCellValueFactory(cellData -> cellData.getValue().getHsdq());
        columnaHM.setCellValueFactory(cellData -> cellData.getValue().getHsdqM());
        columnaS50.setCellValueFactory(cellData -> cellData.getValue().getS50());
        columnaS50M.setCellValueFactory(cellData -> cellData.getValue().getS50M());
        columnaDetectado.setCellValueFactory(cellData -> cellData.getValue().getTiene());

        tbltrastornos.setRowFactory(tv -> new TableRow<TrastornoTabla>() {
            @Override
            public void updateItem(TrastornoTabla item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.getTiene().get()) {
                    setStyle("-fx-background-color: #42B855 ;");
                } else {
                    setStyle("");
                }
            }
        });
        columnTrastorno.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) -> {
            return new SimpleStringProperty(param.getValue().getValue());
        });
        tbthabitos.setItems(ptol);
        tbltrastornos.setItems(ttol);

        itemRaiz = new TreeItem<>("Trastornos");

        trastornoView.setRoot(itemRaiz);

        testContestado = true;
        itemInsomnio = new TreeItem<>("Insomnio");
        itemRc = new TreeItem<>("RC");
        itemRls = new TreeItem<>("RLS");
        itemApnea = new TreeItem<>("Apnea");
        itemHipersomnia = new TreeItem<>("Hipersomnia");
        itemNarcolepsia = new TreeItem<>("Narcolepsia");
        trastornoView.getRoot().getChildren().addAll(itemInsomnio);
        trastornoView.getRoot().getChildren().addAll(itemRc);
        trastornoView.getRoot().getChildren().addAll(itemRls);
        trastornoView.getRoot().getChildren().addAll(itemApnea);
        trastornoView.getRoot().getChildren().addAll(itemHipersomnia);
        trastornoView.getRoot().getChildren().addAll(itemNarcolepsia);

    }

    public Boolean getTestContestado() {
        return testContestado;
    }

    public void setTestContestado(Boolean testContestado) {
        this.testContestado = testContestado;
    }

    public void configurarVista() {
        ponerPreguntasHabitos();
        ponerPuntajes();
    }

    public void ponerPreguntasHabitos() {

        if (test != null) {
            for (int i = 62; i < 70; i++) {
                Respuesta r = test.obtenerRespuesta(new Double(i));
                Pregunta p = test.getPregunta(i);
                PreguntaTabla pt = new PreguntaTabla(r);
                pt.setPregunta(new SimpleStringProperty(p.getTexto()));

                ptol.add(pt);
            }

        } else {
            for (int i = 62; i < 70; i++) {
                PreguntaTabla pt = pcd.getPregunta(i, ic.getIdCuestionario().intValue());
                Pregunta p = pd.getPregunta(i, 1);
                pt.setPregunta(new SimpleStringProperty(p.getTexto()));

                ptol.add(pt);
            }
        }
    }

    public void ponerPuntajes() {

        if (test != null) {
            ttol.add(traerTrastorno("Insomnio", 1.0));
            ttol.add(traerTrastorno("Ritmo Circadiano", 2.0));
            ttol.add(traerTrastorno("RLS/PLMD", 3.0));
            ttol.add(traerTrastorno("Apnea", 4.0));
            ttol.add(traerTrastorno("Hipersomnia", 5.0));
            ttol.add(traerTrastorno("Narcolepsia", 6.0));
            ttol.add(traerTrastorno("Impacto", 7.0));
        } else {

            if (trastornos.size() > 0) {
                trastornos.forEach((t) -> {
                    TrastornoGuardado tg = new TrastornoGuardado((DBObject) t);
                    System.out.println(tg.toString());
                    if (tg.getNumCuestionario().equals(ic.getIdCuestionario())) {
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
            return df.format(test.getTrastorno(numCuestionario, numTrastorno.intValue())
                    / test.getNumPreguntas(numTrastorno, new Double(numCuestionario)));

        }
    }

    public String formatearCutoff(Double numTrastorno, Double numCuestionario) {
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println(test.getCutoff(numTrastorno, numCuestionario));
        return df.format(test.getCutoff(numTrastorno, numCuestionario));
    }

    public void ponerConducta() {

        System.out.println(conducta.toString());
        if (!conducta.isTrabaja()) {
            txttrabaja.setText("NO");
            txthorasps.setText("" + conducta.getPromedioHoras());
            txtdiasd.setText("-");
            txthorassd.setText("-");
            txthorasst.setText("-");
            txtdriast.setText("-");
            txtlunesa.setText("Domingo");
            txtdriast.setText("-");
        } else {

            txttrabaja.setText("SI");
            String diatrabaja = "";
            String horariot = "";
            if (conducta.getJornadaLaboral() == 3) {
                diatrabaja = "Domingo";
            } else if (conducta.getJornadaLaboral() == 2) {
                diatrabaja = "Sabado";
            } else if (conducta.getJornadaLaboral() == 1) {
                diatrabaja = "Viernes";
            }
            txtlunesa.setText(diatrabaja);
            if (conducta.getHorarioTrabajo() == 1) {
                horariot = "Fijo";
            } else if (conducta.getHorarioTrabajo() == 2) {
                horariot = "Por turnos";
            } else if (conducta.getHorarioTrabajo() == 3) {
                horariot = "Sin horario fijo";
            }
            txthorario.setText(horariot);
            String hpdiasd = Double.toString(conducta.getPromedioHorasDescanso());
            String hpdiast = Double.toString(conducta.getPromedioHorasLaborales());
            String diasd = Double.toString(conducta.getDiasDeDescanso());
            String hpsueño = Double.toString(conducta.getPromedioHoras());
            txtdiasd.setText(diasd);
            txthorassd.setText(hpdiasd);
            txthorasst.setText(hpdiast);
            txthorasps.setVisible(false);
            txtdriast.setText("" + conducta.getHorasDeTrabajo());

        }

    }

    public void configurarTrastorno() {
        trastornos = pred.traerTrastornos(ic.getIdCuestionario());
    }

    public void ponerTrastornosSintomas() {

        if (sintomasDetectados != null) {

            ponerSintomasView(sintomasDetectados[1], itemInsomnio);
            ponerSintomasView(sintomasDetectados[2], itemRc);
            ponerSintomasView(sintomasDetectados[3], itemRls);
            ponerSintomasView(sintomasDetectados[4], itemApnea);
            ponerSintomasView(sintomasDetectados[5], itemHipersomnia);
            ponerSintomasView(sintomasDetectados[6], itemNarcolepsia);

        } else {
            configurarSintomasDetectados();
            ponerTrastornosSintomas();
        }
    }

    public void ponerSintomasView(String numSintoma, TreeItem ti) {
        
        if(numSintoma.contains(","))
        {
                    String[] sintomas = numSintoma.split(",");
        for (String sintoma : sintomas) {
            Sintoma s = sd.getSintoma(Double.valueOf(sintoma));
            TreeItem<String> itemSintoma = new TreeItem<>(s.getNombre());
            ti.getChildren().addAll(itemSintoma);

        }
        }
    }

    public void configurarSintomasDetectados() {
        Double numC = ic.getIdCuestionario();
        sintomasDetectados = new String[10];
        for (int i = 1; i < 10; i++) {
            sintomasDetectados[i] = " ";
        }
        sintomasDetectados[1] = scd.traerSintoma(numC, 1.0).getSintomas();
        sintomasDetectados[2] = scd.traerSintoma(numC, 2.0).getSintomas();
        sintomasDetectados[3] = scd.traerSintoma(numC, 3.0).getSintomas();
        sintomasDetectados[4] = scd.traerSintoma(numC, 4.0).getSintomas();
        sintomasDetectados[5] = scd.traerSintoma(numC, 5.0).getSintomas();
        sintomasDetectados[6] = scd.traerSintoma(numC, 6.0).getSintomas();

    }

}
