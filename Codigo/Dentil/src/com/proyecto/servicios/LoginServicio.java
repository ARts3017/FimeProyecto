/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.servicios;

import com.proyecto.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.proyecto.entidades.Login;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * * @author Arturo
 */
public class LoginServicio {
    
 
    PreparedStatement pstm;
    ResultSet rs;
    Conexion con;
    Login login;
    
    public LoginServicio(){
        con=new Conexion();
    }
    
    public boolean validacionCredencialesIngreso(String usr,String pwd){
        boolean acceso=false;
        
        try{
            pstm=con.con().prepareStatement("SELECT * FROM login WHERE usr=? AND pwd=?");
            pstm.setString(1, usr);
            pstm.setString(2, pwd);
            rs=pstm.executeQuery();
            Login l=new Login();
            if(!rs.isFirst()){
                acceso=true;
                
                while(rs.next()){
                     l.setId(rs.getInt("id"));
                     l.setUsr(rs.getString("usr"));
                     l.setPwd(rs.getString("pwd"));
                }
         
            }else{
               l.setId(0);
               l.setUsr("null");
               l.setPwd("null");     
            }
              
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                this.con.con().close();
                pstm.close();
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return acceso;
        
    }
    
    public void loginIngresoUsuario(Login l){
        System.out.println("USUARIO---->"+l.getUsr());
        
    }
    
}
