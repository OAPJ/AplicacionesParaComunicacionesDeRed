/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ovall
 */
public interface CuentaBancaria extends Remote{
    public double consultarSaldo() throws RemoteException;
    public void depositar(double dinero) throws RemoteException;
    public void retirar(double dinero) throws RemoteException;
    public Titular optenerTitular()throws RemoteException;
}
