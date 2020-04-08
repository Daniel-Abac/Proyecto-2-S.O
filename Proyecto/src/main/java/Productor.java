
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kheysser
 */
public class Productor implements Runnable{
 
    private Formulario formulario;
    private Bufer bufer;
    private long tiempoADormir;
    
    public Productor(Formulario formulario, Bufer bufer){
        this.bufer = bufer;
        this.formulario = formulario;
    }
    
    public Productor(Formulario formulario, Bufer bufer, long tiempo){
        this(formulario, bufer);
        this.tiempoADormir = tiempo;
    }
    
    
    @Override
    public void run() {
        
        // mientras haya un elemento en la lista productor
        while(formulario.modeloproductor.getSize() > 0){
            
            synchronized(bufer){
                //pasarlo a el bufer
                if(!bufer.estaLleno()){
                        
                        bufer.Agregar(formulario.modeloproductor.remove(formulario.modeloproductor.getSize() - 1));            
                        bufer.notify();
                        
                        
                }
            }
            try {
                Thread.sleep(this.tiempoADormir);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
