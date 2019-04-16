/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforos2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ovall
 */
public class Contenedor {
    private int[]  buffer;
    private int pon = 0; 
    private int saca = 0;
    private Semaphore valoresDisponibles, espaciosDIsponibles;
    
    public Contenedor(int n){
        buffer = new int[n];
        valoresDisponibles = new Semaphore(0);
        espaciosDIsponibles = new Semaphore(n);
    }
    
    public void poner(int x){
        try {
            espaciosDIsponibles.acquire();
            ponerDato(x);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }finally{
            valoresDisponibles.release();
        }
    }

    private synchronized void ponerDato(int x) {
        int i = pon;
        buffer[pon] = x;
        pon = (++i == buffer.length) ? 0 : i;
    }
    
    public int sacar(){
        int val = 0;
        try {
            valoresDisponibles.acquire();
            val = sacarDato();
        } catch (InterruptedException ex) {
        }finally{
            espaciosDIsponibles.release();
        }
        return val;
    }

    private synchronized int sacarDato() {
        int i = saca;
        int val = buffer[i];
        saca = (++i == buffer.length) ? 0 : i;
        return val;
    }
}
