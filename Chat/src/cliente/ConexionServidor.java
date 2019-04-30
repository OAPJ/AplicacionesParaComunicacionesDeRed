/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;

/**
 *
 * @author ovall
 */
public class ConexionServidor implements ActionListener{
    private DataOutputStream salida;
    private Socket sock;
    private JTextField msg;
    private String usr;
    
    public ConexionServidor(Socket s, JTextField m, String usr){
        sock = s;
        msg = m;
        this.usr = usr;
        try {
            salida = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            salida.writeUTF(usr+": "+msg.getText());
            msg.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
