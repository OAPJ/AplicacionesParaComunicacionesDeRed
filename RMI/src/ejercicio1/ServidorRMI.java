/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 *
 * @author Alumno
 */
public class ServidorRMI {
    
    public static void main(String[] args) {
        try{
            ObjetoRemoto objetoRemoto= new ObjetoRemoto();
            Registry reg= LocateRegistry.createRegistry(1234);
            reg.bind("Tacoos", objetoRemoto);
            System.out.println("Se puso en marcha el objetito ");
        }catch(RemoteException e){
            
        } catch (AlreadyBoundException ex) {
            
        }
    }
    
}
