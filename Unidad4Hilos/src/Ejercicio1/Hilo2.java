/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

/**
 *
 * @author ovall
 */
public class Hilo2 implements Runnable {
    
    private int n;
    private String name;
    
    public Hilo2(int n, String name){
        this.n = n;
        this.name = name;
    }
    
    public void run(){
        for(int i=0; i<n; i++)
            System.out.println((i+1)+" == "+name);
    }
    
    public static void main(String[] args){
        Hilo2 h = new Hilo2(10, "Patricio Estrella");
        Hilo2 h2 = new Hilo2(10, "Bob esponja");
        Thread t1 = new Thread(h);
        t1.start();
        Thread t2 = new Thread(h2);
        t2.start();
    }
    
}
