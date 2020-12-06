/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.entidades;

/**
 *
 * @author Arturo
 */
public class Medicamento {
   
    private int medId;
    private String medDescripcion;
    private int medCantidad;
    private float medCosto;
    private float medVeentas;
    private int pacId;
    private String medNombrePaciente;

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public String getMedDescripcion() {
        return medDescripcion;
    }

    public void setMedDescripcion(String medDescripcion) {
        this.medDescripcion = medDescripcion;
    }

    public int getMedCantidad() {
        return medCantidad;
    }

    public void setMedCantidad(int medCantidad) {
        this.medCantidad = medCantidad;
    }

    public float getMedCosto() {
        return medCosto;
    }

    public void setMedCosto(float medCosto) {
        this.medCosto = medCosto;
    }

    public float getMedVeentas() {
        return medVeentas;
    }

    public void setMedVeentas(float medVeentas) {
        this.medVeentas = medVeentas;
    }

    public int getPacId() {
        return pacId;
    }

    public void setPacId(int pacId) {
        this.pacId = pacId;
    }

    public String getMedNombrePaciente() {
        return medNombrePaciente;
    }

    public void setMedNombrePaciente(String medNombrePaciente) {
        this.medNombrePaciente = medNombrePaciente;
    }
    
    
}
