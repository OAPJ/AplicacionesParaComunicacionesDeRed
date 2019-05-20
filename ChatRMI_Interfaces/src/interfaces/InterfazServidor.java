/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author ovall
 */
public interface InterfazServidor extends Remote{
    public void registrar(InterfazCliente c) throws RemoteException;
    public void publicar(String msg) throws RemoteException;
    public Vector obtenerClientesActivos() throws RemoteException;
}
