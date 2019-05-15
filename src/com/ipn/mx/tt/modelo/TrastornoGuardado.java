/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.modelo;

import com.mongodb.DBObject;

/**
 *
 * @author Axel Reyes
 */
public class TrastornoGuardado {

    private Double numCuestionario;
    private Double idCuestionario;
    private Double insomnio;
    private Double rc;
    private Double rls;
    private Double apnea;
    private Double hipersomnia;
    private Double narcolepsia;
    private Double otro;

    public TrastornoGuardado(Double numCuestionario, Double idCuestionario, Double insomnio, Double rc, Double rls, Double apnea, Double hipersomnia, Double narcolepsia, Double otro) {
        this.numCuestionario = numCuestionario;
        this.idCuestionario = idCuestionario;
        this.insomnio = insomnio;
        this.rc = rc;
        this.rls = rls;
        this.apnea = apnea;
        this.hipersomnia = hipersomnia;
        this.narcolepsia = narcolepsia;
        this.otro = otro;
    }

    public TrastornoGuardado(DBObject dbo) {
        this.numCuestionario = (Double) dbo.get("_numCuestionario");
        this.idCuestionario = (Double) dbo.get("_idCuestionario");
        this.insomnio = (Double) dbo.get("insomnio");
        this.rc = (Double) dbo.get("rc");
        this.rls = (Double) dbo.get("rls");
        this.apnea = (Double) dbo.get("apnea");
        this.hipersomnia = (Double) dbo.get("hipersomnia");
        this.narcolepsia = (Double) dbo.get("narcolepsia");
        this.otro = (Double) dbo.get("otro");
    }

    public Double getNumCuestionario() {
        return numCuestionario;
    }

    public void setNumCuestionario(Double numCuestionario) {
        this.numCuestionario = numCuestionario;
    }

    public Double getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Double idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Double getInsomnio() {
        return insomnio;
    }

    public void setInsomnio(Double insomnio) {
        this.insomnio = insomnio;
    }

    public Double getRc() {
        return rc;
    }

    public void setRc(Double rc) {
        this.rc = rc;
    }

    public Double getRls() {
        return rls;
    }

    public void setRls(Double rls) {
        this.rls = rls;
    }

    public Double getApnea() {
        return apnea;
    }

    public void setApnea(Double apnea) {
        this.apnea = apnea;
    }

    public Double getHipersomnia() {
        return hipersomnia;
    }

    public void setHipersomnia(Double hipersomnia) {
        this.hipersomnia = hipersomnia;
    }

    public Double getNarcolepsia() {
        return narcolepsia;
    }

    public void setNarcolepsia(Double narcolepsia) {
        this.narcolepsia = narcolepsia;
    }

    public Double getOtro() {
        return otro;
    }

    public void setOtro(Double otro) {
        this.otro = otro;
    }

    @Override
    public String toString() {
        return "TrastornoGuardado{" + "numCuestionario=" + numCuestionario + ", idCuestionario=" + idCuestionario + ", insomnio=" + insomnio + ", rc=" + rc + ", rls=" + rls + ", apnea=" + apnea + ", hipersomnia=" + hipersomnia + ", narcolepsia=" + narcolepsia + ", otro=" + otro + '}';
    }

}
