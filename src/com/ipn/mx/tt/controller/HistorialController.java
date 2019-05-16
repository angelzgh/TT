/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.CuestionarioAplicadoDAO;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.PacienteTabla;
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
            if (busqueda.length() > 2) {
                ptol.clear();
                LinkedList ls = new LinkedList();
                cuestionarios.forEach((cuestionario)
                        -> {
                    
                    PrediagnosticoTabla pt = new PrediagnosticoTabla((DBObject) cuestionario);
                    if (pt.getStatus().getValue().equals("2.0") && pt.getNombre().getValue().contains(curp)) {
                        ptol.add(pt);
                    }
                    
                });
            } else {
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
            pc.setIc(ic);
            System.out.println(ic.toString());
            pc.cargarResultadosPred();
            //pc.startgrafica();
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "Seleccione un Prediagnostico.", 2);
        }
        
    }
    
    public void cargarDatos() {
        cuestionarios.forEach((cuestionario) -> {
            PrediagnosticoTabla pt = new PrediagnosticoTabla((DBObject) cuestionario);
            if (pt.getStatus().getValue().equals("2.0")) {
                ptol.add(pt);
            }
        });
    }
}
