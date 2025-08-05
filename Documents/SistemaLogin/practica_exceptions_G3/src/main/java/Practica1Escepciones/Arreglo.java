/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica1Escepciones;

import javax.swing.JOptionPane;

/**
 *
 * @author isaac
 */
public class Arreglo {
    //Atributos
    private int numeros[];
    private int tam;
    private String mensajes;
    
    //Constructor

    public Arreglo() {
    }

    public Arreglo(int tam) {
        this.tam = tam;
        //Crear el arreglo
        this.numeros = new int [this.tam];
    }
    

    public Arreglo(int[] numeros) {
        this.numeros = numeros;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
    
    //Metodo para crear el arreglo
    public void crearArreglo(){
        //Crear arreglo
        this.numeros = new int[this.tam];
    }
    
    //Metodo para solicitar los elementos del arreglo
    public void solicitarNumeros(){
        for (int i = 0; i < this.numeros.length; i++) {
            
            try{
            this.numeros[i]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor"));  
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Debes ingresar un numero entero" + e.getMessage());
            }
        }
    }
    
    //Metodo para solicitar el indice del arreglo
    public void solicitarIndice(){
        try{
        int indice=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el indice del valor"));
        JOptionPane.showMessageDialog(null, "El valor del indice es: " + this.numeros[indice]);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Debes ingresar un numero entero" + e.getMessage());
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"Debes ingresar un indice del rango 0," + (this.numeros.length - 1) + e.getMessage());
        }
    }
    
    //Metodo para imprimir arreglo
    public String imprimirArreglo(){
        String valoresArreglo="";
        
        for (int i = 0; i < this.numeros.length; i++){
            valoresArreglo+=", " + numeros[i];
        }
        return valoresArreglo;
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "Arreglo{" + "numeros=" + imprimirArreglo() + '}';
    }
    
} 
