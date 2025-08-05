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
public class testArreglo {
    //Declarar el objeto arreglo
        static Arreglo arreglo;
        static Arreglo arreglo2;
        
    public static void main(String[] args) {
        //Crear objeto arreglo
        arreglo=new Arreglo(3);
        
        //Crear objeto arreglo2
        
        arreglo2 = new Arreglo();
        arreglo2.setTam(5);
        arreglo2.crearArreglo();
        
        //Solicitar los numeros para el arreglo2
        arreglo2.solicitarNumeros();
        
        //Solicitar los numeros
        arreglo.solicitarNumeros();
        
        //Imprimir arreglo
        JOptionPane.showMessageDialog(null, arreglo);
        
        //Solicitar indice
        arreglo.solicitarIndice();
    }
}
