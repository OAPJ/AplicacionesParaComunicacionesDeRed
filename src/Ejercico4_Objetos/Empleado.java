/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercico4_Objetos;

import java.io.Serializable;

/**
 *
 * @author ovall
 */
public class Empleado implements Serializable{
    private String nombre;
    private String direccion;
    private long tel;
    private String correo;
    private long ns;
    private String curp;
    
    public Empleado(String nombe, String direccion){
        this.nombre = nombe;
        this.direccion = direccion;
        tel = 12345;
        correo = "default@def.ult";
        ns = 123456;
        curp = "MNHH7909";
    }

    public Empleado(String nombre, String direccion, long tel, String correo, long ns, String curp) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tel = tel;
        this.correo = correo;
        this.ns = ns;
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getNs() {
        return ns;
    }

    public void setNs(long ns) {
        this.ns = ns;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public String toString() {
        return "Nombre= "+ nombre + "\n direccion= " + direccion + "\n tel= " + tel + "\n correo= " + correo + "\n ns= " + ns + "\n curp= " + curp;
    }
    
    
    
}
