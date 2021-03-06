/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.ConductaDAO;
import com.ipn.mx.tt.dao.CuestionarioAplicadoDAO;
import com.ipn.mx.tt.dao.PacienteDAO;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.PrediagnosticoTabla;
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class HistorialController implements Initializable {

    private cargadorVista cv;
    private menuController mc;
    private CuestionarioAplicadoDAO cad;
    private ConductaDAO cd;
    private PacienteDAO pd;
    private List cuestionarios;
    private ObservableList<PrediagnosticoTabla> ptol;
    private Validador v;
    @FXML
    private AnchorPane btnPver;

    @FXML
    private JFXTextField txtPnombre;

    @FXML
    private TableView<PrediagnosticoTabla> tblPpre;
    @FXML
    private TableColumn<PrediagnosticoTabla, String> columnCURP;

    @FXML
    private TableColumn<PrediagnosticoTabla, String> columnEdad;

    @FXML
    private TableColumn<PrediagnosticoTabla, String> columnFecha;

    @FXML
    private TableColumn<PrediagnosticoTabla, String> columnTest;
    @FXML
    private JFXButton btnPver1;

    public menuController getMc() {
        return mc;
    }

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cv = new cargadorVista();
        cad = new CuestionarioAplicadoDAO();
        cd = new ConductaDAO();
        pd = new PacienteDAO();
        cd.conectar();
        pd.conectar();
        cad.conectar();
        ptol = FXCollections.observableArrayList();
        columnCURP.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        columnTest.setCellValueFactory(cellData -> cellData.getValue().getNumeroCuestionario());
        columnFecha.setCellValueFactory(cellData -> cellData.getValue().getFecha());
        columnEdad.setCellValueFactory(cellData -> cellData.getValue().getEdad());
        v = new Validador();
        tblPpre.setItems(ptol);

        cuestionarios = cad.traerInformacion();
    }

    @FXML
    void buscarPaciente(KeyEvent event) {
        String curp = toUpperCase(txtPnombre.getText());

        String busqueda = v.validars(curp);
        if (cuestionarios.size() > 0) {
            if (busqueda.length() > 3) {
                ptol.clear();
                LinkedList ls = new LinkedList();
                cuestionarios.forEach((cuestionario)
                        -> {

                    PrediagnosticoTabla pt = new PrediagnosticoTabla((DBObject) cuestionario);
                    if (pt.getStatus().getValue().equals("2.0") && pt.getNombre().getValue().contains(curp)) {
                        String fechi = pd.buscarFecha(pt.getNumeroCuestionario().getValue());
                        System.out.println(fechi);
                        String[] fecha = fechi.split("-");
                        int x = 0;

                        if (fecha.length > 0) {
                            x = 2019 - Integer.parseInt(fecha[0]);

                        } else {
                            x = 60;
                        }
                        pt.setEdad(new SimpleStringProperty(String.valueOf(x)));

                        ptol.add(pt);
                    }

                });
            } else if(busqueda.length() ==0){
                ptol.clear();
                cargarDatos();
            }
        } else {

        }
    }

    @FXML
    private void verPrediagnostico(ActionEvent event) {
        if (tblPpre.getSelectionModel().getSelectedItem() != null) {
            PrediagnosticoController pc = (PrediagnosticoController) cv.cambiarVista("/Center/Prediagnostico.fxml", mc.getPanelPrin());
            InfoCuestionario ic
                    = cad.traerInfo(Double.valueOf(tblPpre.getSelectionModel().getSelectedItem().getNumeroCuestionario().get()));
            pc.setMc(mc);

            pc.setConducta(cd.buscarConducta(ic.getIdCuestionario()));
            pc.setPaciente(pd.buscarPaciente(ic.getPaciente()));
            pc.setIc(ic);
            //System.out.println(ic.toString());
            pc.cargarResultados();
            pc.setTestContestado(false);
            pc.startgrafica();
        cd.desconectar();
        pd.desconectar();
        cad.desconectar();
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "Seleccione un Prediagnostico.", 2);
        }

    }

    @FXML
    private void verTest(ActionEvent event) {
        if (tblPpre.getSelectionModel().getSelectedItem() != null) {

            HistorialvistaController hc = (HistorialvistaController) cv.cambiarVista("/Center/Historialvista.fxml", mc.getPanelPrin());
            hc.setMc(mc);
            hc.setNumPaciente(Double.parseDouble(tblPpre.getSelectionModel().getSelectedItem().getNumeroCuestionario().getValue()));
            hc.cargarTablas();
        cd.desconectar();
        pd.desconectar();
        cad.desconectar();
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "Seleccione un Prediagnostico.", 2);
        }

    }

    public void cargarDatos() {
        cuestionarios.forEach((cuestionario) -> {
            PrediagnosticoTabla pt = new PrediagnosticoTabla((DBObject) cuestionario);
            if (pt.getStatus().getValue().equals("2.0")) {
                String fechi = pd.buscarFecha(pt.getNumeroCuestionario().getValue());
                System.out.println(fechi);
                String[] fecha = fechi.split("-");
                int x = 0;

                if (fecha.length > 0) {
                    x = 2019 - Integer.parseInt(fecha[0]);

                } else {
                    x = 60;
                }
                pt.setEdad(new SimpleStringProperty(String.valueOf(x)));
                ptol.add(pt);
            }
        });
    }
}
