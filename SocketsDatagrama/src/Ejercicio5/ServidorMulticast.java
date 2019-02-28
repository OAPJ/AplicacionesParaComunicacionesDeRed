/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

/**
 *
 * @author ovall
 */
public class ServidorMulticast {
    private int port = 4321;
    private String ip = "230.0.0.0";
    private DatagramChannel canal;
    private ByteBuffer buffer;
    private NetworkInterface interf;
    private MembershipKey key;
    
    public ServidorMulticast() {
        try {
            canal = DatagramChannel.open(StandardProtocolFamily.INET);
            interf = NetworkInterface.getByInetAddress(InetAddress.getByName("127.0.0.1"));
            canal.bind(new InetSocketAddress(port));
            canal.setOption(StandardSocketOptions.SO_REUSEADDR, true); //Se pueden reutilizar las direccion
            canal.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf); // Se va a estables como multicast
            key =canal.join(InetAddress.getByName(ip), interf);
            System.out.println("Gupo: "+key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void resibirMsg(){
        try {
            System.out.println("Esperando mensaje");
            buffer = ByteBuffer.allocate(1024);
            canal.receive(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array()));
            key.drop();//Liberar la clave
            canal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        ServidorMulticast s = new ServidorMulticast();
        s.resibirMsg();
    }
}
