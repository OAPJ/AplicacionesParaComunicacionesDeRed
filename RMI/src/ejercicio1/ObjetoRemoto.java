/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Alumno
 */
public class ObjetoRemoto extends UnicastRemoteObject implements RMInterface {
    
    public ObjetoRemoto()throws RemoteException {
        super();
    }
    
    @Override
    public double suma(double a, double b) throws RemoteException {
        return a+b;
    }

    @Override
    public double resta(double a, double b) throws RemoteException {
        return a-b;
    }

    @Override
    public double multiplicacion(double a, double b) throws RemoteException {
        return a*b;
    }

    @Override
    public double division(double a, double b) throws RemoteException {
        return a/b;
    }

    @Override
    public double cuadrado(double a) throws RemoteException {
        return a*a;
    }

    @Override
    public double raiz(double a) throws RemoteException {
        return Math.sqrt(a);
    }

    @Override
    public double porcentaje(double a, double b) throws RemoteException {
        return (b*100)/a;
    }

    @Override
    public double reciproco(float a) throws RemoteException {
        return a!=0 ? 1.0/a : 0;
    }
}
