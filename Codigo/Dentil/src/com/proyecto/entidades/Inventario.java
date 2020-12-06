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
public class Inventario {
    
    private int invId;
    private String invDescripcion;
    private Date invFechaLlegada;
    private int intCantidad;
    private int invKg;
    private int invelTs;
    private int invCosto;

    public int getInvId() {
        return invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
    }

    public String getInvDescripcion() {
        return invDescripcion;
    }

    public void setInvDescripcion(String invDescripcion) {
        this.invDescripcion = invDescripcion;
    }

    public Date getInvFechaLlegada() {
        return invFechaLlegada;
    }

    public void setInvFechaLlegada(Date invFechaLlegada) {
        this.invFechaLlegada = invFechaLlegada;
    }

    public int getIntCantidad() {
        return intCantidad;
    }

    public void setIntCantidad(int intCantidad) {
        this.intCantidad = intCantidad;
    }

    public int getInvKg() {
        return invKg;
    }

    public void setInvKg(int invKg) {
        this.invKg = invKg;
    }

    public int getInvelTs() {
        return invelTs;
    }

    public void setInvelTs(int invelTs) {
        this.invelTs = invelTs;
    }

    public int getInvCosto() {
        return invCosto;
    }

    public void setInvCosto(int invCosto) {
        this.invCosto = invCosto;
    }
    
    
    
}
