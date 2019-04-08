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
public class Contenedor {
    private int[] contenedor;
    private int aux, n;
    
    private int dato;
    private boolean hayDato;
    
    public Contenedor(int n){
        aux = 0;
        this. n = n;
        contenedor = new int[this.n];
    }
    
    
    public synchronized void Poner(int x){
        while(aux >= n){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contenedor[aux++] = x;
        notifyAll();
    }
    
    public synchronized int sacar(){
        while(aux > n || aux == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dato = contenedor[(aux-1)];
        aux--;
        notifyAll();
        return dato;
    }
}
