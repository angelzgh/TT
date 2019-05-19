/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Axel Reyes
 */
public class Cuestionario {

    private int numCuestionario = 0;
    private Instrumento s50, hsdr;
    private Date inicioCuestionario, duracion, finCuestionario;
    private LinkedList RespuestasContestadas;

    public Cuestionario() {
        s50 = new Instrumento();
        hsdr = new Instrumento();
        inicioCuestionario = new Date();
        RespuestasContestadas = new LinkedList();
    }

    public Cuestionario(Cuestionario c) {
        this.numCuestionario = c.numCuestionario;
        this.s50 = c.s50;
        this.hsdr = c.hsdr;
        this.inicioCuestionario = c.inicioCuestionario;
        this.duracion = c.duracion;
        this.finCuestionario = c.finCuestionario;
        this.RespuestasContestadas = c.RespuestasContestadas;
    }

    public boolean respuestaContestada(int numeroPregunta) {
        boolean existe = false;
        Respuesta r = new Respuesta(numeroPregunta, 0);
        for (int i = 0; i < RespuestasContestadas.size(); i++) {
            Respuesta archivo = (Respuesta) RespuestasContestadas.get(i);
            if (archivo.getNumeroPregunta() == r.getNumeroPregunta()) {
                //System.out.println(archivo.getNumeroPregunta() +"//"+ r.getNumeroPregunta());
                existe = true;
            }
        }
        return existe;
    }

    public void agregarRespuesta(int numeroPregunta, int valorRespuesta) {
        Respuesta r = new Respuesta(numeroPregunta, valorRespuesta);
        RespuestasContestadas.add(r);
    }

    public void quitarRespuesta(int numeroPregunta) {
        int x = 0;
        for (Object RespuestasContestada : RespuestasContestadas) {

            Respuesta r = (Respuesta) RespuestasContestada;
            if (r.getNumeroPregunta() == numeroPregunta) {
                RespuestasContestadas.remove(x);
                break;
            }
            x++;
        }
    }

    public void mostrarRespuestas() {
        System.out.println("RESPUESTAS");
        for (Object RespuestasContestada : RespuestasContestadas) {

            Respuesta r = (Respuesta) RespuestasContestada;
            System.out.println(r.toString());
        }
        System.out.println("FIN RESPUESTAS");
    }

    public void quitarDesde(int desde) {
        while (desde < RespuestasContestadas.size()) {
            RespuestasContestadas.remove(desde);
        }
    }

    public Respuesta obtenerRespuesta(int numeroPregunta) {
        Respuesta r = null;
        for (int i = 1; i < RespuestasContestadas.size(); i++) {
            Respuesta res = (Respuesta) RespuestasContestadas.get(i);
            if (res.getNumeroPregunta() == numeroPregunta) {
                r = res;
                break;
            }
        }

        return r;
    }

    public LinkedList obtenerPreguntasContestadas() {

        return RespuestasContestadas;
    }

    public void calificarPregunta(int instrumento, int trastorno, Double puntaje) {
        if (instrumento == 1) {
            s50.sumarPuntaje(trastorno, puntaje);
        } else {
            hsdr.sumarPuntaje(trastorno, puntaje);
        }

    }

    public void quitarPregunta(int instrumento, int trastorno, int puntaje) {
        if (instrumento == 1) {
            s50.restarPuntaje(trastorno, puntaje);
        } else {
            hsdr.restarPuntaje(trastorno, puntaje);
        }

    }

    public Double getTrastorno(int instrumento, int trastorno) {
        if (instrumento == 1) {
            return s50.getTrastorno(trastorno);
        } else {
            return hsdr.getTrastorno(trastorno);
        }
    }

    public int getNumCuestionario() {
        return numCuestionario;
    }

    public void setNumCuestionario(int numCuestionario) {
        this.numCuestionario = numCuestionario;
    }

    @Override
    public String toString() {
        return "Cuestionario{" + "numCuestionario=" + numCuestionario + ", s50=" + s50 + ", hsdr=" + hsdr + ", inicioCuestionario=" + inicioCuestionario + ", duracion=" + duracion + ", finCuestionario=" + finCuestionario + ", RespuestasContestadas=" + RespuestasContestadas + '}';
    }

    public Date getInicioCuestionario() {
        return inicioCuestionario;
    }

    public void setInicioCuestionario(Date inicioCuestionario) {
        this.inicioCuestionario = inicioCuestionario;
    }

    public Date getDuracion() {
        System.out.println(finCuestionario.getTime() - inicioCuestionario.getTime());
        duracion = new Date(finCuestionario.getTime() - inicioCuestionario.getTime());

        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public Date getFinCuestionario() {
        finCuestionario = new Date();
        return finCuestionario;
    }

    public void setFinCuestionario(Date finCuestionario) {
        this.finCuestionario = finCuestionario;
    }

}
