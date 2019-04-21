package sleeping_barber;

import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    
                    //Notificamos al barbero que hay un cliente sentado
                    s_clientes.release();
                    s_asientos.release();
                                                           
                    try
                    {
                        s_barbero.acquire();                       
                        ClienteSinCortar = false;
                        //Cliente cortándose el cabello
                        this.CortandoCabello(); 
                        s_asientos.release(); 
                    }
                    catch (InterruptedException ex) {}
                }  
                else
                {         
                    Barberia.barberia.addElement("Cliente " + this.clienteID + ": No hay asientos, regreso más tarde.");
                    
                    s_asientos.release();
                    ClienteSinCortar=false;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void CortandoCabello()
    {
        Barberia.barberia.addElement("Cortando el cabello al cliente " + this.clienteID + ".");
        try
        {
            sleep(tiempoCorte+ 100);
//            if(totalAsientos==asientos && this.clienteID!=1 && clientesIn==(totalClientes-clientesOut) && !s_asientos.tryAcquire())
            if(totalAsientos==asientos )
               Barberia.barberia.addElement("Barbero: zZz...zZz...zZz...zZz...");
           
        }catch(Exception e) {}
    }
}
