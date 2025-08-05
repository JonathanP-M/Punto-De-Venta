/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RolUsuario;
import Vista.PanelRolUsuarios;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
    public class ControladorPanelRolUsuarios {
    private PanelRolUsuarios vista;
    private RolUsuario modelo;
    
    public ControladorPanelRolUsuarios(){
        this.vista=new PanelRolUsuarios();
        this.modelo=new RolUsuario();
        manejadorEventos();
        LlenarTablaRolUsuarios();
        
        
        
    }
    
    //Metodos set y get 

    public PanelRolUsuarios getVista() {
        return vista;
    }

    public void setVista(PanelRolUsuarios vista) {
        this.vista = vista;
    }

    public RolUsuario getModelo() {
        return modelo;
    }

    public void setModelo(RolUsuario modelo) {
        this.modelo = modelo;
    }
    
    public void manejadorEventos(){
     this.vista.btnRolRegistrar.addActionListener(e->Registrar());
        
        //agregr evento al bpton de buscar
       this.vista.btnRolBuscar.addActionListener(e-> BuscarId());
        
        //agregar evento editar
       this.vista.btnRolEditar.addActionListener(e-> editar());
        
        //agragra evento al boton de elminarv
        this.vista.btnRolEliminar.addActionListener(e ->eliminarRol());
        
        //evento para el boton nuevo
        this.vista.btnRolNuevo.addActionListener(e-> nuevoRol());
        
        //evento para el boton salir
        this.vista.btnRolSalir.addActionListener(e-> salirRol());
    }
    
   public void Registrar(){
        //JOptionPane.showMessageDialog(this.vista, "Registrar Usuario");
        
        //obtener los datos del usuario de la vista  y agregarcelos al modelo
        this.modelo.setNombreRolUsuario(this.vista.TxtRolNombre.getText());
        this.modelo.setDescripcionRolusuario(this.vista.TxtRolObservaciones.getText());

        
        
        //validar si se inserta el usuario
        
        if (this.modelo.insertar()) {
            //llamar al metoo llenarTablausuarios para que se muestren todos los uasrios en la tabla
                   // llenarTablaUsuario();
            JOptionPane.showMessageDialog(this.vista,this.modelo.getMensajes());
             
        } else {
             JOptionPane.showMessageDialog(this.vista,this.modelo.getMensajes());
        }
        
        
         }
    
  public void BuscarId(){
    
    this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtRolUsuario.getText()));
        
        if (this.modelo.buscarPorID(this.modelo.getIdRolUsuario())) {
            this.vista.TxtRolNombre.setText(this.modelo.getNombreRolUsuario());
            this.vista.TxtRolObservaciones.setText(this.modelo.getDescripcionRolusuario());
           
        } else {
            
        }
        //llamar al metoo llenarTablausuarios para que se muestren todos los uasrios en la tabla
         //llenarTablaUsuario();
    }
  
    //Metodo para editar
    public void editar(){
       
        //Validar cajas de texto
        if(validarCajasTexto()){
        
        
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtRolUsuario.getText()));
        this.modelo.setNombreRolUsuario(this.vista.TxtRolNombre.getText());
        this.modelo.setDescripcionRolusuario(this.vista.TxtRolObservaciones.getText());
        


        if (this.modelo.modificar(this.modelo.getIdRolUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos de usuario se editaron correctamente");
           //Limpiar cajas de texto
           limpiarCajasTexto();
           
                   //llamar al metodo llenar tabla usuarios
        LlenarTablaRolUsuarios();
        } else {
            JOptionPane.showMessageDialog(this.vista, "Los datos de usuario no se editaron");
        }
        }else{
         JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }//Fin del meotodo validar cajas de texto
        
        
    }//fin del metodo editar
  
  
         public void eliminarRol(){
       
        //Validar cajas de texto
      if (!this.vista.txtRolUsuario.getText().trim().isEmpty()){
        
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtRolUsuario.getText()));


        if (this.modelo.eliminar(this.modelo.getIdRolUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos de usuario se eliminaron correctamente");
           //Limpiar cajas de texto
           limpiarCajasTexto();
                   //llamar al metodo llenar tabla usuarios
        LlenarTablaRolUsuarios();
        } else {
            JOptionPane.showMessageDialog(this.vista, "Los datos de usuario no se eliminaron");
        }
        }else{
         JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
         this.vista.txtRolUsuario.requestFocus();
        }//Fin del meotodo validar cajas de texto
        
        
    }//fin del metodo eliminar
         
          public boolean validarCajasTexto(){
        try {
            if (this.vista.TxtRolNombre.getText().trim().isEmpty()) {
               JOptionPane.showMessageDialog(this.vista, "Debes ingresar el nombre de usuario");
               this.vista.TxtRolNombre.requestFocus();
               return false;
               
            } if (this.vista.TxtRolObservaciones.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.vista, "Debes ingresar el apellido Paterno");
               this.vista.requestFocus();
               return false;

            } 
                return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.vista, "Error" + e.getMessage());
             return false;
        }
         
    }
         
         //Metodo para limpiar las cajas de texto
        public void limpiarCajasTexto(){
        this.vista.txtRolUsuario.setText("");
        this.vista.TxtRolNombre.setText("");
        this.vista.TxtRolObservaciones.setText("");
        }
        
        //metodo para nuevo
        public void nuevoRol(){
         //Limpiar Cajastexto
         limpiarCajasTexto();
         this.vista.txtRolUsuario.requestFocus();
        }
        
        
        //metodo para salir
        public void salirRol() {
         //ocular el panel usuario
         this.vista.setVisible(false);
            
        }
        
         //llenar la tabla usuarios
        public void LlenarTablaRolUsuarios() {
            this.vista.tablaRol.setModel(obtenerDatosRolUsuarios()); 
        }
       
        //obtener los datos de usuario
       public DefaultTableModel obtenerDatosRolUsuarios() {
           
          String encabezadoTabla[] = {"Id Rol Usuario", "Descripcion Rol Usuario","Tipo Rol Usuario"};
          DefaultTableModel modeloTabla = new DefaultTableModel(encabezadoTabla ,0);
                
        Object[] fila = new Object[modeloTabla.getColumnCount()];
        
        
        //Agregar los datos del objeto usuario del arraylist al modelotabla
        for( RolUsuario rolusuario : this.modelo.buscar()){
            fila[0] = rolusuario.getIdRolUsuario();
            fila[1] = rolusuario.getDescripcionRolusuario();
            fila[2] = rolusuario.getNombreRolUsuario();

            
            modeloTabla.addRow(fila);
        }
        
        return modeloTabla;
         
       }   
         
     }
    
        
        
