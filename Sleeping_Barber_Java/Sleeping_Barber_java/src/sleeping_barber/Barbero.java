package sleeping_barber;

import java.util.Random;
import static sleeping_barber.Barberia.*;

public class Barbero extends Thread{
    int cliente;
    public Barbero(int id){
        cliente = id;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                //Intenta adquirir un cliente, si no esta disponible duerme
                s_clientes.acquire();
                //if(!s_clientes.tryAcquire())
                //   Barberia.barberia.addElement("Barbero: zZz...zZz...zZz...zZz...");
                
                //El s_barbero ha sido despertado
                s_asientos.acquire();
                
                //Una silla se libera
                asientos++;                
                
                //El s_barbero esta listo para cortar
                s_barbero.release();
                s_asientos.release();
                this.CortarCabello(); 
            } catch (InterruptedException ex) {}
        }
    }
  
    public void CortarCabello()
    {
        // Babero se demora de 3 a 6 segundos en cortar el cabello
        Random rnd = new Random();
        int tiempo = rnd.nextInt((6 - 3) + 1) + 3;
        tiempoCorte= tiempo*1000;
        //Formulario.barberia.addElement("Barbero: Termine mi trabajo");
        try
        {
            sleep(tiempo*1000);
        }catch (InterruptedException ex){ }
    }
}
