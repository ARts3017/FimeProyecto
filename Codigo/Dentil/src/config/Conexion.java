
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    Connection con;
    
    public Conexion(){ //Constructor 
    
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dentil?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","root","");
        }catch(SQLException e){
            e.printStackTrace();
        }   
    }
    
    public Connection getConnection(){
        return con;
    }
    
}
