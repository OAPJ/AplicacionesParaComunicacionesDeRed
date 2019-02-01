/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Nombre: Juan Antonio Ovalle PAtiño
 * Nombre del programa: Practica 1: Construcción de un servicio que simule al comando
 *                                  ‘echo’ utilizando sockets orientados a conexión
 * Descripción: El cliente tiene que ingresar un ip, un mensjae y si quiere el puerto y se verifica que sean validos el ip y el puerto
 *              Se manda el mensaje al cliente y el servidor devuelve el mismo mensaje.
 * Fecha: 1 / Febrero / 2019
 * @author ovall
 */

public class Servidor {
    private ServerSocket servidor;
    private Socket cliente;
    private int puerto;
    private DataInputStream entrada; 
    private DataOutputStream salida;
   // private String msj;
    
    public Servidor(int port){
        try{
            puerto = port;
            this.servidor = new ServerSocket(puerto);
            cliente = servidor.accept();
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream (cliente.getOutputStream());
            String msj = entrada.readUTF();
            salida.writeUTF(msj);
            System.out.println("Cliente dice>> "+msj);
            cliente.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Servidor s = new Servidor(5500);
    }
}
