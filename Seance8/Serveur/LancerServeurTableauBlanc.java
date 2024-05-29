import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.StubNotFoundException;

public class LancerServeurTableauBlanc{
    public static void main (String args[]) {

        try{

            // on récupère d'abord le service, c'est à dire 
            Registry reg = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
            TableauBlancServeur tBS = (TableauBlancServeur) reg.lookup("distributeur");

            TableauBlanc tB = new TableauBlanc(tBS); // On crée notre tableau 
            // On cree les contacts

            // Donc là je récupère l'annuaire de charlemagne
            Registry reg = LocateRegistry.getRegistry("charlemagne.iutnc.univ-lorraine.fr", 3333); 
            
            // Puis on recupere le service repondeur via l'annuaire de charlemagne
            ServiceRepondeur servRep = (ServiceRepondeur) reg.lookup("tableauBlanc");
            
            // j'exporte ma liste de contacts
            ServiceAgenda rdListeContacts = (ServiceAgenda) UnicastRemoteObject.exportObject(listeContacts,0); 
            // et la passe en param de nouveauService
            servRep.nouveauService(rdListeContacts);

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