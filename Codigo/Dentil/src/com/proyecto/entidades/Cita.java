/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.entidades;

import java.util.Date;

/**
 *
 * @author Arturo
 */
public class Cita {
    
    private int cita;
    private String citaTipoCIta;
    private Date citaFecha;
    private String citaHora;
    private int paciId ;
    private String citaNombrePaciente;
    private String citaIdDoctor;
    private String citaNombreDoctor;
    private String citaConsultorio;
    private String citaConfirmacion;

    public int getCita() {
        return cita;
    }

    public void setCita(int cita) {
        this.cita = cita;
    }

    public String getCitaTipoCIta() {
        return citaTipoCIta;
    }

    public void setCitaTipoCIta(String citaTipoCIta) {
        this.citaTipoCIta = citaTipoCIta;
    }

    public Date getCitaFecha() {
        return citaFecha;
    }

    public void setCitaFecha(Date citaFecha) {
        this.citaFecha = citaFecha;
    }

    public String getCitaHora() {
        return citaHora;
    }

    public void setCitaHora(String citaHora) {
        this.citaHora = citaHora;
    }

    public int getPaciId() {
        return paciId;
    }

    public void setPaciId(int paciId) {
        this.paciId = paciId;
    }

    public String getCitaNombrePaciente() {
        return citaNombrePaciente;
    }

    public void setCitaNombrePaciente(String citaNombrePaciente) {
        this.citaNombrePaciente = citaNombrePaciente;
    }

    public String getCitaIdDoctor() {
        return citaIdDoctor;
    }

    public void setCitaIdDoctor(String citaIdDoctor) {
        this.citaIdDoctor = citaIdDoctor;
    }

    public String getCitaNombreDoctor() {
        return citaNombreDoctor;
    }

    public void setCitaNombreDoctor(String citaNombreDoctor) {
        this.citaNombreDoctor = citaNombreDoctor;
    }

    public String getCitaConsultorio() {
        return citaConsultorio;
    }

    public void setCitaConsultorio(String citaConsultorio) {
        this.citaConsultorio = citaConsultorio;
    }

    public String getCitaConfirmacion() {
        return citaConfirmacion;
    }

    public void setCitaConfirmacion(String citaConfirmacion) {
        this.citaConfirmacion = citaConfirmacion;
    }
    
    
    
}
