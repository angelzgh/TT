/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.controller;
import java.util.*;


/**
 *
 * @author Temp
 */
public class ReportePrueba {
    private String fecha;
    private String nombre;
    private String apellidos;
    private String edad;
    private String sexo;
    private String curp;
    private String trabaja;
    private String trabajade;
    private String diasdetrabajo;
    private String horassueñodescanso;
    private String horasueñotrabajo;
    private String diasdescanso;
    private String horassueño;
    private String insomnio;
    private String ritmo;
    private String sindrome;
    private String apnea;
    private String hiper;
    private String narcolepsia;
    private String impacto;
    private String recomendaciones;
    private String recoespecialista;
  
     public ReportePrueba(){        
    }

    public ReportePrueba(String fecha, String nombre, String apellidos, String edad, String sexo, String curp, 
            String trabaja, String trabajade, String diasdetrabajo, String horassueñodescanso, String horasueñotrabajo, 
            String diasdescanso, String horassueño, String insomnio, String ritmo, String sindrome, String apnea, 
            String hiper, String narcolepsia, String impacto, String recomendaciones, String recoespecialista) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.curp = curp;
        this.trabaja = trabaja;
        this.trabajade = trabajade;
        this.diasdetrabajo = diasdetrabajo;
        this.horassueñodescanso = horassueñodescanso;
        this.horasueñotrabajo = horasueñotrabajo;
        this.diasdescanso = diasdescanso;
        this.horassueño = horassueño;
        this.insomnio = insomnio;
        this.ritmo = ritmo;
        this.sindrome = sindrome;
        this.apnea = apnea;
        this.hiper = hiper;
        this.narcolepsia = narcolepsia;
        this.impacto = impacto;
        this.recomendaciones = recomendaciones;
        this.recoespecialista = recoespecialista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
    }

    public String getTrabajade() {
        return trabajade;
    }

    public void setTrabajade(String trabajade) {
        this.trabajade = trabajade;
    }

    public String getDiasdetrabajo() {
        return diasdetrabajo;
    }

    public void setDiasdetrabajo(String diasdetrabajo) {
        this.diasdetrabajo = diasdetrabajo;
    }

    public String getHorassueñodescanso() {
        return horassueñodescanso;
    }

    public void setHorassueñodescanso(String horassueñodescanso) {
        this.horassueñodescanso = horassueñodescanso;
    }

    public String getHorasueñotrabajo() {
        return horasueñotrabajo;
    }

    public void setHorasueñotrabajo(String horasueñotrabajo) {
        this.horasueñotrabajo = horasueñotrabajo;
    }

    public String getDiasdescanso() {
        return diasdescanso;
    }

    public void setDiasdescanso(String diasdescanso) {
        this.diasdescanso = diasdescanso;
    }

    public String getHorassueño() {
        return horassueño;
    }

    public void setHorassueño(String horassueño) {
        this.horassueño = horassueño;
    }

    public String getInsomnio() {
        return insomnio;
    }

    public void setInsomnio(String insomnio) {
        this.insomnio = insomnio;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getSindrome() {
        return sindrome;
    }

    public void setSindrome(String sindrome) {
        this.sindrome = sindrome;
    }

    public String getApnea() {
        return apnea;
    }

    public void setApnea(String apnea) {
        this.apnea = apnea;
    }

    public String getHiper() {
        return hiper;
    }

    public void setHiper(String hiper) {
        this.hiper = hiper;
    }

    public String getNarcolepsia() {
        return narcolepsia;
    }

    public void setNarcolepsia(String narcolepsia) {
        this.narcolepsia = narcolepsia;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getRecoespecialista() {
        return recoespecialista;
    }

    public void setRecoespecialista(String recoespecialista) {
        this.recoespecialista = recoespecialista;
    }
 /**   public void prueba(){
        String master=System.getProperty("user.dir")+"\\com\\ipn\\mx\\tt\\controller";
//        Hashmap parametros=new Hashmap();
        String fileName = "ao.jasper";

/**HashMap param = new HashMap();
try {
// llenamos el reporte con un origen de datos vacio donde le pasamos como argumentos
//el nombre del archvios jasper, el hasmap y una JREMptyDataSource que especifica que no habra conexion a
// base de datos
JasperPrint print = JasperFillManager.fillReport(fileName, param, new JREmptyDataSource());

//lanzamos ej jasper viewer recibiendo coo argumento el informe y un valor boolenano para indicar
// que una vez cerrado el visor, no termine la aplicacion principal
JasperViewer jviewer = new JasperViewer(print,false);

//Establecemos el titulo del visor
jviewer.setTitle("Reporte sin conexion a base de datos" );
jviewer.setVisible(true);

} catch (JRException e) {
e.printStackTrace();
System.exit(1);
}
}*/
}
     
     
     
     
     
     
     
     

