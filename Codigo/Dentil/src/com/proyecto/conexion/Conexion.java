/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Arturo
 */
public class Conexion {
    
  
    Connection con=null;
    
     public Connection con(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dentil","root","123");
               
            }catch(Exception e){
                e.printStackTrace();
            }
            
        
        
        return con;
    }
    
}
