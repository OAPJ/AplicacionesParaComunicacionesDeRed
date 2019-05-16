/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ovall
 */
public class Servidor {
    public static void main(String[] args) {
        try{
            ObjetoBanco ob= new ObjetoBanco();
            Registry reg= LocateRegistry.createRegistry(1234);
            reg.bind("RMI", ob);
            System.out.println("Servidor activo...");
        }catch(RemoteException e){
            
        } catch (AlreadyBoundException ex) {
            
        }
    }
}
