/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorTenelt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class ServidorTelnet {
    private String ip;
    private int puerto;
    private ServerSocket serv;
    private Socket cliente;
    
    public ServidorTelnet(int puerto){
        this.puerto = puerto;
        try {
            serv = new ServerSocket(this.puerto);
            escuchar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void escuchar(){
        while(true){
            try {
                cliente  = serv.accept();
                //Gestionar con hilos
                HiloCliente h = new HiloCliente(cliente);
                h.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        ServidorTelnet s = new ServidorTelnet(1234);
        
    }
}
