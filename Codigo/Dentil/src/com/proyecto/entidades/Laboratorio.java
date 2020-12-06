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
public class Laboratorio {
    
    private int labId;
    private int pacId;
    private String labNombrePaciente;
    private int empId;
    private String labNombreEmpleado;
    private String laboFecha;
    private float labCosto;
    private String labDescripcion;
    private String labResultados;

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public int getPacId() {
        return pacId;
    }

    public void setPacId(int pacId) {
        this.pacId = pacId;
    }

    public String getLabNombrePaciente() {
        return labNombrePaciente;
    }

    public void setLabNombrePaciente(String labNombrePaciente) {
        this.labNombrePaciente = labNombrePaciente;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLabNombreEmpleado() {
        return labNombreEmpleado;
    }

    public void setLabNombreEmpleado(String labNombreEmpleado) {
        this.labNombreEmpleado = labNombreEmpleado;
    }

    public String getLaboFecha() {
        return laboFecha;
    }

    public void setLaboFecha(String laboFecha) {
        this.laboFecha = laboFecha;
    }

    public float getLabCosto() {
        return labCosto;
    }

    public void setLabCosto(float labCosto) {
        this.labCosto = labCosto;
    }

    public String getLabDescripcion() {
        return labDescripcion;
    }

    public void setLabDescripcion(String labDescripcion) {
        this.labDescripcion = labDescripcion;
    }

    public String getLabResultados() {
        return labResultados;
    }

    public void setLabResultados(String labResultados) {
        this.labResultados = labResultados;
    }
    
    
}
