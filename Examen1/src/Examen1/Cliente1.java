/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ovall
 */
public class Cliente1 {
    private String ip = "127.0.0.1";
    private int puerto = 5000;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Socket cliente;
    private String msj;
    
    public Cliente1(){
        try{
            cliente = new Socket(ip, puerto);
        } catch(UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void enviar(){
        try {
            salida = new DataOutputStream(cliente.getOutputStream());
            System.out.println("Msj: ");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader (isr);
            msj = br.readLine();
            salida.writeUTF(msj);
        } catch (Exception e) {
        }
    }
    
    public void resibir(){
        try {
            entrada = new DataInputStream(cliente.getInputStream());
            msj = entrada.readUTF();
            System.out.println("Cliente dice>>" +msj);
        } catch (Exception e) {
        }
    }
    
    public void cliente1(){
        System.out.println("Cliente 1");
        try {
            while(true){
                enviar();
                if(msj.equals("Salir"))
                    break;
                resibir();
                if(msj.equals("Salir"))
                    break;
            }
        } catch (Exception e) {
        }
        System.out.println("Termino");
    }
    
    public void cliente2(){
        System.out.println("Cliente 2");
        try {
            while(true){
                resibir();
                if(msj.equals("Salir"))
                    break;
                enviar();
                if(msj.equals("Salir"))
                    break;
            }
        } catch (Exception e) {
        }
        System.out.println("Termino");
    }
    
    public static void main(String[] args){
        Cliente1 cliente = new Cliente1();
        //Comentar el cliente dos y correr
        //Despues comentar el cliente1 y correr
        
//        cliente.cliente1(); 
        cliente.cliente2();
        try {
            cliente.cliente.close();
        } catch (Exception e) {
        }
    }
}
