/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercico4_Objetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class ServidorObjeto {
    public static void main(String[] args){
        try {
            ServerSocket servidor = new ServerSocket(4000);
            Socket c = servidor.accept();
            ObjectOutputStream envio = new ObjectOutputStream(c.getOutputStream());
            Empleado e = new Empleado("Juan Antonio", "Mesa prieta 124");
            //enviar el ojeto
            envio.writeObject(e);
            System.out.println("Se envió el empleado");
            envio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
