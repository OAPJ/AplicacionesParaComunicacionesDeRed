/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author ovall
 */
public class Pelicula implements Runnable{
    private Semaphore s;
    private String marca;
    
    public Pelicula(Semaphore se, String m){
        this.s = se;
        this.marca = m;
    }

    @Override
    public void run() {
        try {
            s.acquire();//Candado cerrado
            System.out.println("Se compro ticket para: "+marca);
            Thread.sleep(new Random().nextInt(5000)+1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }finally{
            s.release();
        }
    }
}
