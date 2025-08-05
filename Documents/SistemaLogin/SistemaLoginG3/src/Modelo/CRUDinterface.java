/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Fredi
 */
public interface CRUDinterface {
    
    public boolean insertar();
    public ArrayList  buscar();
    public boolean  buscarPorID(int id);
    public boolean modificar();
    public boolean eliminar(int id);
    
    
}
