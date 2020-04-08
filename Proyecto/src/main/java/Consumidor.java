
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
public class Consumidor implements Runnable{

    Formulario formulario;
    Bufer bufer;
    private long tiempoADormir;
    
    public Consumidor(Formulario formulario, Bufer bufer){
        this.bufer = bufer;
        this.formulario = formulario;
    }
    
    public Consumidor(Formulario formulario, Bufer bufer, long t){
        this(formulario, bufer);
        this.tiempoADormir = t;
    }
    
    
    
    @Override
    public void run() {
            
        //Mientras haya algun elemento en el bufer
        while(bufer.getSize() > 0){
            
            // pasarlo a la lista del consumidor
            synchronized(bufer){
                    
                    if(bufer.isEmpty()){
                        try {
                            bufer.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    formulario.modeloconsumidor.addElement(bufer.Eliminar());
                    
            }
            try {
               Thread.sleep(this.tiempoADormir);
            } catch (InterruptedException ex) {
               Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        
         
    }
    
}
