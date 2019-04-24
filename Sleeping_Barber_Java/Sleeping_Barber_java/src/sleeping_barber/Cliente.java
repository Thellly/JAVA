package sleeping_barber;

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
                Barberia.s_asientos.acquire(); 
                if (Barberia.asientos > 0)
                {
                    Barberia.output.addElement("Ha entrado el cliente " + this.clienteID + ".");
                    Barberia.asientos--;
                    //Notificamos al barbero que hay un cliente sentado
                    Barberia.s_clientes.release();
                    Barberia.s_asientos.release();
                    try
                    {
                        Barberia.s_barbero.acquire();                       
                        ClienteSinCortar = false;
                        //Cliente cortándose el cabello
                        this.CortandoCabello(); 
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }  
                else
                {         
                    Barberia.output.addElement("Cliente " + this.clienteID + ": No hay asientos, regreso más tarde.");
                    Barberia.s_asientos.release();
                    ClienteSinCortar = false;
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void CortandoCabello()
    {
        Barberia.output.addElement("Cortando el cabello al cliente " + this.clienteID + ".");
    }
}
