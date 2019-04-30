/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.Observable;

/**
 *
 * @author ovall
 */
public class Mensaje extends Observable{
    private String msg;
    
    public Mensaje(){
        
    }
    
    public String getMsg(){
        return msg;
    }
    public void setMsg(String m){
        msg = m;
        this.setChanged();//Idica si hubo un cambio
        //Notiifcar a todos los observadores que hubo un cambio
        this.notifyObservers(msg);
    }
}
