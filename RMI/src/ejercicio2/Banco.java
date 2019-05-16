/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ovall
 */
public interface Banco extends Remote{
    public CuentaBancaria crearCuenta(Titular t, double d) throws RemoteException;
    public CuentaBancaria crearCuenta(String n, int c, double d) throws RemoteException;
    public List<CuentaBancaria> obtenerCuentas() throws RemoteException;
}
