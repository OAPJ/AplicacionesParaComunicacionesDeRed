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
import java.net.UnknownHostException;

/**
 * Nombre: Juan Antonio Ovalle Patiño
 * Nombre del programa: Tarea 3: Envío y recepción de mensajes con multicast
 * Descripción: El cliente y el servidor pueden enviar y/o recebir mensajes pero con Multicast
 * Fecha: 22 / Febrero / 2019
 * @author ovall
 */

public class Cliente {
    private int port;
    private String group;
    private MulticastSocket ms;
    private DatagramPacket recibir;
    private DatagramPacket enviar;
    
    public Cliente(int port, String group){
        this.port = port;
        this.group = group;
        try {
            ms = new MulticastSocket(this.port);
            ms.joinGroup(InetAddress.getByName(this.group));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recibir(){
        try {
            byte[] buffer = new byte[1024];
            recibir = new DatagramPacket(buffer, buffer.length);
            ms.receive(recibir);
            System.out.println(new String(recibir.getData()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }/*finally{
            ms.close();
        }*/
    }
    
    public void enviar(){
        try {
            ms = new MulticastSocket();
            byte buffer[] = "Hola".getBytes();
            this.enviar = new DatagramPacket(buffer, buffer.length,
                InetAddress.getByName(this.group), this.port);
            ms.send(this.enviar);
            System.out.println("Enviado");
            // Se abandona el grupo
            //this.ms.leaveGroup(InetAddress.getByName(this.group));
        }
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            this.ms.close();
        }
    }
    
    
    public static void main(String[] args) {
        Cliente c = new Cliente(55400, "225.3.4.5");
        c.recibir();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        c.enviar();
    }
}
