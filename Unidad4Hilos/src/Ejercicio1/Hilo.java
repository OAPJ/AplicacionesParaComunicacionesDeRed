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
public class Hilo extends Thread{
    private int num;
    
    public Hilo(int n, String nom){
        num = n;
        this.setName(nom);
    }
    
    public void run(){
        for(int i=0; i<num; i++){
            System.out.println((i+1)+" => "+this.getName());
            try {
                Hilo.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Hilo h = new Hilo(15, "1");
        Hilo h2 = new Hilo(10, "2");
        Hilo h3 = new Hilo(5, "3");
        h.setPriority(10);
        h2.setPriority(1);
        h3.setPriority(1);
        h.start();
//        h.yield();
        h2.start();
        h3.start();
        try {
            h.join();
            h2.join();
            h3.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Adios");
    }
}
