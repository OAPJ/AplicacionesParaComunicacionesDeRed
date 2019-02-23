/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author ovall
 */

public class Cliente {
    private DatagramSocket socket;
    private DatagramPacket enviar;
    private DatagramPacket Recibir;
    private int nPaq;
    private File myFile;
    private BufferedInputStream bis;
    
    public Cliente(String a){
        try {
            socket = new DatagramSocket();
            myFile = new File(a);
            bis = new BufferedInputStream(new FileInputStream(myFile));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public void enviarArchivo(){
        nPaq = (int)Math.ceil((double)myFile.length()/1024);//redondea hacia arriba
        for(int i=0; i<nPaq; i++){
            byte[] buffer = new byte[1024];
            try {
                bis.read(buffer, 0, buffer.length);
                System.out.println("Paquete "+i);
                enviar = new DatagramPacket(buffer, buffer.length, 
                        InetAddress.getByName("127.0.0.1"), 1234);
                socket.send(enviar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Cliente c = new Cliente("best-friend.mp3");
        c.enviarArchivo();
    }
}