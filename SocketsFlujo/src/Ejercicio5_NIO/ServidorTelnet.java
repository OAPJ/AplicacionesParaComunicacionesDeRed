/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5_NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author ovall
 */
public class ServidorTelnet {
    private ServerSocketChannel ssc;
    private SocketChannel sc;
    private ByteBuffer buffer;
    private int puerto;
    
    public ServidorTelnet(int port){
        try {
            puerto = port;
            ssc = ServerSocketChannel.open();
            //Ligar al puerto
            ssc.socket().bind(new InetSocketAddress(puerto));
            //Congigurar el servidor en modo no bloqueante
            ssc.configureBlocking(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void escuchar(){
        try {
            //Enviar mensaje al cliente
            buffer = ByteBuffer.wrap("Mensaje recibido".getBytes());
            System.out.println("");
            while(true){
                System.out.println("Esperando conexiones");
                sc = ssc.accept();
                if(sc == null){
                    //HAcer una pausa hasta que alguien se conecte
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Se conecto: "+sc.socket().toString());
                    //Resetea la posicion
                    buffer.rewind();
                    //Mandar ell msj al cliente
                    sc.write(buffer);
                    int bytesRead = sc.read(buffer);
                    while(bytesRead != -1){
                        //System.out.println("Read "+bytesRead);
                        //Ponerlo en lectura o escritura
                        buffer.flip();
                        while(buffer.hasRemaining())
                            System.out.print((char) buffer.get());
                        buffer.clear();
                        bytesRead = sc.read(buffer);
                    }
                    sc.close();
                    System.out.println("");
                }
                System.out.println("Algo");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args){
        ServidorTelnet st = new ServidorTelnet(1234);
        st.escuchar();
    }
    
}
