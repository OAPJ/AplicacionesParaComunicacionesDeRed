/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ovall
 */
public class Semaforos {
    
    public static void main(String[] args){
//        Semaphore sem =  new Semaphore(3, true);
//        String[] marcas = {"Toyota", "Nissan", "Ford", "Renault","Mercedez Benz", "Chevrolet", "Jaguar"};
//        Carro[] carros = new Carro[marcas.length];
//        for(int i=0; i<marcas.length; i++){
//            carros[i] = new Carro(sem, marcas[i]);
//            new Thread(carros[i]).start();
//        }
        
        int cajas = 2;
        Semaphore sem =  new Semaphore(cajas, true);
        String[] nombres = {"Pelicula1","Pelicula2","Pelicula3","Pelicula4","Pelicula5","Pelicula6", "Pelicula7"};
        //Pelicula[] pelicula = new Pelicula[nombres.length];
        for (String nombre : nombres) {
            //pelicula[i] = new Pelicula(sem, nombres[i]);
            new Thread(new Pelicula(sem, nombre)).start();
        }
    }
}
