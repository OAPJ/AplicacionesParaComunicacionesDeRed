/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ovall
 */
public class Servidor {
    private int puerto;
    private int puerto2;
    private ServerSocket servidor;
    private Socket cliente;
    private Socket cliente2;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String msjCliente1 = "";
    private String msjCliente2 = "";
    
    public Servidor(int puerto, int port){
        this.puerto = puerto;
        this.puerto2 = port;
        try {
            servidor = new ServerSocket(puerto);
            cliente = servidor.accept();
            cliente2 = servidor.accept();
            while(true){
                recibirC1();
                enviarc2();
                recibirC2();
                enviarc1();
               if(msjCliente2.equals("Salir") || msjCliente1.equals("Salir"))
                    break;
            }
            cliente.close();
            cliente2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void recibirC1(){
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            msjCliente1 = entrada.readUTF();
            System.out.println("msj clinet1: "+msjCliente1);
        } catch (Exception e) {
        }
    }
    
    public void enviarc1(){
        try {
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(msjCliente2);
            System.out.println("msj cliente1 enviado");
        } catch (Exception e) {
        }
    }
    
    public void recibirC2(){
        try {
            entrada = new DataInputStream(cliente2.getInputStream());
            msjCliente2 = entrada.readUTF();
            System.out.println("msj cliente2: "+msjCliente2);
        } catch (Exception e) {
        }
    }
    
    public void enviarc2(){
        try {
            salida = new DataOutputStream(cliente2.getOutputStream());
            salida.writeUTF(msjCliente1);
            System.out.println("msj cliente2 enviado");
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        Servidor s = new Servidor(5000, 5001);
    }
}
