/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *
 * @author ovall
 */

public class Servidor {
    private DatagramChannel canal;
    private ByteBuffer buffer;
    private int port = 5000;
    
    public Servidor(){
        try {
            canal = DatagramChannel.open(); //Abrimos el canal de comunicacion
            canal.configureBlocking(false);
            InetSocketAddress isa = new InetSocketAddress("127.0.0.1",port); //Enlasar el canal con una dirrecion
            canal.bind(isa);
            System.out.println("Servidor activo en: "+isa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void revibir(){
        try {
            buffer = ByteBuffer.allocate(1024);
            SocketAddress sa = canal.receive(buffer);
            buffer.flip(); //Preparando para lectura
            System.out.println(new String(buffer.array()));
            canal.send(buffer, sa);
            canal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Servidor s = new Servidor();
        s.revibir();
    }
}
