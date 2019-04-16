/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforos2;

/**
 *
 * @author ovall
 */
public class Consumidor extends Thread{
    Contenedor c;
    
    public Consumidor(Contenedor c, String n){
        super(n);
        this.c = c;
    }
    
    @Override
    public void run(){
        for(int i=1; i<11; i++){
            System.out.println(getName()+ " saco "+ c.sacar());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
