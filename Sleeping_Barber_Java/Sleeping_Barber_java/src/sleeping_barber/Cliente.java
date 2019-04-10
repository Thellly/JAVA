
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
                    //System.out.println("Cliente "+this.iD+" acaba de sentarse.");
                    Formulario.modelo.addElement("Cliente "+this.iD+" acaba de sentarse.");
                    //Cliente se sento en una silla, decrementamos número de sillas
                    NumeroSillasLibres--;
                    //Notificamos al barbero que hay un cliente sentado
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
                    //No hay asientos libres
                    //System.out.println("No hay asientos libres. Cliente " + this.iD + " ha dejado la barbería.");
                    Formulario.modelo.addElement("No hay asientos libres. Cliente " + this.iD + " ha dejado la barbería.");
                    AccesoSillas.release();
                    ClienteSinCortar=false;
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void CortandoCabello()
    {
        //System.out.println("Cliente " + this.iD + " se está cortando el cabello");
        Formulario.modelo.addElement("Cliente " + this.iD + " se está cortando el cabello.");
        try
        {
            sleep(5050);

        }catch(InterruptedException ex){}
    }
}
