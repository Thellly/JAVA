package sleeping_barber;

import java.util.Random;


public class Barbero extends Thread{
    int cliente;
    public Barbero(int id){
        cliente = id;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                if(!Barberia.s_clientes.tryAcquire()){
                    Barberia.output.addElement("Barbero: zZzZzz...zZzZzz...zZzZzz...");
                }
                Barberia.s_mutex.acquire();
                Barberia.asientos++;                

                Barberia.s_barbero.release();
                Barberia.s_mutex.release();
                this.CortarCabello(); 
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
  
    public void CortarCabello()
    {
        // Babero se demora de 4 a 6 segundos en cortar el cabello
        Random rnd = new Random();
        int tiempo = rnd.nextInt(4) + 3;
        try{
            sleep(tiempo * 1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
