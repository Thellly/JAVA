
package sleeping_barber;

import static sleeping_barber.Formulario.*;

/**
 *
 * @author Grupo1
 */
public class Barbero extends Thread{
    
        public void run()
        {
            while(true)
            {
                try
                {
                    //Intenta adquirir un cliente, si no esta disponible duerme
                    clientes.acquire();                                       
                    //El barbero ha sido despertado
                    AccesoSillas.acquire();
                    //Una silla se libera
                    NumeroSillasLibres++;
                    //El barbero esta listo para cortar
                    barbero.release();
                    AccesoSillas.release();
                    this.CortarCabello(); 
                } catch (InterruptedException ex) {}
            }
        }
  
        public void CortarCabello()
        {
            //System.out.println("El barbero esta cortando el cabello del cliente ");
            Formulario.modelo.addElement("El barbero esta cortando el cabello del cliente.");
            try
            {
                sleep(5000);
            } catch (InterruptedException ex){ }
        }
}
