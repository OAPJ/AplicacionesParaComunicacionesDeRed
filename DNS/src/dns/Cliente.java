/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dns;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ovall
 */
public class Cliente {
    private DatagramSocket cliente;
    private DatagramPacket recibido;
    private DatagramPacket enviado;
    private ByteArrayInputStream array;
    private ObjectInputStream ops;
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
            byte[] data = new byte[4];
            recibido = new DatagramPacket(data, data.length);
            cliente.receive(recibido);
            int len = 0;
            for(int i=0; i<4; i++)
                len |= (data[3-i] & 0Xff) << (i << 3);
            byte[] buffer = new byte[len];
            recibido = new DatagramPacket(buffer, buffer.length);
            cliente.receive(recibido);
            array = new ByteArrayInputStream(buffer);
            ops = new ObjectInputStream(array);
            ArrayList<String> direcciones = (ArrayList <String>)ops.readObject();
            
            System.out.println(direcciones.size());
            for(String d: direcciones){
                System.out.println(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviar(String dom){
        try {
            byte[] buffer = dom.getBytes();
            enviado = new DatagramPacket(buffer, buffer.length,InetAddress.getLocalHost(), puerto);
            cliente.send(enviado);
            System.out.println("Enviando");
            recibir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Cliente c = new Cliente();
        c.enviar("https://www.google.com/");
        //c.recibir();
    }
}
