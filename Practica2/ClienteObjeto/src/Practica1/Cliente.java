/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JFileChooser;

/**
 *
 * @author ovall
 * 
 *  1 Para enviar
 *  2 Para Recibir
 *  3 Para Mostrar
 */
public class Cliente {
    private static String ip = "127.0.0.1"; //"192.168.0.18";
    private static String decisionServidor;
    private static String nombreArchivo;
    private static int puerto = 4000;
    private static Socket cliente;
    private static DataOutputStream salida;
    private static DataInputStream entrada;
    private static PrintStream envio;
    private static InputStream llegada;
    private static FileInputStream origen;
    private static FileOutputStream destino;
    
    
    
    public static File openFile(){
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = selector.showOpenDialog(null);
        if (res == 1 )
            return null;
        File archivo = selector.getSelectedFile();
        return selector.getSelectedFile();
    }
    
    public static void mandarDecision(String decision){
        try {
            // Creamos el flujo de salida
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(decision);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void recivirDecision(){
        try {
            System.out.println("Esperando...");
            cliente = new Socket(ip, puerto);
            entrada = new DataInputStream(cliente.getInputStream());
            decisionServidor = entrada.readUTF();
            System.out.println(decisionServidor);
        } catch (Exception e) {
        }
    }
    
    public static void enviarA(){
        try {
            OutputStream os =  cliente.getOutputStream();
                envio = new PrintStream(os);
                //Seleccionamos el archivo
                File archivo = openFile();
                // Enviamos el nombre del archivo 
                salida.writeUTF(archivo.getName());
                // Creamos flujo de entrada para realizar la lectura del archivo en bytes
                origen = new FileInputStream(archivo.getAbsoluteFile());
                // Creamos un array de tipo byte 
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = origen.read(buffer)) > 0)
                    envio.write(buffer, 0, len);
                System.out.println("Archivo Enviado: "+archivo.getName());
        } catch (Exception e) {
        }
    }
    
    public static void recivir(){
        try {
            nombreArchivo = entrada.readUTF();
            llegada = cliente.getInputStream();
            //Idicar donde se guarda el archivo
            destino = new FileOutputStream("Compartir/"+nombreArchivo);
            //Creamos el array de bytes para leer los datos del archivo
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = llegada.read(buffer)) >0)
                destino.write(buffer);
            destino.close();
            llegada.close();
            System.out.println("Archivo recibido: "+nombreArchivo);
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args){
        try {
            String decision = "1";
            // Creamos el Socket con la direccion y elpuerto de comunicacion
            cliente = new Socket(ip, puerto);
            //Mandos al servidor la opcion que desa el cliente
            mandarDecision(decision);
            if(decision.equals("1")){
                enviarA();
            }
//            recivirDecision();
            //if(decisionServidor.equals("1"))
////                recivir();
            cliente.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
