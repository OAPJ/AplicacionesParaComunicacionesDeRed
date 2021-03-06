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
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * Nombre: Juan Antonio Ovalle PAtiño
 * Nombre del programa: Practica 3: Envío de archivos con sockets de flujo en modo no bloqueante
 * Descripción: EL cliente manda un archivo al servidor con sokets no bloqueantes
 * Fecha: 20 / Febrero / 2019
 * @author ovall
 */

public class ServidorNIO {
    private ServerSocketChannel ssc;
    private SocketChannel sc;
    private ByteBuffer buffer;
    private FileChannel file;
    private int puerto;
    private Path ruta;
    
    public ServidorNIO(int puerto){
        this.puerto = puerto;
        try {
            //Crear el ssc
            ssc = ServerSocketChannel.open();
            //Ligar al puerto
            ssc.socket().bind(new InetSocketAddress(puerto));
            //Congigurar el servidor en modo no bloqueante
            ssc.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recibirArchivo(){
        try {
            while(true){
                System.out.println("Esperando conexiones");
                //Establecer el socket del cliente
                sc = ssc.accept();
                if(sc != null){
                    System.out.println("Se conecto: "+sc.getRemoteAddress());
                    //Donde y como guardar el archivo
                    ruta = Paths.get("ArchivoNuevo.txt");
                    file = FileChannel.open(ruta, 
                            EnumSet.of(StandardOpenOption.CREATE,
                                    StandardOpenOption.TRUNCATE_EXISTING,
                                    StandardOpenOption.WRITE));
                    buffer = ByteBuffer.allocate(1024);
                    buffer.clear();
                    //leer del socket y del canal del archivo
                    while(sc.read(buffer) > 0){
                        buffer.flip();
                        file.write(buffer);
                        buffer.clear();
                    }
                    file.close();
                    System.out.println("Archivo recibido...");
                    sc.close();
                }
                else{
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        ServidorNIO s = new ServidorNIO(9000);
        s.recibirArchivo();
    }
}
