
package Modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author
 */
public  class Usuario extends ConecxionBaseDatos implements CRUDinterface {

    //Seccion Atributos
    private int idUsuario;
    private String nombreUsuario;
    private String apellidoParternoUsuario;
    private String apellidoMaternoUsuario;
    private String emailUsuario;
    private String telefonoCleluarUSuario;
    CallableStatement cstmt;
    ResultSet result;

    //Constructor= Sirve a para inicializar los atrivbutos
    public Usuario() {
    }
     


    public Usuario(int idUsuario, String nombreUsuario, String apellidoParternoUsuario, String apellidoMaternoUsuario, String emailUsuario, String telefonoCleluarUSuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoParternoUsuario = apellidoParternoUsuario;
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoCleluarUSuario = telefonoCleluarUSuario;
    }

    //Metodos SET y GET= Get obtiene el valor del atributo, SET establece el nombre del atributo
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoParternoUsuario() {
        return apellidoParternoUsuario;
    }

    public void setApellidoParternoUsuario(String apellidoParternoUsuario) {
        this.apellidoParternoUsuario = apellidoParternoUsuario;
    }

    public String getApellidoMaternoUsuario() {
        return apellidoMaternoUsuario;
    }

    public void setApellidoMaternoUsuario(String apellidoMaternoUsuario) {
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefonoCleluarUSuario() {
        return telefonoCleluarUSuario;
    }

    public void setTelefonoCleluarUSuario(String telefonoCleluarUSuario) {
        this.telefonoCleluarUSuario = telefonoCleluarUSuario;
    }
 
    //Metodo toString= 
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ",\n nombreUsuario=" + nombreUsuario + ",\n apellidoParternoUsuario=" + apellidoParternoUsuario + ",\n apellidoMaternoUsuario=" + apellidoMaternoUsuario + ",\n emailUsuario=" + emailUsuario + ",\n telefonoCleluarUSuario=" + telefonoCleluarUSuario + '}';
    }

    @Override
    public boolean insertar() {
        if (super.openConexionBD()) {
            //JOptionPane.showInternalMessageDialog(null,"se conecto ala base de datos bd"+super.getMensajes());
            try {
                //llamar el prosediemnto almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.sp_insertar_usuario(?, ?, ?, ?, ?);");
                this.cstmt.setString(1, this.nombreUsuario);
                this.cstmt.setString(2, this.apellidoParternoUsuario);
                this.cstmt.setString(3, this.apellidoMaternoUsuario);
                this.cstmt.setString(4, this.emailUsuario);
                this.cstmt.setString(5, this.telefonoCleluarUSuario);

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
    public ArrayList<Usuario> buscar() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        if (super.openConexionBD()) {
            //JOptionPane.showInternalMessageDialog(null,"se conecto ala base de datos bd"+super.getMensajes());

            try {
                //llamar el prosediemnto almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.sp_consultar_todos_usuarios();");

                //ejecutre el procediemnto alamcenado y agrega los datos a resultado
                this.result = this.cstmt.executeQuery();

                //recorer la consulta 
                while (this.result.next()) {

                    //crear objeto de usuario
                    Usuario usuario = new Usuario();

                    //agregagr los datos de la consulta  alos atributos del usuario
                    usuario.idUsuario = Integer.parseInt(this.result.getString(1));
                    usuario.nombreUsuario = this.result.getString(2);
                    usuario.apellidoParternoUsuario = this.result.getString(3);
                    usuario.apellidoMaternoUsuario = this.result.getString(4);
                    usuario.emailUsuario = this.result.getString(5);
                    usuario.telefonoCleluarUSuario = this.result.getString(6);

                    //agregar el objeo usuario ala lista 
                    listaUsuarios.add(usuario);

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
        this.idUsuario = id;
        if (super.openConexionBD()) {
            //JOptionPane.showInternalMessageDialog(null,"se conecto ala base de datos bd"+super.getMensajes());
            try {
                //llamar el prosediemnto almacenado
                this.cstmt = super.getConexcion().prepareCall("call `bd_sistema-login`.sp_consultar_id_usuarios(?);");
                this.cstmt.setInt(1, this.idUsuario);

                //ejecutre el procediemnto alamcenado y agrega los datos a resultado
                this.result = this.cstmt.executeQuery();

                //recorer la consulta 
                while (this.result.next()) {
                    //agregagr los datos de la consulta  alos atributos del usuario
                    this.idUsuario = Integer.parseInt(this.result.getString(1));
                    this.nombreUsuario = this.result.getString(2);
                    this.apellidoParternoUsuario = this.result.getString(3);
                    this.apellidoMaternoUsuario = this.result.getString(4);
                    this.emailUsuario = this.result.getString(5);
                    this.telefonoCleluarUSuario = this.result.getString(6);

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

    @Override
    public boolean modificar() {
        
        if (super.openConexionBD()) {
            
            //Lammar el prosedemiento alamcenado de editar
            try {
            this.cstmt=super.getConexcion().prepareCall("call `bd_sistema-login`.sp_actualizar_usuario(?, ?, ?, ?, ?, ?);");
            this.cstmt.setInt(1, this.idUsuario);
            this.cstmt.setString(2, this.nombreUsuario);
            this.cstmt.setString(3,this.apellidoParternoUsuario);
            this.cstmt.setString(4,this.apellidoParternoUsuario);
            this.cstmt.setString(5, this.emailUsuario);
            this.cstmt.setString(6, this.telefonoCleluarUSuario);
            
            //ejeucra
            this.cstmt.execute();
            super.setMensajes("se modificaron correctamente los datos ");
            //cerrar conexcion
            this.cstmt.close();
            this.getConexcion().close();
            
            }catch(SQLException ex){
            
            super.setMensajes("eror de aql:"+ ex.getMessage());
            }
        } else {
            super.setMensajes("error no se pued conectar al servidor ala servidor ");
        }
        
        
        
        return false;
    }
    @Override
    public boolean eliminar(int id) {
        this.idUsuario=id;
        if (super.openConexionBD()) {
            
            //Lammar el prosedemiento alamcenado de editar
            try {
            this.cstmt=super.getConexcion().prepareCall("call sp_eleminar_Usuario(?);");
            this.cstmt.setInt(1,this.idUsuario);
          
            //ejeucra
            this.cstmt.execute();
            super.setMensajes("se modificaron correctamente los datos ");
            
            //cerrar conexcion
            this.cstmt.close();
            this.getConexcion().close();
            
            }catch(SQLException ex){
            
            super.setMensajes("eror de aql:"+ ex.getMessage());
            }
            
            return true;
        } else {
            super.setMensajes("error no se pued conectar al servidor ala servidor ");
        }
        
        
        
        return false;
    }

    
    

}
