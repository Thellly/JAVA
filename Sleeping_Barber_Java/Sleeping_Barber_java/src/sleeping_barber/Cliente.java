package sleeping_barber;

import java.awt.Color;

public class Cliente  extends Thread{
    int clienteID;
    boolean ClientesEsperando = true;
    
    public Cliente(int id){
        clienteID = id;
    }
        
    @Override
    public void run()
    {  
        while (ClientesEsperando)
        {
            //Mientras el cliente no se ha cortado el cabello
            try
            {
                Barberia.s_mutex.acquire();
                Barberia.output.addElement("Barberia: Ha entrado el cliente " + this.clienteID + ".");
                
                if (Barberia.asientos > 0)
                {
                    Barberia.asientos--;                    
                    Barberia.s_mutex.release();
                    Barberia.s_barbero.acquire();
                    Barberia.s_clientes.release();
                    ClientesEsperando = false;
                    
                    this.CortandoCabello(); 
                }  
                else
                {         
                    Barberia.output.addElement("Cliente " + this.clienteID + ": No hay asientos, regreso más tarde.");
                    Barberia.s_mutex.release();
                    ClientesEsperando = false;
                }
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void CortandoCabello()
    {
        Barberia.output.addElement("Cortando el cabello al cliente " + this.clienteID + ".");
    }
}
