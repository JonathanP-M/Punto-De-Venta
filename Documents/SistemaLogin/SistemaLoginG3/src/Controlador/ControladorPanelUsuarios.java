/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Vista.PanelUsuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public class ControladorPanelUsuarios {
    //Atributos
    private PanelUsuario vista;
    private Usuario modelo;
    
    //Constructor

    public ControladorPanelUsuarios() {
        //Crear objetos vista y modelo
        this.vista=new PanelUsuario();
        this.modelo=new Usuario();
        //Llamar al metodo manejador eventos
        manejadorEventos();
        //llenar el metodo llenartablausuario
        llenarTablaUsuario();
    }
    
    //Metodos set y get

    public PanelUsuario getVista() {
        return vista;
    }

    public void setVista(PanelUsuario vista) {
        this.vista = vista;
    }

    public Usuario getModelo() {
        return modelo;
    }

    public void setModelo(Usuario modelo) {
        this.modelo = modelo;
    }
    
    //Metodo par amanejador de eventos 
    public void manejadorEventos(){
        //Agregar evento al boton de Registrar
        this.vista.btnRegistrar.addActionListener(e->registrar());
        
        //agregr evento al bpton de buscar
        this.vista.btnBuscar.addActionListener(e-> buscarId());
        
        //agregar evento editar
        this.vista.btnEditar.addActionListener(e-> editar());
        
        //agragra evento al boton de elminarv
        this.vista.btnEliminar.addActionListener(e ->eliminar());
    }
    
    //Metodo para registrar usuarios
    public void registrar(){
        //JOptionPane.showMessageDialog(this.vista, "Registrar Usuario");
        
        //obtener los datos del usuario de la vista  y agregarcelos al modelo
        this.modelo.setNombreUsuario(this.vista.TxtNombre.getText());
        this.modelo.setApellidoParternoUsuario(this.vista.TxtApellidoPaterno.getText());
        this.modelo.setApellidoMaternoUsuario(this.vista.TxtApellidoMaterno.getText());
        this.modelo.setEmailUsuario(this.vista.TxtEmail.getText());
        this.modelo.setTelefonoCleluarUSuario(this.vista.TxtTelefono.getText());
        
        
        //validar si se inserta el usuario
        
        if (this.modelo.insertar()) {
            //llamar al metoo llenarTablausuarios para que se muestren todos los uasrios en la tabla
                    llenarTablaUsuario();
            JOptionPane.showMessageDialog(this.vista,this.modelo.getMensajes());
             
        } else {
             JOptionPane.showMessageDialog(this.vista,this.modelo.getMensajes());
        }
        
        
        
        
        
    }//fin del meotod resgistar
    public void buscarId(){
    
    this.modelo.setIdUsuario(Integer.parseInt(this.vista.TxtUsuario.getText()));
        
        if (this.modelo.buscarPorID(this.modelo.getIdUsuario())) {
            this.vista.TxtNombre.setText(this.modelo.getNombreUsuario());
            this.vista.TxtApellidoPaterno.setText(this.modelo.getApellidoParternoUsuario());
            this.vista.TxtApellidoMaterno.setText(this.modelo.getApellidoMaternoUsuario());
            this.vista.TxtEmail.setText(this.modelo.getEmailUsuario());
            this.vista.TxtTelefono.setText(this.modelo.getTelefonoCleluarUSuario());
        } else {
            
        }
        //llamar al metoo llenarTablausuarios para que se muestren todos los uasrios en la tabla
        llenarTablaUsuario();
    }
    
    
    //metodo para agregar el arraylist ala tablausuarios
    public void llenarTablaUsuario(){
    
        this.vista.tablaUsuarios.setModel(obrenerModeloTabla());
        
    }//fin del metodo llenar tabla usuario
    
    //metodo para obtener el modelo tabla (llenar el arraylist al defaultTablemodel)
    
    public DefaultTableModel obrenerModeloTabla(){
        
    String encabezadoTabla[]={"ID","Nombre","Apellido paterno","Apellido Materno","email","telefono"};
    DefaultTableModel  modelotabla = new DefaultTableModel(encabezadoTabla,0);
    
    Object filas[]=new Object[modelotabla.getColumnCount()];
    
        for (Usuario usuario: this.modelo.buscar()) {
            
            filas[0]=usuario.getIdUsuario();
            filas[1]=usuario.getNombreUsuario();
            filas[2]=usuario.getApellidoParternoUsuario();
            filas[3]=usuario.getApellidoMaternoUsuario();
            filas[4]=usuario.getEmailUsuario();
            filas[5]=usuario.getTelefonoCleluarUSuario();
            
            modelotabla.addRow(filas);
            
        }
    
    return modelotabla;
    
    }
    
    public void editar(){
        //obtenr los datos formularios de la vista
        this.modelo.setIdUsuario(Integer.parseInt(this.vista.TxtUsuario.getText()));
        this.modelo.setNombreUsuario(this.vista.TxtNombre.getText());
        this.modelo.setApellidoParternoUsuario(this.vista.TxtApellidoPaterno.getText());
        this.modelo.setApellidoMaternoUsuario(this.vista.TxtApellidoMaterno.getText());
        this.modelo.setEmailUsuario(this.vista.TxtTelefono.getText());
        this.modelo.setTelefonoCleluarUSuario(this.vista.TxtTelefono.getText());
        
        //mandar a llmar el meodo modificar 
        
        if (this.modelo.modificar()) {
            
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            //llamr el metodo de llenar
            llenarTablaUsuario();
            
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    
    }
    
    
    
 //metdi para elimiar
    public void eliminar(){
     if (!this.vista.TxtUsuario.getText().trim().isEmpty()){
        
        //Obtener los datos de la vista y agregarlos al modelo
        this.modelo.setIdUsuario(Integer.parseInt(this.vista.TxtUsuario.getText()));


        if (this.modelo.eliminar(this.modelo.getIdUsuario())) {
            JOptionPane.showMessageDialog(this.vista, "Los datos de usuario se eliminaron correctamente...");
           //Limpiar cajas de texto
           
                   //llamar al metodo llenar tabla usuarios
        llenarTablaUsuario();
        } else {
            JOptionPane.showMessageDialog(this.vista, "Los datos de usuario no se eliminaron...");
        }
        }else{
         JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
         this.vista.TxtUsuario.requestFocus();
        }//Fin del meotodo validar cajas de texto

    
    }
    
}//fin de la clase
 