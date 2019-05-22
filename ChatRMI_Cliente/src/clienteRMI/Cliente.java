/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteRMI;

import interfaces.InterfazCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ovall
 */
public class Cliente extends UnicastRemoteObject implements InterfazCliente{
    private String n;
    private ClienteGUI c;
    public Cliente(String n) throws RemoteException{
        super();
        this.n = n;
    }

    @Override
    public void enviarMsg(String msg) throws RemoteException {
        c.actualizarArea(msg);
    }

    @Override
    public String getNombre() throws RemoteException {
        return this.n;
    }
    
    public void setC(ClienteGUI c){
        this.c = c;
    }
}
