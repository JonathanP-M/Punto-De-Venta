/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fredi
 */
public class ConecxionBaseDatos {
    
    //atributos
    private String driverBD;
    private String usuarioBD;
    private String passwordUsuarioBD;
    private String nombreBD;
    
    private Connection conexcion;
    private String mensajes;
    //construto

    public ConecxionBaseDatos() {
        this.driverBD="com.mysql.cj.jdbc.Driver";
        this.usuarioBD="root";
        this.passwordUsuarioBD="1234";
        this.nombreBD="jdbc:mysql://localhost:3306/bd_sistema-login";
        
    }

    public ConecxionBaseDatos(String driverBD, String usuarioBD, String passwordUsuarioBD, String nombreBD) {
        this.driverBD = driverBD;
        this.usuarioBD = usuarioBD;
        this.passwordUsuarioBD = passwordUsuarioBD;
        this.nombreBD = nombreBD;
    }
    //metodo set get

    public String getDriverBD() {
        return driverBD;
    }

    public void setDriverBD(String driverBD) {
        this.driverBD = driverBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getPasswordUsuarioBD() {
        return passwordUsuarioBD;
    }

    public void setPasswordUsuarioBD(String passwordUsuarioBD) {
        this.passwordUsuarioBD = passwordUsuarioBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public Connection getConexcion() {
        return conexcion;
    }

    public void setConexcion(Connection conexcion) {
        this.conexcion = conexcion;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
    //metod para estableser coneccion ala base de datos
    
    public boolean openConexionBD(){
        try {
             Class.forName(this.driverBD);
             this.conexcion=DriverManager.getConnection(this.nombreBD, this.usuarioBD,this.passwordUsuarioBD);
             if(this.conexcion!=null){
             this.mensajes="se establecio el servidor";
             return true;
             }
            
        } catch (Exception e) {
            this.mensajes="Error: "+e.getMessage();
        } 
    return false;
    
    
    }
    //metodo para cerrar la conexion ala base de datos 
    
    public boolean closeConexionBD(){
    if(this.conexcion!=null){
        try {
            this.conexcion.close();
        } catch (SQLException ex) {
           this.mensajes="erorr:"+ ex.getMessage();
        }
    }
    return false;
    }
    
    //metodo to String 

    @Override
    public String toString() {
        return "ConecxionBaseDatos{" + "mensajes=" + mensajes + '}';
    }
    
    
    
}
