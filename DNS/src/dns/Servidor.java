/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dns;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ovall
 */

public class Servidor {
    private DatagramSocket server;
    private DatagramPacket enviar;
    private ByteArrayOutputStream array;
    private ObjectOutputStream ops;
    private DatagramPacket recibir;
    ArrayList<URL[]> ips;
    ArrayList<URL> dominios;

    public Servidor(int port) {
        try{
            this.server= new DatagramSocket(port);
            ips= new ArrayList<>();
            this.dominios= new ArrayList<>();
        }catch(IOException s){
            System.out.println(s);
        }
    }
    
    public void recibir(){
        
        try {
            byte[] buffer = new byte[1024];
            recibir = new DatagramPacket(buffer, buffer.length);
            server.receive(recibir);
            String res = new String(recibir.getData());
            System.out.println(res);
            Thread.sleep(5000);
            enviar(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void enviar(String direccion){
        int aux=0;
        ArrayList<String> resultado;
        resultado= new ArrayList<>();
        if(validarIP(direccion)){
            for(int i=0;i<this.dominios.size();i++){
            if(this.dominios.get(i).getPath().equals("/"+direccion)){
                aux=i;
                resultado.add(dominios.get(i).getAuthority());
                break;
            }
        }
        }else{
            for(int i=0;i<this.dominios.size();i++){
            if(this.dominios.get(i).getAuthority().equals(direccion)){
                aux=i;
                String a = dominios.get(i).getPath().replace("/", "");
                System.out.println(a);
                resultado.add(a);
                break;
            }
        }
        }
        //----------
        try {
            array = new ByteArrayOutputStream();
            ops = new ObjectOutputStream(array);
            ops.writeObject(resultado);
            ops.flush();
            byte[] buffer = array.toByteArray();
            int n = buffer.length;
            byte[] data = new byte[4];
            for(int i=0; i<4; i++){
                int shift = i << 3;
                data[3-i] = (byte)((n & (0xff << shift)) >>> shift);
            }
            //InetAddress c = InetAddress.getByName("localhot");
            enviar = new DatagramPacket(data, 4, recibir.getAddress(), recibir.getPort());
            server.send(enviar);
            System.out.println("Enviando");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void agregarDominio(String dominio, String ip){
        try {
            URL url = new URL(dominio);
            URL urlIP = new URL(url, ip);
            this.dominios.add(urlIP);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean validarIP(String ip){
        String[] aux = ip.split("\\.");
        if(aux.length!=4) return false;
        try{
            int oct1= Integer.parseInt(aux[0]);
            int oct2= Integer.parseInt(aux[1]);
            int oct3= Integer.parseInt(aux[2]);
            int oct4= Integer.parseInt(aux[3]);
             return (oct1<256 && oct1>-1 && oct2<256 && oct2>-1 && oct3<256 && oct3>-1 && oct4<256 && oct4>-1);
        }catch(Exception w){
            return false;
        }
    }
    public static void main(String[] args) {
        Servidor s = new Servidor(5000);
        s.agregarDominio("https://www.google.com/", "216.58.194.131");
        s.recibir();
    }
}