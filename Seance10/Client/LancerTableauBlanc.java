import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.StubNotFoundException;

public class LancerTableauBlanc{
    public static void main (String args[]) {

        try{

           // on récupère d'abord le service, c'est à dire 
           Registry reg = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
           ServiceDistributeur tBS = (ServiceDistributeur) reg.lookup("distributeur");

           TableauBlanc tB = new TableauBlanc(tBS); // On crée notre tableau *

           ServiceTableauBlanc rdTB = (ServiceTableauBlanc) UnicastRemoteObject.exportObject(tB, 0);
            // j'exporte ma liste de contacts
            // et la passe en param de nouveauService
            tBS.enregistrerClient(rdTB);

            // puis je dois constater qu'un contact a été ajouté à ma liste

        }catch(NotBoundException e){
            e.printStackTrace();   
        }catch(StubNotFoundException e){
            System.err.println("Stub not of correct class");
        }catch(RemoteException e){
            e.printStackTrace();
        }

        
    }
}
