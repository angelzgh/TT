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
import com.ipn.mx.tt.util.CustomMessage;
import com.ipn.mx.tt.util.Validador;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class PacienteNuevoController implements Initializable {

    private Paciente p;
    private InfoCuestionario ic;
    private CuestionarioAplicadoDAO cad;
    private menuController c;
    private cargadorVista cv;
    private Validador validador;

    public menuController getC() {
        return c;
    }

    public void setC(menuController c) {
        this.c = c;
    }

    @FXML
    private AnchorPane panelP;

    @FXML
    private JFXTextField txtPnCURP;

    @FXML
    private JFXTextField txtPnnombre;

    @FXML
    private JFXButton btnPnregistrar;

    @FXML
    private JFXTextField txtPntelefono;

    @FXML
    private JFXTextField txtPndireccion;

    @FXML
    private JFXDatePicker datePn;

    @FXML
    private JFXTextField txtPncorreo;

    @FXML
    private JFXRadioButton rbPmasculino;

    @FXML
    private ToggleGroup grupoPregunta;

    @FXML
    private JFXRadioButton rbPfemenino;

    @FXML
    private JFXTextField txtPnapellido;

    @FXML
    private JFXComboBox cbxescolaridad;
    @FXML
    private JFXButton btnPnrActualizar;

    @FXML
    private JFXButton btnVolverP;
    @FXML
    private JFXButton volverPacienteR;
    private BorderPane bp;

    ObservableList<String> items = FXCollections.observableArrayList("Sin estudios", "Primaria",
            "Secundaria", "Preparatoria", "Licenciatura", "Maestria", "Doctorado");

    /**
     * Initializes the controller class.
     */
    PacienteDAO pd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pd = new PacienteDAO();
        validador = new Validador();
        cv = new cargadorVista();
        pd.conectar();
        cad = new CuestionarioAplicadoDAO();
        cad.conectar();
        cbxescolaridad.setItems(items);
        cbxescolaridad.setPromptText("-");
        btnPnrActualizar.setVisible(false);
        btnVolverP.setVisible(false);
        // TODO
    }

    public void registrarPaciente(Paciente p, InfoCuestionario ic) {
        registrarPaciente(p);
        cad.insertarInfoCuestionario(ic);
    }

    public void registrarPaciente(Paciente p) {
        pd.insertarPaciente(p);
    }

    @FXML
    void registrarPaciente(ActionEvent event) {

        String Nombre = validador.validarTF(txtPnnombre),
                Apellido = validador.validarTF(txtPnapellido),
                Sexo,
                Correo = validador.validarTF(txtPncorreo),
                Fecha = validador.validarDP(datePn),
                Direccion = validador.validarTF(txtPndireccion),
                Telefono = validador.validarTF(txtPntelefono),
                CURP = validador.validarTF(txtPnCURP),
                Escolaridad = validador.validarCbx(cbxescolaridad);

        if (rbPfemenino.isSelected()) {
            Sexo = "F";
        } else {
            Sexo = "M";
        }
        if (!pd.pacienteExiste(CURP)) {
            if (!Nombre.equals("") && !Apellido.equals("") && !CURP.equals("") && !Correo.equals("") && !Fecha.equals("")
                    && !Direccion.equals("") && !Telefono.equals("") && !cbxescolaridad.getValue().equals("-")) {

                p = new Paciente(Nombre, Apellido, Sexo, Correo, Fecha, Direccion, Telefono, CURP, Escolaridad);
                ic = new InfoCuestionario(cad.buscarSiguiente() + 1, 0.0, CURP, c.getUsuario().getId(),c.getDia());
                registrarPaciente(p, ic);

                CustomMessage cm1 = new CustomMessage("MENSAJE", "¿Desea realizar el Cuestionario?", 4);
                if (cm1.getMessage().getButtonData().equals(ButtonType.OK.getButtonData())) {
                    ComenzarTestController ctc = (ComenzarTestController) cv.cambiarVista("/Center/ComenzarTest.fxml", c.getPanelPrin());
                    ctc.setC(c);
                    ctc.setPaciente(p);
                    ctc.setDatosPaciente(true);
                    ctc.ocultarEspecialista();
                    ctc.setIc(ic);
                } else {
                    CustomMessage cm2 = new CustomMessage("MENSAJE", "El cuestionario se guardó para más tarde", 2);
                    // GENERAR PDF O MOSTRAR EL NUMERO DE CUESTIONARIO ASIGNADO PARA APLICAR MÁS TARDE
                }
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Hubo un error en alguno de los campos...", 2);
            }
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "EL CURP ya esta registrado...", 2);
        }

    }

    void setBorder(BorderPane panelRight) {
        bp = panelRight;
    }

    public void hacerCuestionario() {

        //CARGAR VISTA 
        ComenzarTestController ctc = (ComenzarTestController) cv.cambiarVista("/Center/ComenzarTest.fxml", c.getPanelPrin());
        ctc.setC(c);
        ctc.setPaciente(p);
        ctc.setDatosPaciente(true);
        ctc.setIc(ic);
        System.out.println(ic.toString());
        System.out.println(p.toString());

    }

    @FXML
    public void actualizarPaciente(ActionEvent event) {
        String Nombre = validador.validarTF(txtPnnombre),
                Apellido = validador.validarTF(txtPnapellido),
                Sexo,
                Correo = validador.validarTF(txtPncorreo),
                Fecha = validador.validarDP(datePn),
                Direccion = validador.validarTF(txtPndireccion),
                Telefono = validador.validarTF(txtPntelefono),
                CURP = validador.validarTF(txtPnCURP),
                Escolaridad = validador.validarCbx(cbxescolaridad);

        if (rbPfemenino.isSelected()) {
            Sexo = "F";
        } else {
            Sexo = "M";
        }
        if (pd.pacienteExiste(CURP)) {
            if (!Nombre.equals("") && !Apellido.equals("") && !CURP.equals("") && !Correo.equals("") && !Fecha.equals("")
                    && !Direccion.equals("") && !Telefono.equals("") && !cbxescolaridad.getValue().equals("-")) {

                p = new Paciente(Nombre, Apellido, Sexo, Correo, Fecha, Direccion, Telefono, CURP, Escolaridad);

                CustomMessage cm1 = new CustomMessage("MENSAJE", "¿Guardar los cambios?", 3);
                if (cm1.getMessage().getButtonData().equals(ButtonType.OK.getButtonData())) {
                    if (pd.actualizarDatos(p)) {
                        CustomMessage cme = new CustomMessage("MENSAJE", "Los cambios se guardaron con éxito", 0);
                        //exito
                    } else {
                        CustomMessage cme = new CustomMessage("ERROR", "Hubo error al intentar guardar los cambios...", 2);
                        //error
                    }
                }
            } else {
                CustomMessage cm = new CustomMessage("ERROR", "Hubo un error en alguno de los campos...", 2);
            }
        } else {
            CustomMessage cm = new CustomMessage("ERROR", "El Paciente no existe...", 2);
        }

    }

    @FXML
    public void volverPaciente(ActionEvent event) {
        PacienteConRegistroController pcrc = (PacienteConRegistroController) cv.cambiarVista("/Center/PacienteConRegistro.fxml", panelP);
        pcrc.setC(c);
    }

    public void ponerPaciente(Paciente p) {
        txtPnCURP.setText(p.getCURP());
        txtPnapellido.setText(p.getApellido());
        txtPncorreo.setText(p.getCorreo());
        txtPndireccion.setText(p.getDireccion());
        txtPnnombre.setText(p.getNombre());
        txtPntelefono.setText(p.getTelefono());
        if (p.getSexo().equals("F")) {
            rbPfemenino.setSelected(true);
        } else {
            rbPmasculino.setSelected(true);
        }
        cbxescolaridad.setValue(p.getEscolaridad());
    }

    public void funcionActualizar() {
        btnPnrActualizar.setVisible(true);
        btnPnregistrar.setVisible(false);
        btnVolverP.setVisible(true);
    }
}
