/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2ValidarEdad;

/**
 *
 * @author isaac
 */
public class testUsuario {
    public static void main(String[] args) {
        //Crear objeto
        usuario usuari1 = new usuario();
        
        //Solicitar edad
        usuario1.setEdad(Integer.parseInt(JOptionPane.showMessageDialog(null,"Ingresa tu edad")))
    }
}
