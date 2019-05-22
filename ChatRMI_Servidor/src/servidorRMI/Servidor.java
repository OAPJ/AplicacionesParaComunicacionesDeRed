/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorRMI;

import interfaces.InterfazCliente;
import interfaces.InterfazServidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author ovall
 * Ojeto Remoto
 */
public class Servidor extends UnicastRemoteObject implements InterfazServidor{
    private Vector clientes;
    
    public Servidor() throws RemoteException{
        super();
        clientes = new Vector();
    }
    
    @Override
    public void registrar(InterfazCliente c) throws RemoteException {
        if(c != null) clientes.add(c);
    }

    @Override
    public void publicar(String msg) throws RemoteException {
        for(int i=0; i<clientes.size(); i++){
            InterfazCliente ic = (InterfazCliente) clientes.get(i);
            ic.enviarMsg(msg);
        }
    }

    @Override
    public Vector obtenerClientesActivos() throws RemoteException {
        return clientes;
    }
    
    public static void main(String[] args){
        try {
            Servidor s = new Servidor();
            Registry r = LocateRegistry.createRegistry(1099);
            r.rebind("servidor", s);
            System.out.println("Servidor activo...");
        } catch (Exception e) {
        }
    }

    @Override
    public void elimarCliente(InterfazCliente c) throws RemoteException {
        clientes.remove(c);
    }
}
