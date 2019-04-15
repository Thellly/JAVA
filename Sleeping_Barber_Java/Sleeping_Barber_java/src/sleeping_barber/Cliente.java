package sleeping_barber;

import static sleeping_barber.Barberia.*;
public class Cliente  extends Thread{
    int clienteID;
    boolean ClienteSinCortar = true;
    
    public Cliente(int id){
        clienteID = id;
    }
        
    @Override
    public void run()
    {  
        while (ClienteSinCortar)
        {
        //Mientras el cliente no se ha cortado el cabello
            try
            {
                s_asientos.acquire(); 
                if (asientos > 0)
                {
                    Barberia.barberia.addElement("Ha entrado el cliente "+this.clienteID+".");
                    asientos--;
                    
                    s_clientes.release();
                    s_asientos.release();
                                                           
                    try
                    {
                        s_barbero.acquire();                       
                        ClienteSinCortar = false;
                        //Cliente cortándose el cabello
                        this.CortandoCabello(); 
                    }
                    catch (InterruptedException ex) {}
                }  
                else
                {         
                    Barberia.barberia.addElement("Cliente " + this.clienteID + ": no hay asientos, regreso mas tarde.");
                    s_asientos.release();
                    ClienteSinCortar=false;
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void CortandoCabello()
    {
        Barberia.barberia.addElement("Cortando el pelo al cliente " + this.clienteID + "");
        try
        {
            sleep(5050);

        }catch(InterruptedException ex){}
    }
}
