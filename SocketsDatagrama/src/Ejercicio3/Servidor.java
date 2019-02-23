/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Nombre: Juan Antonio Ovalle Patiño
 * Nombre del programa: Tarea 3: Envío y recepción de mensajes con multicast
 * Descripción: El cliente y el servidor pueden enviar y/o recebir mensajes pero con Multicast
 * Fecha: 22 / Febrero / 2019
 * @author ovall
 */

public class Servidor {
    private int port;
    private String group;
    private MulticastSocket ms;
    private DatagramPacket enviar;
    private DatagramPacket recibir;
    
    public Servidor(int port, String group){
        this.port = port;
        this.group = group;
        try {
            ms = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviar(){
        try {
            byte[] buffer = "Hola cliente".getBytes();
            enviar = new DatagramPacket(buffer, buffer.length,InetAddress.getByName(group), port);
            ms.send(enviar);
            System.out.println("Enviando");
        } catch (IOException e) {
            e.printStackTrace();
        }/*finally{
            ms.close();
        }*/
    }
    
    public void recibir(){
         try {
            ms = new MulticastSocket(port);
            ms.joinGroup(InetAddress.getByName(group));
            byte buffer[] = new byte[1024];
            this.recibir = new DatagramPacket(buffer, buffer.length);
            // Recibimos
            this.ms.receive(this.recibir);
            System.out.println(new String(recibir.getData()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        Servidor s = new Servidor(55400, "225.3.4.5");
        s.enviar();
        s.recibir();
    }
}
