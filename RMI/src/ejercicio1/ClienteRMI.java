/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class ClienteRMI {
    
    public static void main(String[] args) {
        try{
            RMInterface rMInterface;
            rMInterface= (RMInterface) LocateRegistry.getRegistry("127.0.0.1",1234).lookup("Tacoos");
            System.out.println("Suma "+rMInterface.suma(16, 10));
            System.out.println("Resta "+rMInterface.resta(165, 86));
            System.out.println("Multiplicacion "+rMInterface.multiplicacion(51, 13));
            System.out.println("Divicion "+rMInterface.division(154, 8));
            System.out.println("Elvar al cuadrado "+rMInterface.cuadrado(43));
            System.out.println("Raiz Cuadrada "+rMInterface.raiz(752));
            System.out.println("Porcentaje "+rMInterface.porcentaje(450, 8));
            System.out.println("Reciproco "+rMInterface.reciproco(12));
        }catch(RemoteException f){
            
        } catch (NotBoundException ex) {
            Logger.getLogger(ClienteRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
