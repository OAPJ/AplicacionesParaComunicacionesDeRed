/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author ovall
 */
public class Roca extends JLabel implements Runnable {
    private JLabel jl;
    private int distancia;
    private int x, i;
    
    public Roca(JLabel jl, int d){
        this.jl = jl;
        x = jl.getX();
        this.distancia = d;
    }
    
    public void run(){
        int velocidad = (int) (Math.random()*5+1)*10;
        for(i=distancia; i>0; i--){
            try {
                jl.setLocation(x, i);
                Thread.sleep(velocidad);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        i = -1;
        
    }
    public int getI(){
        return i;
    }
}
