package sleeping_barber;

import java.util.Random;
import static sleeping_barber.Formulario.*;

public class Barbero extends Thread{
    @Override
    public void run(){
        while(true){
            try{
                //Intenta adquirir un cliente, si no esta disponible duerme
                clientes.acquire();
                //El barbero ha sido despertado
                asientosLibres.acquire();
                //Una silla se libera
                nroAsientos++;
                //El barbero esta listo para cortar
                barbero.release();
                asientosLibres.release();
                this.CortarCabello(); 
            } catch (InterruptedException ex) {}
        }
    }
  
    public void CortarCabello()
    {
        // Babero se demora de 3 a 6 segundos en cortar el cabello
        Random rnd = new Random();
        int tiempo = rnd.nextInt((6 - 3) + 1) + 3;
                
        //Formulario.modelo.addElement("Barbero: Termine mi trabajo");
        try
        {
            sleep(tiempo*1000);
        }catch (InterruptedException ex){ }
    }
}
