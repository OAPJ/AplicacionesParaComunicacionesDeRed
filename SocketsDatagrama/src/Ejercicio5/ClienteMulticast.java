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
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *
 * @author ovall
 */
public class ClienteMulticast {
    private int port = 4321;
    private String ip = "230.0.0.0";
    private DatagramChannel canal;
    private ByteBuffer buffer;
    private NetworkInterface interf;
    
    public ClienteMulticast() {
        try {
            canal = DatagramChannel.open();
            canal.bind(null);
            interf = NetworkInterface.getByInetAddress(InetAddress.getByName("127.0.0.1"));
            canal.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);
            System.out.println(interf.getName()+" multicast: "+interf.supportsMulticast());
        } catch (IOException e) {
        }
    }
    public void enviarMsg(String msg){
        try {
            buffer = ByteBuffer.wrap(msg.getBytes());
            InetSocketAddress isa = new InetSocketAddress(ip,port);
            canal.send(buffer, isa);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                canal.close();
            } catch (IOException e) {
            }
        }
    }
    
    public static void main(String[] args) {
        ClienteMulticast c = new ClienteMulticast();
        c.enviarMsg("Tengo hambre");
    }
}
