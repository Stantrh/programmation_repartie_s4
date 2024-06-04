import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.StubNotFoundException;

public class LancerServeurTableauBlanc{
    public static void main (String args[]) {

        try{

            // Je récupère mon annuaire, généralement localhost 1099
            Registry reg = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));

            ServiceDistributeurTableauBlanc sDTB = new ServiceDistributeurTableauBlanc();



            // j'exporte mon tableau central
            ServiceDistributeur rdServeurCentralTB = (ServiceDistributeur) UnicastRemoteObject.exportObject(sDTB,0); 

            // Et l'ajoute à l'annuaire
            reg.rebind("distributeur", rdServeurCentralTB); 

            // puis je dois constater qu'un contact a été ajouté à ma liste

        }catch(StubNotFoundException e){
            System.err.println("Stub not of correct class");
        }catch(RemoteException e){
            e.printStackTrace();
        }

        
    }
}