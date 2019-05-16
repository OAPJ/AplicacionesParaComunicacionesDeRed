/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ovall
 */
public class ObjetoBanco extends UnicastRemoteObject implements Banco{
    private List<CuentaBancaria> cuentas;
    
    public ObjetoBanco() throws RemoteException {
        super();
        cuentas = new LinkedList<>();
    }
    
    @Override
    public CuentaBancaria crearCuenta(Titular t, double d) throws RemoteException {
        CuentaBancaria cu = new ObjetoCuentaBancaria(t, d);
        cuentas.add(cu);
        return cu;
    }

    @Override
    public CuentaBancaria crearCuenta(String n, int c, double d) throws RemoteException {
        CuentaBancaria cu = new ObjetoCuentaBancaria(n, c, d);
        cuentas.add(cu);
        return cu;
    }

    @Override
    public List<CuentaBancaria> obtenerCuentas() throws RemoteException {
        return cuentas;
    }
    
}
