/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sincronizacion;

import java.util.Random;

/**
 *
 * @author ovall
 */
public class Productor extends Thread {
    private Contenedor c;
    
    public Productor(Contenedor c, String n){
        super(n);
        this.c = c;
    }
    public void run(){
        for(int i=1; i<11; i++){
            c.Poner(i);
            System.out.println(getName()+" produjo: "+i);
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
