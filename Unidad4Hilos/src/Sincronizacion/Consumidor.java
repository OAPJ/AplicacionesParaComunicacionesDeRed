/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sincronizacion;

/**
 *
 * @author ovall
 */
public class Consumidor extends Thread{
    private Contenedor c;
    
    public Consumidor(Contenedor c, String n){
        super(n);
        this.c = c;
    }
    
    public void run(){
        int val = 0;
        for(int i=1; i<11; i++){
            val = c.sacar();
            System.out.println(getName()+" consume: "+val);
        }
    }
}
