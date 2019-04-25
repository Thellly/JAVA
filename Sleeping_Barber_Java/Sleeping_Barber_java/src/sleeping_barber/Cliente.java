package sleeping_barber;

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
                
                if (Barberia.asientos > 0)
                {
                    Barberia.output.addElement("Ha entrado el cliente " + this.clienteID + ".");
                    
                    Barberia.asientos--;                    
                    Barberia.s_mutex.release();
                    //if(Barberia.s_barbero.tryAcquire())
                    {
                        Barberia.s_barbero.acquire();
                        Barberia.s_clientes.release();
                        ClientesEsperando = false;
                        //Cliente cortándose el cabello
                        this.CortandoCabello(); 
                    }
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
