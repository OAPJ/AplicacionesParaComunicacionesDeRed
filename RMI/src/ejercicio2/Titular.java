/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;
import java.io.Serializable;
/**
 *
 * @author ovall
 */
public class Titular implements Serializable{
    private String nombre;
    private int clave;
    private String direccion;
    private String correo;
    
    public Titular(String n, int c){
        this.nombre = n;
        this.clave = c;
        this.direccion = "";
        this.correo = "";
    }
    
    public Titular(String n, int c, String d, String co){
        this.nombre = n;
        this.clave = c;
        this.direccion = d;
        this.correo = co;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the clave
     */
    public int getClave() {
        return clave;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Titular:" + "Nombre = " + nombre + ", Clave = " + clave + ", Direccion = " + direccion + ", Correo = " + correo;
    }
}
