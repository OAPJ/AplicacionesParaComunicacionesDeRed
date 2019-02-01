/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
/**
 *
 * @author ovall
 */
public class Servidor {
    private int puerto = 5000;
    private ServerSocket servidor;
    private Socket cliente;
    private DataInputStream entrada;
    private DataOutputStream salida;
    
    public Servidor(){
        try {
            servidor = new ServerSocket(puerto);
            while(true){
                cliente = servidor.accept();
                entrada  = new DataInputStream(cliente.getInputStream());
                //Mostrar lo que manda el cliente
                String ip = cliente.getInetAddress().getHostAddress();
                int numPuerto = cliente.getPort();
                System.out.println(ip+":"+numPuerto+" dice>> "+entrada.readUTF());
                // enviarle un mensaje al cliente
                salida = new DataOutputStream(cliente.getOutputStream());
                salida.writeUTF("Mensaje recibido.... que ondoa");
                //cierro conexion con el cliente
                cliente.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        Servidor s = new Servidor();
    }
}
