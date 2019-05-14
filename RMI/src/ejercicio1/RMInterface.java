/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Alumno
 */
public interface RMInterface extends Remote {
    public double suma(double a, double b) throws RemoteException;
    public double resta(double a, double b) throws RemoteException;
    public double multiplicacion(double a, double b) throws RemoteException;
    public double division(double a, double b) throws RemoteException;
    public double cuadrado(double a) throws RemoteException;
    public double raiz(double a) throws RemoteException;
    public double porcentaje(double a, double b) throws RemoteException;
    public double reciproco(float a) throws RemoteException;
    
}
