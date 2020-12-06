/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.controller;

import com.proyecto.servicios.LoginServicio;
import com.proyecto.entidades.Login;

/**
 *
 * @author Arturo
 */
public class LoginControll {
    private LoginServicio servicio;
    public LoginControll(){
        
    }
    
    public void controladorLogin(Login login){
        boolean acceso;
        servicio=new LoginServicio();
        acceso=servicio.validacionCredencialesIngreso(login.getUsr(), login.getPwd());
        
        System.out.println("ACCESO->"+acceso);
        
        
    }
    
    
}
