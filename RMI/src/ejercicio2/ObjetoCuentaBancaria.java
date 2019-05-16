/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ovall
 */
public class ObjetoCuentaBancaria extends UnicastRemoteObject implements CuentaBancaria{
    private Titular titular;
    private double saldo=0;
    
    public ObjetoCuentaBancaria(Titular t, double d)throws RemoteException{
        super();
        this.titular = t;
        this.saldo = d;
    }
    
    public ObjetoCuentaBancaria(String n,int c, double d)throws RemoteException{
        super();
        this.titular = new Titular(n, c);
        this.saldo = d;
    }
    
    @Override
    public double consultarSaldo() throws RemoteException {
        return saldo;
    }

    @Override
    public void depositar(double dinero) throws RemoteException {
        saldo = (dinero >0) ? saldo+dinero : saldo;
    }

    @Override
    public void retirar(double dinero) throws RemoteException {
        saldo = (dinero > saldo) ? saldo : saldo-dinero;
    }

    @Override
    public Titular optenerTitular() throws RemoteException {
        return titular;
    }
    
}
