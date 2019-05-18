/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.ipn.mx.tt.dao.CuestionarioAplicadoDAO;
import com.ipn.mx.tt.dao.CuestionarioPreguntaDAO;
import com.ipn.mx.tt.dao.CutoffDAO;
import com.ipn.mx.tt.dao.PrediagnosticoDAO;
import com.ipn.mx.tt.dao.PreguntaContestadaDAO;
import com.ipn.mx.tt.dao.PreguntaDAO;
import com.ipn.mx.tt.dao.PreguntaEquivalenciaDAO;
import com.ipn.mx.tt.dao.SintomaPreguntaDAO;
import com.ipn.mx.tt.dao.TrastornoSintomaDAO;
import com.mongodb.DBObject;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Axel Reyes
 */
public class Test {

    private Cuestionario cuestionario;
    private PreguntaDAO pd;
    private CuestionarioPreguntaDAO cpd;
    private CuestionarioAplicadoDAO cad;
    private SintomaPreguntaDAO spd;
    private TrastornoSintomaDAO tsd;
    private PreguntaEquivalenciaDAO ped;
    private PreguntaContestadaDAO pcd;
    private CutoffDAO cd;
    private PrediagnosticoDAO pred;
    private int contadorPreguntas;
    private List preguntas, tipoCuestionario, SintomaPregunta, TrastornoSintoma, equivalencias, cutoff;
    private int tipo, tamañoCuestionario;
    private int[] trastornos, numeracion;
    private int numeroTrastornos;

