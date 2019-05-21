package com.ipn.mx.tt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ipn.mx.tt.dao.ConductaDAO;
import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class PacienteNuevo2Controller implements Initializable {

    private boolean comenzarEspecialista;
    private boolean datosPaciente;
    private ConductaDAO cd;
    private Validador v;
    private cargadorVista cv;
    private menuController mc;
    private Paciente paciente;
    private InfoCuestionario ic;
    private Conducta c;
    private int tipoCuestionario;
    @FXML
    private AnchorPane panelP;

    @FXML
    private JFXButton btnPnComenzar;

    @FXML
    private Label lblhorario;

    @FXML
    private JFXRadioButton rbPhorariof;

    @FXML
    private ToggleGroup grupoPregunta;

    @FXML
    private JFXRadioButton rbPhorarioturnos;

    @FXML
    private JFXRadioButton rbPhorarionof;

    @FXML
    private JFXRadioButton rbPlv;

    @FXML
    private ToggleGroup grupoPregunta1;

    @FXML
    private JFXRadioButton rbPls;
    @FXML
    private JFXRadioButton rbPld;

    @FXML
    private Label lbldias;

    @FXML
    private Label lblhoras;

    @FXML
    private Label lblphorass;

    @FXML
    private JFXTextField txtPhorasl;

    @FXML
    private Label lblphorasdd;

    @FXML
    private JFXTextField txtPhorasd;

    @FXML
    private JFXComboBox cbxdiasd;

    @FXML
    private Spinner spnhorastrabajo;

    @FXML
    private Label lbltrabaja;

    @FXML
    private Label lblphoras;

    @FXML
    private Spinner spnhoras;

    @FXML
    private JFXRadioButton rbPtrabajas;

    @FXML
    private ToggleGroup grupoPregunta11;

    @FXML
    private JFXRadioButton rbPtrabajan;

    ObservableList<String> items = FXCollections.observableArrayList("0", "1",
            "2", "3", "4", "5", "6", "7");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarObjetos();
        v = new Validador();
        cbxdiasd.setItems(items);
        cbxdiasd.setValue("0");
        cbxdiasd.setVisible(false);
        lbldias.setVisible(false);
        lblhorario.setVisible(false);
        lblhoras.setVisible(false);
        lblphorasdd.setVisible(false);
        lblphorass.setVisible(false);
        lblphoras.setVisible(false);
        spnhoras.setVisible(false);
        lbltrabaja.setVisible(false);
        rbPhorariof.setVisible(false);
        rbPhorarionof.setVisible(false);
        rbPhorarioturnos.setVisible(false);
        rbPls.setVisible(false);
        rbPlv.setVisible(false);
        rbPld.setVisible(false);
        spnhorastrabajo.setVisible(false);
        txtPhorasd.setVisible(false);
        txtPhorasl.setVisible(false);
        cd = new ConductaDAO();
        cd.conectar();
        cv = new cargadorVista();
        comenzarEspecialista = false;
        // TODO
    }

    public boolean isComenzarEspecialista() {
        return comenzarEspecialista;
    }

    public void setComenzarEspecialista(boolean comenzarEspecialista) {
        this.comenzarEspecialista = comenzarEspecialista;
    }

    public void hacerCuestionario() {

        if (comenzarEspecialista) {
            TestEspecialistaController tec
                    = (TestEspecialistaController) cv.cambiarVista("/Center/TestEspecialista.fxml", mc.getPanelPrin());
            tec.setMc(mc);
            tec.setTipoCuestionario(1);
            tec.iniciarTest();
            tec.setConducta(c);
            tec.setPaciente(paciente);
            if (ic != null) {
                tec.setIc(ic);
            }

        } else {
            TestPacienteController tpc
                    = (TestPacienteController) cv.cambiarVista("/Center/TestPaciente.fxml", mc.getPanelPrin());
            tpc.setMc(mc);
            tpc.setTipoCuestionario(tipoCuestionario);
            tpc.setPaciente(paciente);

            tpc.setDatosPaciente(datosPaciente);
            if (datosPaciente) {
                tpc.setConducta(c);
                tpc.setIc(ic);
                tpc.ponerPaciente();
                tpc.clickComenzar();
            }
            //1 .- Paciente
            //2 .- Acompañante
            //CARGAR VISTA 
        }

    }

    public int getTipoCuestionario() {
        return tipoCuestionario;
    }

    public void setTipoCuestionario(int tipoCuestionario) {
        this.tipoCuestionario = tipoCuestionario;
    }

    public boolean isDatosPaciente() {
        return datosPaciente;
    }

    public void setDatosPaciente(boolean datosPaciente) {
        this.datosPaciente = datosPaciente;
    }

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    public void setPaciente(Paciente p) {
        paciente = p;
    }

    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }

    public void configurarObjetos() {

        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 6, 1);
        spnhoras.setValueFactory(svf);
        spnhorastrabajo.setValueFactory(svf);
    }

    @FXML
    public void trabaja(ActionEvent event) {

        if (rbPtrabajan.isSelected()) {
            cbxdiasd.setVisible(false);
            lbldias.setVisible(false);
            lblhorario.setVisible(false);
            lblhoras.setVisible(false);
            lblphorasdd.setVisible(false);
            lblphorass.setVisible(false);
            lbltrabaja.setVisible(false);
            rbPhorariof.setVisible(false);
            rbPhorarionof.setVisible(false);
            rbPhorarioturnos.setVisible(false);
            rbPls.setVisible(false);
            rbPlv.setVisible(false);
            rbPld.setVisible(false);
            spnhorastrabajo.setVisible(false);
            txtPhorasd.setVisible(false);
            txtPhorasl.setVisible(false);
            lblphoras.setVisible(true);
            spnhoras.setVisible(true);
        } else {
            cbxdiasd.setVisible(true);
            lbldias.setVisible(true);
            lblhorario.setVisible(true);
            lblhoras.setVisible(true);
            lblphorasdd.setVisible(true);
            lblphorass.setVisible(true);
            lbltrabaja.setVisible(true);
            rbPhorariof.setVisible(true);
            rbPhorarionof.setVisible(true);
            rbPhorarioturnos.setVisible(true);
            rbPls.setVisible(true);
            rbPlv.setVisible(true);
            rbPld.setVisible(true);
            spnhorastrabajo.setVisible(true);
            txtPhorasd.setVisible(true);
            txtPhorasl.setVisible(true);
            lblphoras.setVisible(false);
            spnhoras.setVisible(false);
        }
    }

    @FXML
    public void comenzarCuestionario(ActionEvent event) {

        Double horariotrabajo, trabajade, diadescanso, horastrabajo, promediohoraslaboral, promediohorasdescanso, promediohoras;
        //
        promediohoras = v.validarSp(spnhoras);
        promediohorasdescanso = v.validarTFtoDouble(txtPhorasd);
        promediohoraslaboral = v.validarTFtoDouble(txtPhorasl);
        horastrabajo = v.validarSp(spnhorastrabajo);
        diadescanso = 1.0;
        horariotrabajo = 0.0;
        trabajade = 0.0;

        boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false, b7 = false;
        if (rbPtrabajas.isSelected()) {
            if (rbPhorariof.isSelected() || rbPhorarioturnos.isSelected() || rbPhorarionof.isSelected()) {
                if (rbPhorariof.isSelected()) {
                    horariotrabajo = 1.0;
                }
                if (rbPhorarioturnos.isSelected()) {
                    horariotrabajo = 2.0;
                }
                if (rbPhorarionof.isSelected()) {
                    horariotrabajo = 3.0;
                }
                b1 = true;
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Selecciona una horario de trabajo", 0);
            }
            if (rbPld.isSelected() || rbPls.isSelected() || rbPlv.isSelected()) {
                if (rbPld.isSelected()) {
                    trabajade = 3.0;
                }
                if (rbPls.isSelected()) {
                    trabajade = 2.0;
                }
                if (rbPlv.isSelected()) {
                    trabajade = 1.0;
                }
                b2 = true;
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Selecciona un turno ", 0);
            }
            if (txtPhorasl.getText().trim().length() > 0) {
                b3 = true;
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Error en campo Promedio en horas de sueño (Laboral)", 0);
            }

            if (txtPhorasd.getText().trim().length() > 0) {
                b4 = true;
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Error en campo Promedio en horas de sueño (Descanso)", 0);
            }
            if (b1 = b2 = b3 = b4 == true) {
                System.out.println("REQUISITOS COMPLETOS");
                //TRAER DATOS DEL CUESTIONARIO
                c = new Conducta(horariotrabajo, trabajade, diadescanso, promediohoras, horastrabajo, promediohoraslaboral, promediohorasdescanso);
                System.out.println(c.toString());

            }
        } else if (rbPtrabajan.isSelected()) {
            if (true) {
                b4 = true;
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Error en campo Promedio en horas de sueño (Descanso)", 0);
            }
            if (b4) {
                System.out.println("REQUISITOS NO TRABAJA COMPLETOS");
                //CONSTRUCTOR HABITOS NO TRABAJA
                c = new Conducta(promediohoras);
                System.out.println(c.toString());
            }
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "Seleccionar una opción ", 0);
        }

        if (c != null) {
            c.setNumCuestionario(ic.getIdCuestionario());
            cd.insertar(c);
            hacerCuestionario();

        }
    }

    public void imprimirDatos() {
        System.out.println(paciente.toString() + ic.toString() + c.toString());
    }

    void setPanel(AnchorPane panelVista) {
        panelP = panelVista;
    }

}
