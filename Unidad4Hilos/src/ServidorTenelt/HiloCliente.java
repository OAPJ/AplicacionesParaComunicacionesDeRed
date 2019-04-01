/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorTenelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class HiloCliente extends Thread{
    private Socket cliente;
    private PrintWriter salida;
    
    public HiloCliente(Socket s){
        cliente = s;
    }
    
    public void run(){
        try {
            salida = new PrintWriter(cliente.getOutputStream());
            System.out.println("Cliente: "+cliente.getInetAddress().getHostName()+" Puerto: "+cliente.getPort());
            salida.print("Hola cliente");
            salida.close();
            cliente.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
