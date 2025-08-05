/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author LuisE
 */
public class RolUsuario extends ConecxionBaseDatos implements CRUDinterface {
    //Atributos
    private int idRolUsuario;
    private String nombreRolUsuario;
    private String descripcionRolusuario;
    private String contraseniaLogin;
     CallableStatement cstmt;
    ResultSet result;
    
    //Constructor

    public RolUsuario() {
    }

    public RolUsuario(int idRolUsuario, String nombreRolUsuario, String descripcionRolusuario) {
        this.idRolUsuario = idRolUsuario;
        this.nombreRolUsuario = nombreRolUsuario;
        this.descripcionRolusuario = descripcionRolusuario;
        
        
    }
    
    //Matedos set y get

    public String getContraseñaLogin() {
        return contraseniaLogin;
    }

    public void setContraseñaLogin(String contraseñaLogin) {
        this.contraseniaLogin = contraseñaLogin;
    }
    

    public int getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(int idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getNombreRolUsuario() {
        return nombreRolUsuario;
    }

    public void setNombreRolUsuario(String nombreRolUsuario) {
        this.nombreRolUsuario = nombreRolUsuario;
    }

    public String getDescripcionRolusuario() {
        return descripcionRolusuario;
    }

    public void setDescripcionRolusuario(String descripcionRolusuario) {
        this.descripcionRolusuario = descripcionRolusuario;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "RolUsuario{" + "idRolUsuario=" + idRolUsuario + ",\n nombreRolUsuario=" + nombreRolUsuario + ",\n descripcionRolusuario=" + descripcionRolusuario + '}';
    }

    @Override
    public boolean insertar() {
        if (super.openConexionBD()) {
            //JOptionPane.showInternalMessageDialog(null,"se conecto ala base de datos bd"+super.getMensajes());
            try {
                //llamar el prosediemnto almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.sp_insertar_rolusuario(?,?);;");
                this.cstmt.setString(1, this.nombreRolUsuario);
                this.cstmt.setString(2, this.descripcionRolusuario);
            

                //ejecutre el procediemnto alamcenado
                this.cstmt.execute();
                //cerar la conecxion
                this.cstmt.close();
                super.getConexcion().close();
                super.setMensajes("se agurdaron correctamente los datos del usuario");
                return true;
            } catch (SQLException e) {
                super.setMensajes("eror de sql" + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "no se puede conectar..." + super.getMensajes());
        }
        return false;

    }
    

     @Override
    public ArrayList<RolUsuario> buscar() {
        ArrayList<RolUsuario> listaUsuarios = new ArrayList<RolUsuario>();

        if (super.openConexionBD()) {
            //JOptionPane.showInternalMessageDialog(null,"se conecto ala base de datos bd"+super.getMensajes());

            try {
                //llamar el prosediemnto almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.sp_consultar_todosrol();");

                //ejecutre el procediemnto alamcenado y agrega los datos a resultado
                this.result = this.cstmt.executeQuery();

                //recorer la consulta 
                while (this.result.next()) {

                    //crear objeto de usuario
                    RolUsuario rolusuario = new RolUsuario();

                    //agregagr los datos de la consulta  alos atributos del usuario
                    rolusuario.idRolUsuario = Integer.parseInt(this.result.getString(1));
                    rolusuario.nombreRolUsuario = this.result.getString(2);
                    rolusuario.descripcionRolusuario = this.result.getString(3);
                 

                    //agregar el objeo usuario ala lista 
                    listaUsuarios.add(rolusuario);

                }
                //cerar la conecxion
                this.cstmt.close();
                super.getConexcion().close();

                super.setMensajes("se consultaron correctamente los datos del usuario");

            } catch (SQLException e) {
                super.setMensajes("eror de sql" + e.getMessage());

            }

        } else {
            JOptionPane.showMessageDialog(null, "no se puede conectar..." + super.getMensajes());
        }

        return listaUsuarios;
    }

    @Override
    public boolean buscarPorID(int id) {
        this.idRolUsuario = id;
        if (super.openConexionBD()) {
            //JOptionPane.showInternalMessageDialog(null,"se conecto ala base de datos bd"+super.getMensajes());
            try {
                //llamar el prosediemnto almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.sp_consultar_idrol(?);");
                this.cstmt.setInt(1, this.idRolUsuario);

                //ejecutre el procediemnto alamcenado y agrega los datos a resultado
                this.result = this.cstmt.executeQuery();

                //recorer la consulta 
                while (this.result.next()) {
                    //agregagr los datos de la consulta  alos atributos del usuario
                    this.idRolUsuario = Integer.parseInt(this.result.getString(1));
                    this.nombreRolUsuario = this.result.getString(2);
                    this.descripcionRolusuario = this.result.getString(3);
                   

                }
                //cerar la conecxion
                this.cstmt.close();
                super.getConexcion().close();
                super.setMensajes("se consultaron correctamente los datos del usuario");
                return true;
            } catch (SQLException e) {
                super.setMensajes("eror de sql" + e.getMessage());

            }
             

        } else {
            JOptionPane.showMessageDialog(null, "no se puede conectar..." + super.getMensajes());
        }
        return false;

    }

    public boolean modificar(int id) {
        this.idRolUsuario = id;
        if (super.openConexionBD()) {
            try {
                //Llamar al procedimiento almacenado
                this.cstmt =super.getConexcion().prepareCall("call `bd_sistema-login`.sp_editar_rolUsuario(?, ?, ?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.cstmt.setString(2, this.nombreRolUsuario);
                this.cstmt.setString(3, this.descripcionRolusuario);

                //ejecutar el procedimiento almacenado
                this.cstmt.execute();
                //Cerrar conexión
                this.cstmt.close();
                super.getConexcion().close();

                return true;
            } catch (SQLException ex) {
                super.setMensajes("Error sql" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }

        return false;
        
    }

    @Override
    public boolean eliminar(int id) {
         this.idRolUsuario = id;
        if (super.openConexionBD()) {
            try {
                //Llamar al procedimiento almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.eliminar_rolusuario(?)");
                this.cstmt.setInt(1, this.idRolUsuario);

                //ejecutar el procedimiento almacenado
                this.cstmt.execute();
                //Cerrar conexión
                this.cstmt.close();
                super.getConexcion().close();

                return true;
            } catch (SQLException ex) {
                super.setMensajes("Error sql" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }

        return false;//fin del metodo insertarS
    
    }

    @Override
    public boolean modificar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
    