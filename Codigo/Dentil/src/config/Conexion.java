
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    Connection con=null;
    
    public Connection Conexion(){ //Constructor 
    
        try{
             Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dentil","root","123");
        }catch(Exception e){
            e.printStackTrace();
        }   
        return con;
    }
    
    public Connection getConnection(){
        return con;
    }
    
}
