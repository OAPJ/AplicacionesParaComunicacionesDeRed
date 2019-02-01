/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ovall
 */
public class Cliente {
    private String ip = "127.0.0.1";
    private int puerto = 5000;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Socket cliente;
    private ArrayList<String> mensaje;
    private Random numero;
    
    public Cliente(){
        mensaje = new ArrayList<>();
        mensaje.add("COnectado en el puerto ");
        mensaje.add("La direccion ip es "+ip);
        mensaje.add("Que tal tu dia");
        mensaje.add("Excelente dia");
        mensaje.add("Ya estoy conectado");
        numero = new Random();
        try{
            
            cliente = new Socket(ip, puerto);
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(mensaje.get(numero.nextInt(mensaje.size()-1)));
            //recibir el mesnaje del servidor
            entrada = new DataInputStream(cliente.getInputStream());
            System.out.println("Servidor dice>>"+entrada.readUTF());
            cliente.close();
        } catch(UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Cliente cliente = new Cliente();
    }
}
