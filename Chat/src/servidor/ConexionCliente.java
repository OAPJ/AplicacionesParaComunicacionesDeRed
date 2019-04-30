/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ovall
 */
public class ConexionCliente extends Thread implements Observer{
    private Socket sock;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Mensaje mensaje;
    
    public ConexionCliente(Socket s, Mensaje m){
        sock = s;
        mensaje = m;
        try {
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        String msgNuevo;
        boolean conectado = true;
        mensaje.addObserver(this);
        while(conectado){
            try {
                msgNuevo = entrada.readUTF();
                mensaje.setMsg(msgNuevo);
                
            } catch (IOException e) {
                e.printStackTrace();
                conectado = false;
                try {
                    entrada.close();
                    salida.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg){
        try {
            salida.writeUTF(arg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
