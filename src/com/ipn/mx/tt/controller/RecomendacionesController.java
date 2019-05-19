/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;

import com.ipn.mx.tt.dao.RecomendacionDAO;
import com.ipn.mx.tt.modelo.Conducta;
import com.ipn.mx.tt.modelo.InfoCuestionario;
import com.ipn.mx.tt.modelo.Paciente;
import com.ipn.mx.tt.modelo.Recomendacion;
import com.ipn.mx.tt.modelo.RecomendacionTabla;
import com.ipn.mx.tt.modelo.Test;
import com.ipn.mx.tt.modelo.TrastornoIntensidad;
import com.ipn.mx.tt.modelo.TrastornoIntensidadTabla;
import com.ipn.mx.tt.util.cargadorVista;
import com.jfoenix.controls.JFXButton;
import com.mongodb.DBObject;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * FXML Controller class
 *
 * @author garci
 */
public class RecomendacionesController implements Initializable {

    private String[] sintomasDetectados;
    private menuController mc;
    private cargadorVista cv;
    private Test test;
    private InfoCuestionario ic;
    private Paciente paciente;
    private Conducta conducta;
    private RecomendacionDAO rd;
    private LinkedList lsRecomendacion;

    @FXML
    private TableView<TrastornoIntensidadTabla> tblRtrastornos;
    @FXML
    private JFXButton btnVolver;

    @FXML
    private TableColumn<TrastornoIntensidadTabla, String> columnaTrastorno;

    @FXML
    private TableColumn<TrastornoIntensidadTabla, String> columnaIntensidad;

    @FXML
    private TableView<RecomendacionTabla> tblRecomendaciones;

    @FXML
    private TableColumn<RecomendacionTabla, String> columnaRrecomendaciones;

    @FXML
    private JFXButton btnRimprimir;

    @FXML
    private JFXButton btnRenviarcorreo;

    @FXML
    private JFXButton btnRcerrar;

    private ObservableList<RecomendacionTabla> rtol;
    private ObservableList<TrastornoIntensidadTabla> titol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rd = new RecomendacionDAO();
        rd.conectar();
        cv = new cargadorVista();
        columnaTrastorno.setCellValueFactory(cellData -> cellData.getValue().getTrastorno());
        columnaIntensidad.setCellValueFactory(cellData -> cellData.getValue().getIntensidad());

        columnaRrecomendaciones.setCellValueFactory(cellData -> cellData.getValue().getRecomendacion());
        rtol = FXCollections.observableArrayList();
        titol = FXCollections.observableArrayList();
        tblRecomendaciones.setItems(rtol);
        tblRtrastornos.setItems(titol);
        rtol.add(new RecomendacionTabla(new Recomendacion("Come bien")));
        titol.add(new TrastornoIntensidadTabla(new TrastornoIntensidad("Insomnio", "Media", 20.49, 40.0)));
        lsRecomendacion=new LinkedList();
    }

    public String[] getSintomasDetectados() {
        return sintomasDetectados;
    }

    public void setSintomasDetectados(String[] sintomasDetectados) {
        this.sintomasDetectados = sintomasDetectados;
    }

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
    void enviarEmailRecomendacion(ActionEvent event) {

    }

    @FXML
    void imprimirRecomendacion(ActionEvent event) {

    }

    @FXML
 void PDF(ActionEvent event) throws JRException, IOException, ParseException {
      Date now = new Date(System.currentTimeMillis());
SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
System.out.println(date.format(now));
    
		
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

        if (conducta.getJornadaLaboral() == 3) {
            diatrabaja = "Domingo";
        } else if (conducta.getJornadaLaboral() == 2) {
            diatrabaja = "Sabado";
        } else if (conducta.getJornadaLaboral() == 1) {
            diatrabaja = "Viernes";
        }
        if (conducta.getHorarioTrabajo() == 1) {
            horariot = "Fijo";
        } else if (conducta.getHorarioTrabajo() == 2) {
            horariot = "Por turnos";
        } else if (conducta.getHorarioTrabajo() == 3) {
            horariot = "Sin horario fijo";
        }

        if (conducta.isTrabaja()) {
            parametros.put("trabaja", "Sí");
            parametros.put("diatrabaja", diatrabaja);
            parametros.put("horariot", horariot);
            parametros.put("hpdiasd", hpdiasd);
            parametros.put("hpdiast", hpdiast);
            parametros.put("diasd", diasd);
            parametros.put("hpsueño", "No aplica");
        } else {
            parametros.put("trabaja", "No");
            parametros.put("diatrabaja", "No aplica");
            parametros.put("horariot", "No aplica");
            parametros.put("hpdiasd", "No aplica");
            parametros.put("hpdiast", "No aplica");
            parametros.put("diasd", "No aplica");
            parametros.put("hpsueño", hpsueño);

}
parametros.put("fecha",date.format(now));
parametros.put("nombre",paciente.getNombre());
parametros.put("apellidos",paciente.getApellido());
parametros.put("edads",edad);
parametros.put("sexo",paciente.getSexo());
parametros.put("curp",paciente.getCURP());
parametros.put("escolaridad",paciente.getEscolaridad());
JasperPrint informe=JasperFillManager.fillReport(master, parametros, new JREmptyDataSource());

        
        parametros.put("nombre", paciente.getNombre());
        parametros.put("apellidos", paciente.getApellido());
        parametros.put("edads", edad);
        parametros.put("sexo", paciente.getSexo());
        parametros.put("curp", paciente.getCURP());
        parametros.put("escolaridad", paciente.getEscolaridad());
//JasperViewer.viewReport(informe,false);

JasperExportManager.exportReportToPdfFile(informe,"C://TT//"+paciente.getNombre()+ ".pdf");
String file = new String("C://TT//"+paciente.getNombre()+ ".pdf");
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
        pc.configurarTrastorno();
        pc.configurarVista();
        pc.ponerConducta();
        pc.setSintomasDetectados(sintomasDetectados);
        pc.ponerTrastornosSintomas();
    }

    @FXML
    void verRecomendacion(ActionEvent event) {

    }

    public void ponerRecomendaciones() {
        obtenerRecomendaciones(1.0);
        obtenerRecomendaciones(2.0);
        obtenerRecomendaciones(3.0);
        obtenerRecomendaciones(4.0);
        obtenerRecomendaciones(5.0);
        obtenerRecomendaciones(6.0);

    }

    public void obtenerRecomendaciones(Double trastorno) {
        List l = rd.traerRecomendacion(trastorno);
        LinkedList ls=new LinkedList();
        System.out.println("TAMAÑO MAXIMO:" + l.size());
        while(ls.size()<3){
            int x = (int) (Math.random() * l.size()) + 1;
            System.out.println(x);
            if(!ls.contains(x))
            {
                ls.add(x);
                Recomendacion r=new Recomendacion((DBObject) l.get(x-1));
                RecomendacionTabla rt=new RecomendacionTabla(r);
                lsRecomendacion.add(r.getRecomendacion());
                rtol.add(rt);
                
            }
        }
    }
}
