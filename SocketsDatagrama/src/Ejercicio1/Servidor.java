/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author ovall
 */
public class Servidor {
    private DatagramSocket servidor;
    private DatagramPacket recibido;
    private DatagramPacket enviado;
    private int puerto;
    
    public Servidor(int port){
        puerto = port;
        try {
            servidor = new DatagramSocket(puerto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recibir(){
        try {
            byte[] buffer = new byte[1024];
            recibido = new DatagramPacket(buffer, buffer.length);
            servidor.receive(recibido);
            System.out.println(new String(recibido.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviar(){
        try {
            byte[] buffer = "Hola cliente".getBytes();
            enviado = new DatagramPacket(buffer, buffer.length,
                            recibido.getAddress(), recibido.getPort());
            servidor.send(enviado);
            System.out.println("Enviando");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Servidor s = new Servidor(5000);
        s.recibir();
        s.enviar();
    }
}
