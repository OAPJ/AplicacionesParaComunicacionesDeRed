/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class Servidor {
    private ServerSocket serv;
    private Socket sock;
    private int puerto;
    private String ip;
    private Mensaje msg;
    private int maxConexiones;
    
    public Servidor(int p, int mc){
        puerto = p;
        maxConexiones = mc;
        try {
            serv = new ServerSocket(puerto, maxConexiones);
            msg = new Mensaje();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recibirConexiones(){
        while(true){
            try {
                sock = serv.accept();
                System.out.println("CLeinte: "+sock.getInetAddress().getHostName()+" "+sock.getPort());
                ConexionCliente cc = new ConexionCliente(sock, msg);
                cc.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Servidor s = new Servidor(1234, 20);
        s.recibirConexiones();
    }
}
