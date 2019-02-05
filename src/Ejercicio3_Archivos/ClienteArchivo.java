/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3_Archivos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class ClienteArchivo {
    
    public static void main(String[] args){
        try {
            Socket s = new Socket("127.0.0.1", 4000);
            OutputStream os = s.getOutputStream();
            PrintStream envio = new PrintStream(os);
            //leer el archivo
            FileInputStream origen = new FileInputStream("Archivo.txt");
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = origen.read(buffer)) > 0)
                envio.write(buffer, 0, len);
            origen.close();
            envio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
