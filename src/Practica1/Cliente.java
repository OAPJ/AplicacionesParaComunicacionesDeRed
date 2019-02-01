/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * Nombre: Juan Antonio Ovalle PAtiño
 * Nombre del programa: Practica 1: Construcción de un servicio que simule al comando
 *                                  ‘echo’ utilizando sockets orientados a conexión
 * Descripción: El cliente tiene que ingresar un ip, un mensjae y si quiere el puerto y se verifica que sean validos el ip y el puerto
 *              Se manda el mensaje al cliente y el servidor devuelve el mismo mensaje.
 * Fecha: 1 / Febrero / 2019
 * @author ovall
 */

public class Cliente {
    private static String mensajeCorrecto = "Datos no validos, el formato es: <servidor> <mensaje> <puerto>* opcional";
    private static String mensajeCliente = "";
    private static int puerto = 5500;
    private static String ip;
    private static DataInputStream entrada;
    private static DataOutputStream salida;
    private static Socket cliente;
    
    public static void validar(String[] cadena){
        int limiteCadena = cadena.length-1;
        if((cadena.length > 1) && (validarIp(cadena[0]))){
            if(validarPuerto(cadena[cadena.length-1]) == 3)
               limiteCadena++;
            
            if(puerto != 5500)
                puerto = 5500;
            
            for(int i=1; i<limiteCadena; i++)
                if(i != (limiteCadena-1))
                    mensajeCliente+= cadena[i]+" ";
                else
                    mensajeCliente+= cadena[i];
            
            try{
                cliente = new Socket(ip, puerto);
                salida = new DataOutputStream(cliente.getOutputStream());
                salida.writeUTF(mensajeCliente);
                entrada = new DataInputStream(cliente.getInputStream());
                System.out.println("Servidor dice>> "+entrada.readUTF());
            } catch(UnknownHostException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else
            System.out.println(mensajeCorrecto);
    }
    
    public static boolean validarIp(String cadena){
        //Separa los numeros cuando encuentra un '.'
        String[] numeros = cadena.split("\\.");
        if(numeros.length>4 || numeros.length<4)
            return false;
        else
            for(int i=0; i<numeros.length; i++)
                if(Integer.parseInt(numeros[i])>255 || Integer.parseInt(numeros[i])<0)
                    return false;
        return true;
    }
    
    public static int validarPuerto(String pue){
        try{
            if(Integer.parseInt(pue)>1025 && Integer.parseInt(pue)<65336){
                puerto = Integer.parseInt(pue);
                return 1;
            }
            return 2;
        }catch(Exception e){
            return 3;
        }
    }
    
    public static void main(String[] args){
        validar(args);
    }
}
