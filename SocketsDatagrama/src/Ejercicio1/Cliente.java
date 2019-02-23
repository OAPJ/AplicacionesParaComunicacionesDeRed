/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author ovall
 */
public class Cliente {
    private DatagramSocket cliente;
    private DatagramPacket recibido;
    private DatagramPacket enviado;
    private int puerto = 5000;
    
    public Cliente(){
        try {
            cliente = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recibir(){
        try {
            byte[] buffer = new byte[1024];
            recibido = new DatagramPacket(buffer, buffer.length);
            cliente.receive(recibido);
            System.out.println(new String(recibido.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviar(){
        try {
            byte[] buffer = "Hola servidor".getBytes();
            enviado = new DatagramPacket(buffer, buffer.length,InetAddress.getLocalHost(), puerto);
            cliente.send(enviado);
            System.out.println("Enviando");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Cliente c = new Cliente();
        c.enviar();
        c.recibir();
    }
}
