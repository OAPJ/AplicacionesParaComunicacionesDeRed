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
public class Test {
    public static void main(String[] args) {
        Contenedor c = new Contenedor(10);
        Productor p = new Productor(c, "Pro 1");
        Productor p2 = new Productor(c, "Pro 2");
        Consumidor cs = new Consumidor(c, "Con 1");
        Consumidor cs2 = new Consumidor(c, "Con 2");
        Consumidor cs3 = new Consumidor(c, "Con 3");
        p.start();
        p2.start();
        cs.start();
        cs2.start();
        cs3.start();
    }
}
