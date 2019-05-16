/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ovall
 */
public class Cliente {
    public static void main(String[] args) {
        try{
            Banco b = (Banco) LocateRegistry.getRegistry("127.0.0.1",1234).lookup("RMI");
            CuentaBancaria cu = b.crearCuenta(new Titular("Juan", 1334), 5000);
            cu.depositar(500);
            System.out.println(cu.optenerTitular().toString()+" Sadldo = "+cu.consultarSaldo());
            CuentaBancaria cu2 = b.crearCuenta("Miguel", 1456, 5000);
            cu2.retirar(450);
            System.out.println(cu2.optenerTitular().toString()+" Sadldo = "+cu2.consultarSaldo());
            CuentaBancaria cu3 = b.crearCuenta("Luis", 7849, 850);
            cu3.depositar(0);
            cu3.retirar(100000000);
            System.out.println(cu3.optenerTitular().toString()+" Sadldo = "+cu3.consultarSaldo());
            for(CuentaBancaria c : b.obtenerCuentas())
                System.out.println(c.optenerTitular().toString());
        }catch(RemoteException f){
            
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
