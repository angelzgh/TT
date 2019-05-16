/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.Test;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class RecomendacionesController implements Initializable {

    private menuController mc;
    private cargadorVista cv;
    private Test test;
    private InfoCuestionario ic;
    private Paciente paciente;
    private Conducta conducta;

    public menuController getMc() {
        return mc;
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

    public void setMc(menuController mc) {
        this.mc = mc;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public InfoCuestionario getIc() {
        return ic;
    }

    public void setIc(InfoCuestionario ic) {
        this.ic = ic;
    }

    @FXML
    private JFXButton btnVolver;

    @FXML
    private TableView<?> tblRtrastornos;

    @FXML
    private TableView<?> tblPtrastornos1;

    @FXML
    private TableColumn<?, ?> tblRrecomendaciones;

    @FXML
    private JFXButton btnRimprimir;

    @FXML
    private JFXButton btnRenviarcorreo;

    @FXML
    private JFXButton btnRcerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cv = new cargadorVista();
    }

    @FXML
    void enviarEmailRecomendacion(ActionEvent event) {

    }

    @FXML
    void imprimirRecomendacion(ActionEvent event) {

    }
    @FXML
 void PDF(ActionEvent event) throws JRException, IOException {
HashMap parametros=new HashMap();
String master=System.getProperty("user.dir")+"\\src\\Center\\Reporte.jasper";
System.out.println(master);
String edad = Integer.toString(paciente.getEdad());
String diatrabaja ="";
String horariot = "";
if(conducta.getJornadaLaboral()==3){
diatrabaja = "Domingo";
}else if(conducta.getJornadaLaboral()==2){
diatrabaja = "Sabado";
}else if(conducta.getJornadaLaboral()==1){
diatrabaja = "Viernes";
}
if(conducta.getHorarioTrabajo()==1){
horariot = "Fijo";
}else if(conducta.getHorarioTrabajo()==2){
horariot = "Por turnos";    
}else if(conducta.getHorarioTrabajo()==3){
horariot = "Sin horario fijo";
}
String hpdiasd = Double.toString(conducta.getPromedioHorasDescanso());
String hpdiast = Double.toString(conducta.getPromedioHorasLaborales());
String diasd = Double.toString(conducta.getDiasDeDescanso());
String hpsueño = Double.toString(conducta.getPromedioHoras());

if(conducta.isTrabaja()){
    parametros.put("trabaja","Sí");  
parametros.put("diatrabaja",diatrabaja);
parametros.put("horariot",horariot);
parametros.put("hpdiasd",hpdiasd);
parametros.put("hpdiast",hpdiast);
parametros.put("diasd",diasd);
parametros.put("hpsueño","No aplica");
}else{
parametros.put("trabaja","No");  
parametros.put("diatrabaja","No aplica");
parametros.put("horariot","No aplica");
parametros.put("hpdiasd","No aplica");
parametros.put("hpdiast","No aplica");
parametros.put("diasd","No aplica");
parametros.put("hpsueño",hpsueño);

}
parametros.put("nombre",paciente.getNombre());
parametros.put("apellidos",paciente.getApellido());
parametros.put("edads",edad);
parametros.put("sexo",paciente.getSexo());
parametros.put("curp",paciente.getCURP());
parametros.put("escolaridad",paciente.getEscolaridad());
JasperPrint informe=JasperFillManager.fillReport(master, parametros, new JREmptyDataSource());
//JasperViewer.viewReport(informe,false);
// Lo exportamos!
JasperExportManager.exportReportToPdfFile(informe,"C://TT//"+paciente.getNombre()+ ".pdf");
String file = new String("C://TT//"+paciente.getNombre()+ ".pdf");
//definiendo la ruta en la propiedad file
Runtime.getRuntime().exec("cmd /c start "+file);
}

    @FXML
    void regresarPrediagnostico(ActionEvent event) {
        Prediagnostico2Controller pc
                = (Prediagnostico2Controller) cv.cambiarVista("/Center/Prediagnostico2.fxml", mc.getPanelPrin());
        pc.setTest(test);
        pc.setIc(ic);
        pc.setMc(mc);
        pc.setConducta(conducta);
        pc.setPaciente(paciente);
        pc.configurarVista();
    }

    @FXML
    void verRecomendacion(ActionEvent event) {

    }
}