    public Test(int tipo) {
        cuestionario = new Cuestionario();
        pd = new PreguntaDAO();
        cpd = new CuestionarioPreguntaDAO();
        spd = new SintomaPreguntaDAO();
        tsd = new TrastornoSintomaDAO();
        ped = new PreguntaEquivalenciaDAO();
        cd = new CutoffDAO();
        pcd = new PreguntaContestadaDAO();
        cad = new CuestionarioAplicadoDAO();
        pred = new PrediagnosticoDAO();
        this.tipo = tipo;

        pred.conectar();
        cd.conectar();
        pd.conectar();
        cpd.conectar();
        spd.conectar();
        tsd.conectar();
        ped.conectar();
        pcd.conectar();
        cad.conectar();

        preguntas = pd.getPreguntas(tipo);
        tipoCuestionario = cpd.getCuestionario();
        SintomaPregunta = spd.traerSintomas();
        TrastornoSintoma = tsd.traerTrastornos();
        equivalencias = ped.getEquivalencia();
        cutoff = cd.getCutOff();
        numeroTrastornos = 10;
        trastornos = new int[10];
        tamañoCuestionario = 69;
        numeracion = new int[tamañoCuestionario];
        for (int i = 0; i < numeroTrastornos; i++) {
            trastornos[i] = 0;
        }
        for (int i = 0; i < tamañoCuestionario; i++) {
            numeracion[i] = i + 1;
        }
        obtenerNumeracion();
        contadorPreguntas = 1;
//System.out.println(SintomaPregunta.toString());
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Date getFinCuestionario() {
        return cuestionario.getFinCuestionario();
    }

    public Date getDuracion() {
        return cuestionario.getDuracion();
    }

    public void calificarPregunta(int instrumento, int trastorno, Double puntaje) {
        cuestionario.calificarPregunta(instrumento, trastorno, puntaje);
    }

    public void agregarRespuesta(int preguntaC, int puntaje) {
        if (!cuestionario.respuestaContestada(preguntaC)) {
            cuestionario.agregarRespuesta(preguntaC, puntaje);
        }

    }

    public LinkedList obtenerPreguntasContestadas() {
        return cuestionario.obtenerPreguntasContestadas();
    }

    public void obtenerNumeracion() {
        Random rgen = new Random();  // Random number generator		
        for (int i = 0; i < numeracion.length; i++) {
            int randomPosition = rgen.nextInt(numeracion.length);
            int temp = numeracion[i];
            numeracion[i] = numeracion[randomPosition];
            numeracion[randomPosition] = temp;
        }

    }

    public Pregunta getPregunta(int i) {
        if (i > 0) {
            Pregunta p = new Pregunta((DBObject) preguntas.get(i - 1), tipo);
            return p;
        } else {
            Pregunta p = new Pregunta(99, "FIN", "");
            return p;
        }
    }

    public int getTipoCuestionario(int i) {
        return getEntero((DBObject) tipoCuestionario.get(i - 1));
    }

    public int getEntero(DBObject dbo) {
        Double x = (Double) dbo.get("_idCuestionario");
        int n;
        n = x.intValue();
        return n;
    }

    public LinkedList getSintoma(int pregunta) {
        LinkedList ls = new LinkedList();
        SintomaPregunta.forEach((sp) -> {
            SintomaPregunta spi = new SintomaPregunta((DBObject) sp);
            if (spi.getPregunta() == pregunta) {
                ls.add(spi);
            }
        });
        return ls;
    }

    public LinkedList getTrastorno(int sintoma) {
        LinkedList ls = new LinkedList();
        TrastornoSintoma.forEach((ts) -> {
            TrastornoSintoma tsi = new TrastornoSintoma((DBObject) ts);
            if (tsi.getSintoma() == sintoma) {
                ls.add(tsi);
            }
        });
        return ls;
    }

    public void levantarBandera(int trastorno) {
        trastornos[trastorno] = 1;
    }

    public void reiniciarBanderas() {
        for (int i = 0; i < numeroTrastornos; i++) {
            trastornos[i] = 0;
        }
    }

    public boolean banderaLevantada(int trastorno) {
        return trastornos[trastorno] != 0;
    }

    public LinkedList obtenerEquivalente(int pregunta) {
        LinkedList ls = new LinkedList();
        equivalencias.forEach((equivalencia) -> {
            PreguntaEquivalente pe = new PreguntaEquivalente((DBObject) equivalencia);
            if (pe.getIdPregunta() == pregunta) {
                ls.add(pe.getIdPreguntaEquivalente());
            }
        });
        return ls;
    }

    public void sumarContadorPregunta() {
        contadorPreguntas++;
    }

    public int getSigPregunta() {

        if (contadorPreguntas > tamañoCuestionario) {
            return -1;
        } else {
            if (!cuestionario.respuestaContestada(numeracion[contadorPreguntas - 1])) {
                return numeracion[contadorPreguntas - 1];
            } else {
                //System.out.println("PREGUNTA PREVIAMENTE CONTESTADA: " + numeracion[contadorPreguntas - 1]);
                sumarContadorPregunta();
                return getSigPregunta();
            }
        }
    }

    public int getContadorPreguntas() {
        return contadorPreguntas;
    }

    public void setContadorPreguntas(int contadorPreguntas) {
        this.contadorPreguntas = contadorPreguntas;
    }

    public boolean cuestionarioCompletado() {
        return contadorPreguntas > tamañoCuestionario;
    }

    public boolean respuestaContestada(int pregunta) {
        return cuestionario.respuestaContestada(pregunta);
    }

    public Double puntajeEquivalente(int cuestionario, int cuestionarioE, Double puntaje) {
        Double resultado;
        if (cuestionario == cuestionarioE) {
            return puntaje;
        } else {
            if (cuestionarioE == 1) {
                resultado = 4 * puntaje / 3;
                resultado -= 0.333333333333333;

            } else {

                resultado = 3 * puntaje / 4;
                resultado += 0.25;
            }
            return resultado;
        }

    }

    public Respuesta obtenerRespuesta(Double numPregunta) {
        return cuestionario.obtenerRespuesta(numPregunta.intValue());
    }

    public void guardarCuestionario(InfoCuestionario ic) {
        Double numCuestionario=ic.getIdCuestionario();
        pcd.guardarPreguntasContestadas(numCuestionario, obtenerPreguntasContestadas());
        cad.actualizarDatos(ic);
        pred.insertarTrastornos(1.0, numCuestionario, cuestionario);
        pred.insertarTrastornos(2.0, numCuestionario, cuestionario);
    }

    public Date getInicioCuestionario() {
        return cuestionario.getInicioCuestionario();
    }

    public Double getTrastorno(int instrumento, int trastorno) {
        if(cuestionario.getTrastorno(instrumento, trastorno)!=0)
            return cuestionario.getTrastorno(instrumento, trastorno);
        else
        return 0.0;
    }

    public Double getCutoff(Double Trastorno, Double idCuestionario) {
        Double x = 0.0;
        for (int i = 0; i < cutoff.size(); i++) {
            Cutoff c = new Cutoff((DBObject) cutoff.get(i));
            if (c.getIdTrastorno().equals(Trastorno) && c.getIdCuestionario().equals(idCuestionario)) {
                x = c.getPromedio();
            }
        }
        return x;
    }
        public Double getNumPreguntas(Double Trastorno, Double idCuestionario) {
        Double x = 0.0;
        for (int i = 0; i < cutoff.size(); i++) {
            Cutoff c = new Cutoff((DBObject) cutoff.get(i));
            if (c.getIdTrastorno().equals(Trastorno) && c.getIdCuestionario().equals(idCuestionario)) {
                x = c.getPreguntas();
            }
        }
        return x;
    }
}
