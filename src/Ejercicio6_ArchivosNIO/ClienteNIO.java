/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio6_ArchivosNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Nombre: Juan Antonio Ovalle PAtiño
 * Nombre del programa: Practica 3: Envío de archivos con sockets de flujo en modo no bloqueante
 * Descripción: EL cliente manda un archivo al servidor con sokets no bloqueantes
 * Fecha: 20 / Febrero / 2019
 * @author ovall
 */

public class ClienteNIO {
    private SocketChannel sc;
    private FileChannel file;
    private ByteBuffer buffer;
    private Path ruta;
    private String host;
    private int puerto;
    
    public ClienteNIO(String host, int port){
        this.host = host;
        this.puerto = port;
        try {
            //Crear el socket
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress(host, puerto));
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void elegirArchivo(String ruta){
        this.ruta = Paths.get(ruta);//Obtener la ruta de un String o de un URI
        
    }
    
    public void enviarArchivo(){
        try {
            file = FileChannel.open(ruta);
            //Asignar un tamaño al buffer
            buffer = ByteBuffer.allocate(1024);
            //Leer del archivo y a la ves mandarlo
            while(file.read(buffer) >0 ){
                //Preparar el buffer para la escritura
                buffer.flip();
                //Mandar a traves del socket el texto al servidor
                sc.write(buffer);
                //Limpear el bufer
                buffer.clear();
            }
            System.out.println("Archivo enviado...");
            //cerrar el file y el socket
            file.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        ClienteNIO c = new ClienteNIO("127.0.0.1", 9000);
        c.elegirArchivo("Archivo.txt");
        c.enviarArchivo();
    }
    
}
