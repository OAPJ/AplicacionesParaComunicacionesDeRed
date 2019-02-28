/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *
 * @author ovall
 */
public class Cliente {
    private DatagramChannel canal;
    private ByteBuffer buffer;
    private int port = 5000;
    private String ip = "127.0.0.1";
    
    public Cliente(){
        try {
            canal = DatagramChannel.open();
            canal.configureBlocking(false);
            canal.bind(null);
            System.out.println("Cliente activo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void eniar(String msj){
        try {
            buffer = ByteBuffer.wrap(msj.getBytes());
            InetSocketAddress isa = InetSocketAddress.createUnresolved(ip, port);
            canal.send(buffer, isa);
            buffer.clear(); //Limpear para recibir
            canal.receive(buffer);
            System.out.println(new String(buffer.array()));
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.eniar("Hola servidor");
    }
}
