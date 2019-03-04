/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class Servidor {
    private static int puerto = 4000;
    private static String decisionCliente;
    private static String decision;
    private static String nombreArchivo;
    private static ServerSocket servidor;
    private static Socket cliente;
    private static DataInputStream entrada;
    private static DataOutput salida;
    private static InputStream llegada;
    private static FileOutputStream destino;
    
    public static void mandarDecision(String decision){
        System.out.println("Ennviando");
        try {
            cliente = servidor.accept();
            //Creamos el flujo de salida
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(decision);
        } catch (Exception e) {
        }
    }
    
    public static void recivirDecision(){
        try {
            // Creamos flujo de entrada para leer los datos que envia el cliente
            entrada = new DataInputStream(cliente.getInputStream());
            // Obtenemos La decisión del cliente
            decisionCliente = entrada.readUTF();
//            System.out.println(decisionCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void recivir(){
        System.out.println("Reciviendo...");
        try {
            nombreArchivo = entrada.readUTF();
            llegada = cliente.getInputStream();
            //Idicar donde se guarda el archivo
            destino = new FileOutputStream("Compartir/"+nombreArchivo);
            // Creamos el array de bytes para leer los datos del archivo
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = llegada.read(buffer)) > 0)
                destino.write(buffer);
            
//            destino.close();
//            llegada.close();
            System.out.println("Archivo recibido: "+nombreArchivo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String aegs[]){
        try {
            System.out.println("Esperando...");
            servidor = new ServerSocket(puerto);
            cliente = servidor.accept();
            recivirDecision();
            if(decisionCliente.equals("1")){
                recivir();
            }
            decision = "1";
            mandarDecision(decision);
            destino.close();
            llegada.close();
            cliente.close();
        } catch (Exception e) {
        }
    }
}
