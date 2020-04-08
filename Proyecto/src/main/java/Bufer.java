
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kheysser
 */
public class Bufer extends DefaultListModel{
    
    
    private int limite;
    public static Semaphore mutex = new Semaphore(1, true); 
    
    public Bufer(int l){
        this.limite = l;
    }
    
    public boolean estaLleno(){
        if (this.getSize() >= limite){
            return true;
        }else{
            return false;
        }
    }
    
    public void Agregar(Object o){
        this.addElement(o);
    }
    
    public Object Eliminar(){
        return this.remove(0);
    }
    
    
    public void semaforo(){
        try {
            mutex.acquire(); // Entra a la región crítica
        } catch (InterruptedException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
    }
    
    
}
