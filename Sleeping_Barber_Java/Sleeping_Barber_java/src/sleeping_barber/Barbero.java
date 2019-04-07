
package sleeping_barber;

/**
 *
 * @author Grupo1
 */
public class Barbero extends Thread{
    public Barbero() {}
        public void run()
        {
            while(true)
            {
                try
                {
                    //Intenta adquirir un cliente, si no esta disponible duerme
                    Barberia.clientes.acquire();                                       
                    //El barbero ha sido despertado
                    Barberia.AccesoSillas.acquire();
                    //Una silla se libera
                    Barberia.NumeroSillasLibres++;
                    //El barbero esta listo para cortar
                    Barberia.barbero.release();
                    Barberia.AccesoSillas.release();
                    this.CortarCabello(); 
                } catch (InterruptedException ex) {}
            }
        }
  
        public void CortarCabello()
        {
            System.out.println("El barbero esta cortando el cabello del cliente ");
            try
            {
                sleep(5000);
            } catch (InterruptedException ex){ }
        }
}
