/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.CuestionarioAplicadoDAO;
import com.ipn.mx.tt.dao.PacienteDAO;
import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class TestPacienteController implements Initializable {

    private int tipoCuestionario;
    private Conducta c;
    private boolean datosPaciente;
    private Paciente paciente;
    private cargadorVista cv;
    private menuController mc;
    private InfoCuestionario ic;
    private Validador v;
    private CuestionarioAplicadoDAO cad;
    private PacienteDAO pd;
    private Conducta conducta;

    @FXML
    private Label lblPaciente;
    @FXML
    private JFXButton btnTPComenzar;

    @FXML
    private JFXTextField txtTPnumero;
    @FXML
    private AnchorPane panelT;

    public Conducta getConducta() {
        return conducta;
    }

    public void setConducta(Conducta conducta) {
        this.conducta = conducta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getTipoCuestionario() {
        return tipoCuestionario;
    }

    public void setTipoCuestionario(int tipoCuestionario) {
        this.tipoCuestionario = tipoCuestionario;
    }

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    public boolean isDatosPaciente() {
        return datosPaciente;
    }

    public void setDatosPaciente(boolean datosPaciente) {
        this.datosPaciente = datosPaciente;
    }

    public void ponerPaciente() {
        lblPaciente.setText(lblPaciente.getText() + " " + paciente.getNombre());
    }

    public Conducta getC() {
        return c;
    }

    public void setC(Conducta c) {
        this.c = c;
    }

    public InfoCuestionario getIc() {
        return ic;
    }

    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }

    public void clickComenzar() {
        btnTPComenzar.fire();
    }

    @FXML
    public void iniciarTest(ActionEvent event) {

        enterBoton();
    }

    @FXML
    public void enterMenu(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            enterBoton();
        }
    }

    public void enterBoton() {
        if (ic == null) {
            if (!(v.validarTF(txtTPnumero).equals(""))) {

                Double numCuestionario = v.validarTFtoDouble(txtTPnumero);
                Double status = cad.statusCuestionario(numCuestionario);
                //Traer Paciente con curp
                if (cad.cuestionarioExiste(numCuestionario) && status != 2) {
                    //Llenar infoCuestionario
                    ic = cad.traerInfo(numCuestionario);
                    //Traer paciente
                    paciente = pd.buscarPaciente(ic.getPaciente());
                    System.out.println(paciente.toString());
                    System.out.println(ic.toString());

                    if (status == 0) {
                        //CREAR habitos de sueño y mandar  datos obtenidos
                        System.out.println("CUESTIONARIO SIN CONTESTAR");
                        //CUESTIONARIO NUEVO
                        datosPaciente = true;
                        cargarConducta();
                    }
                    if (status == 1) {
                        //CARGAR HABITOS DE SUEÑO Y ORDEN PREGUNTAS...
                        System.out.println("CUESTIONARIO EMPEZADO...");
                        //CUESTIONARIO EMPEZADO
                    }
                } else {
                    CustomMessage cm = new CustomMessage("ERROR", "El numero de cuestionario no es válido, Solicite ayuda", 2);
                }

            } else {
                CustomMessage cm = new CustomMessage("ERROR", "INTRODUCE UN NUMERO", 2);

            }

        } else {
            cargarTest();
        }
    }

    public void cargarTest() {

        TestPacientePreguntasController tppc = (TestPacientePreguntasController) cv.cambiarVista("/Center/TestPacientePreguntas.fxml", mc.getPanelPrin());
        tppc.setMc(mc);
        tppc.setTipoCuestionario(tipoCuestionario);
        tppc.iniciarTest();
        tppc.setPaciente(paciente);
        if (datosPaciente) {
            tppc.setIc(ic);
            tppc.setConducta(conducta);
            tppc.ponerPaciente();
        }
        mc.disableTop();
        cad.desconectar();
        pd.desconectar();
    }

    public void cargarConducta() {
        InstruccionesTestController itc
                = (InstruccionesTestController) cv.cambiarVista("/Center/InstruccionesTest.fxml", mc.getPanelPrin());
        itc.setMc(mc);
        itc.setTipoCuestionario(tipoCuestionario);
        itc.setPaciente(paciente);
        if (datosPaciente) {
            itc.setIc(ic);
        }
        itc.setDatosPaciente(datosPaciente);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cv = new cargadorVista();
        v = new Validador();
        cad = new CuestionarioAplicadoDAO();
        pd = new PacienteDAO();
        cad.conectar();
        pd.conectar();

        // TODO
    }

}
