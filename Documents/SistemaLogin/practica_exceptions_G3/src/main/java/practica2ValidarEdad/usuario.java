/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2ValidarEdad;

import javax.swing.JOptionPane;

/**
 *
 * @author isaac
 */
public class usuario {
    //Atributos
    int edad;
    
    //Metodo para validar la edad
    public void validarEdad(int age){
        this.edad = age;
        
        if (this.edad<18) {
            throw new ArithmeticException("Acceso denegado: Eres menor de Edad");
        } else {
            JOptionPane.showMessageDialog(null, "Acceso Permitido: Eres mayor de edad");
        }
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "usuario{" + "edad=" + edad + '}';
    }
    
}
