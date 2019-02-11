/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author ovall
 */
public class Nio {
    public static void main(String[] args){
        try {
            RandomAccessFile aFile = new RandomAccessFile("Datos.txt", "rw");
            //Creamos un canal
            FileChannel inCahnnel = aFile.getChannel();
            //crear el buffer con el espacio de memoria
            ByteBuffer buf = ByteBuffer.allocate(48);
            //Leer los datos requeridos
            int bytesRead = inCahnnel.read(buf);
            while(bytesRead != -1){
                System.out.println("Read "+bytesRead);
                //Ponerlo en lectura o escritura
                buf.flip();
                while(buf.hasRemaining())
                    System.out.print((char) buf.get());
                buf.clear();
                bytesRead = inCahnnel.read(buf);
            }
            //Limpia el buffer
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
