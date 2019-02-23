/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

/**
 *
 * @author ovall
 */

public class Servidor {
    private DatagramSocket servidor;
    private DatagramPacket recibir;
    private DatagramPacket enviar;
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    
    public Servidor(int puerto){
        try {
            servidor = new DatagramSocket(puerto);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public void recibirArchivo(String name){
        try {
            fos = new FileOutputStream(name);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            recibir = new DatagramPacket(buffer, buffer.length);
            do {                
                servidor.receive(recibir);
                bos.write(recibir.getData(), 0, recibir.getData().length);
            } while (recibir.getData().length == 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        } finally{
            try {
                fos.close();
                bos.close();
            } catch (IOException e) {
            }
        }
    }
    
    public static void main(String[] args) {
        Servidor s = new Servidor(1234);
        s.recibirArchivo("best-friend2.mp3");
    }
}
