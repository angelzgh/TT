/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.CuestionarioAplicadoDAO;
import com.ipn.mx.tt.dao.PacienteDAO;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.PacienteTabla;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class PacienteConRegistroController implements Initializable {

    private Validador v;
    private cargadorVista cv;
    private menuController c;
    private CuestionarioAplicadoDAO cad;
    private PacienteDAO pd;
    private boolean directoEspecialista;

    public menuController getC() {
        return c;
    }

    public void setC(menuController c) {
        this.c = c;
    }

    private List pacientes;

    @FXML
    private AnchorPane panelP;

    @FXML
    private JFXButton btnPriniciar;

    @FXML
    private JFXTextField txtPrnombre;

    @FXML
    private TableView<PacienteTabla> tabla;
    @FXML
    private TableColumn<PacienteTabla, String> columnaCURP;

    @FXML
    private TableColumn<PacienteTabla, String> columnaNombre;

    @FXML
    private TableColumn<PacienteTabla, String> test;
    @FXML
    private TableColumn<PacienteTabla, String> columnaEdad;

    @FXML
    private JFXButton btnPpregiagnostico;
    @FXML
    private JFXButton volverPacienteR;

    private ObservableList<PacienteTabla> ol;

    @FXML
    void buscarPaciente(KeyEvent event) {
        String curp = toUpperCase(txtPrnombre.getText());

        String busqueda = v.validars(curp);
        if (pacientes.size() > 0) {
            if (busqueda.length() > 2) {
                ol.clear();
                LinkedList ls = new LinkedList();
                pacientes.forEach((l)
                        -> {
                    Paciente p = new Paciente((DBObject) l);
                    if (p.getCURP().contains(curp)) {
                        ls.add(p);
                    }
                });
                ls.forEach((termino) -> {
                    //AÑADIR A LA VISTAs
                    PacienteTabla pacienteTabla = new PacienteTabla((Paciente) termino);
                    ol.add(pacienteTabla);
                });
            } else {
                ol.clear();
            }
        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        v = new Validador();
        cv = new cargadorVista();
        pacientes = new LinkedList();
        pd = new PacienteDAO();
        cad = new CuestionarioAplicadoDAO();
        cad.conectar();
        pd.conectar();
        pacientes = pd.buscarSimilar();

        System.out.println("PACIENTES:" + pacientes.size() + pacientes.toString());
        ol = FXCollections.observableArrayList();

        columnaCURP.setCellValueFactory(cellData -> cellData.getValue().getCURP());
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        columnaEdad.setCellValueFactory(cellData -> cellData.getValue().getEdad());
        tabla.setItems(ol);
    }

    @FXML
    void iniciarTest(ActionEvent event) {
        if (tabla.getSelectionModel().getSelectedItem() != null) {
            PacienteTabla pt = tabla.getSelectionModel().getSelectedItem();
            pt.setOrigen(getPaciente(pt.getCURP().getValue()));

            ComenzarTestController ctc = (ComenzarTestController) cv.cambiarVista("/Center/ComenzarTest.fxml", c.getPanelPrin());
            ctc.setC(c);
            ctc.setPaciente(pt.getOrigen());
            ctc.setDatosPaciente(true);
            ctc.ocultarEspecialista();
            if (!cad.cuestionarioPrevio(pt.getCURP().getValue())) {
                System.out.println("dsfdsfsdfdsfd");
                InfoCuestionario ic = new InfoCuestionario(cad.buscarSiguiente() + 1, 0.0, pt.getCURP().get(), c.getUsuario().getId(),c.getDia());
                cad.insertarInfoCuestionario(ic);
                ctc.setIc(ic);
            } else {
                System.out.println("Cuestionario previo numCuestionario...");
                Double statusCuestionario = cad.cuestionarioPrevioStatus(pt.getCURP().getValue());
                InfoCuestionario ic;
                switch (statusCuestionario.intValue()) {
                    case 0:
                        //CARGAR INFORMACIÓN DE CUESTIONARIO Y APLICAR
                        ic = new InfoCuestionario(cad.numCuestionario(pt.getCURP().get()), 0.0, pt.getCURP().get(), c.getUsuario().getId(),c.getDia());
                        ctc.setIc(ic);
                        break;
                    case 1:
                        //BUSCAR ORDEN DE CUESTIONARIO Y PREGUNTAS CONTESTADAS
                        break;
                    case 2:
                        ic = new InfoCuestionario(cad.buscarSiguiente() + 1, 0.0, pt.getCURP().get(), c.getUsuario().getId(),c.getDia());
                        cad.insertarInfoCuestionario(ic);
                        ctc.setIc(ic);
                        break;
                    //CREAR NUEVA INFO DE CUESTIONARIO Y APLICAR
                }
            }
            if (directoEspecialista) {
                ctc.setEspecialistaDirecto(true);
                ctc.clicEspecialista();
            }
        } else {
            CustomMessage cm = new CustomMessage("Advertencia", "Seleccione un paciente", 0);
        }

    }

    public Paciente getPaciente(String curp) {
        Paciente p = new Paciente();

        for (int i = 0; i < pacientes.size(); i++) {
            p = new Paciente((DBObject) pacientes.get(i));
            if (p.getCURP().equals(curp)) {
                break;
            }
        }
        return p;

    }

    public void directoEspecialista() {
        directoEspecialista = true;
    }

    @FXML
    void verPrediagnostico(ActionEvent event) {
        if (tabla.getSelectionModel().getSelectedItem() != null) {
            PacienteTabla pt = tabla.getSelectionModel().getSelectedItem();
            pt.setOrigen(getPaciente(pt.getCURP().getValue()));

            PacienteNuevoController pnc = (PacienteNuevoController) cv.cambiarVista("/Center/PacienteNuevo.fxml", panelP);
            pnc.setC(c);
            pnc.ponerPaciente(pt.getOrigen());
            pnc.funcionActualizar();
//        pnc.setBorder();
        } else {
            CustomMessage cm = new CustomMessage("Advertencia", "Seleccione un paciente", 0);
        }

    }

}
