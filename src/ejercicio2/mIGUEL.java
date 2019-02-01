/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class mIGUEL {
    private ServerSocket servidor;
    private Socket cliente;
    //private DataInputStream entrada;
    private BufferedReader entrada;
    private DataOutputStream salida;
    private int puerto;
    
   public mIGUEL(int puerto){
        try {
            this.puerto=puerto;
            servidor = new ServerSocket(this.puerto);
            while(true){
            cliente= servidor.accept();//Walk alone, empty pages burn forever mine walk alone
            //entrada= new DataInputStream(cliente.getInputStream());
            //System.out.println(entrada.readUTF());
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                do {                   
                    System.out.println(entrada.readLine());
                } while (!entrada.readLine().equals("SALIR"));
            salida= new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF("Bye bye");
            cliente.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   }
   public static void main(String Arcs[]){
       mIGUEL st = new mIGUEL(5500);
//       Para conectarse:
//       desde cmd:
//       telnet *IP* *puerto*
   }

}
