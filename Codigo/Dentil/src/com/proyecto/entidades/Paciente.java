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
public class Paciente {
    
    private int pacienteId;
    private String pacienteNOmbre;
    private String pacientePaterno;
    private String pacienteMaterno;
    private Date pacienteFechaNacimiento;
    private int pacienteEdad;
    private int pacienteTelefono;
    private int pacienteCelular;
    private String pacienteEmail;

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNOmbre() {
        return pacienteNOmbre;
    }

    public void setPacienteNOmbre(String pacienteNOmbre) {
        this.pacienteNOmbre = pacienteNOmbre;
    }

    public String getPacientePaterno() {
        return pacientePaterno;
    }

    public void setPacientePaterno(String pacientePaterno) {
        this.pacientePaterno = pacientePaterno;
    }

    public String getPacienteMaterno() {
        return pacienteMaterno;
    }

    public void setPacienteMaterno(String pacienteMaterno) {
        this.pacienteMaterno = pacienteMaterno;
    }

    public Date getPacienteFechaNacimiento() {
        return pacienteFechaNacimiento;
    }

    public void setPacienteFechaNacimiento(Date pacienteFechaNacimiento) {
        this.pacienteFechaNacimiento = pacienteFechaNacimiento;
    }

    public int getPacienteEdad() {
        return pacienteEdad;
    }

    public void setPacienteEdad(int pacienteEdad) {
        this.pacienteEdad = pacienteEdad;
    }

    public int getPacienteTelefono() {
        return pacienteTelefono;
    }

    public void setPacienteTelefono(int pacienteTelefono) {
        this.pacienteTelefono = pacienteTelefono;
    }

    public int getPacienteCelular() {
        return pacienteCelular;
    }

    public void setPacienteCelular(int pacienteCelular) {
        this.pacienteCelular = pacienteCelular;
    }

    public String getPacienteEmail() {
        return pacienteEmail;
    }

    public void setPacienteEmail(String pacienteEmail) {
        this.pacienteEmail = pacienteEmail;
    }
    
    
    
}
