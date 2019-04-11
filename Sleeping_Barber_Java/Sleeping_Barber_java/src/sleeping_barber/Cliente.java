
package sleeping_barber;

import static sleeping_barber.Formulario.*;
/**
 *
 * @author Grupo1
 */
public class Cliente  extends Thread{
    int iD;
    boolean ClienteSinCortar = true;
    public Cliente(int i)
    {
        iD = i;
    }
    
    public void run()
    {  
        while (ClienteSinCortar)
        {
        //Mientras el cliente no se ha cortado el cabello
            try
            {
                AccesoSillas.acquire(); 
                if (NumeroSillasLibres > 0)
                {
                    Formulario.modelo.addElement("Ha entrado el cliente "+this.iD+".");
                    NumeroSillasLibres--;
                    
                    clientes.release();
                    AccesoSillas.release();
                                                           
                    try
                    {
                        barbero.acquire();                       
                        ClienteSinCortar = false;
                        //Cliente cortándose el cabello
                        this.CortandoCabello(); 
                    }
                    catch (InterruptedException ex) {}
                }  
                else
                {         
                    Formulario.modelo.addElement("Cliente " + this.iD + ": no hay asientos, regreso mas tarde.");
                    AccesoSillas.release();
                    ClienteSinCortar=false;
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void CortandoCabello()
    {
        Formulario.modelo.addElement("Cortando el pelo al cliente " + this.iD + "");
        try
        {
            sleep(5050);

        }catch(InterruptedException ex){}
    }
}
