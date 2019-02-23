/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;
import java.io.BufferedReader;
//import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Nombre: Juan Antonio Ovalle PAtiño
 * Nombre del programa: Servidor Telnet
 * Descripción: En este programa se implementó un servidor, con cual se realizó una conexión 
 * mediante el servicio telnet y el CMD de la computadora
 * Fecha: 28 / Enero / 2019
 * @author ovall
 */
public class ServidorTelnet {
    private ServerSocket servidor;
    private Socket cliente;
    private int puerto;
   // private DataInputStream  entrada;
    private BufferedReader entrada; 
    private DataOutputStream salida;
    


    public ServidorTelnet(int port){
        try{
            puerto = port;
            this.servidor = new ServerSocket(puerto);
            while(true){
                cliente = servidor.accept();
                //entrada = new DataInputStream(cliente.getInputStream());
                entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                while(!entrada.readLine().equals("SALIR")){
                    System.out.println(entrada.readLine());
                }
                salida = new DataOutputStream (cliente.getOutputStream());
                salida.writeUTF("Hola y Adios");
                cliente.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        System.out.println(args[0]+" "+args[1]);
        ServidorTelnet st = new ServidorTelnet(5500);
    }
}
