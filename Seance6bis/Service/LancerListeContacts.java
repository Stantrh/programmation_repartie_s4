import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.StubNotFoundException;

public class LancerListeContacts{
    public static void main (String args[]) {

        try{

            ListeContacts listeContacts = new ListeContacts(); /* Creer une instance de ListeContacts */ 
            // On cree les contacts
            Contact c1 = new Contact("Talmo", "neuille@freure.com");
            Contact c2 = new Contact("Augerau", "stephi.lopipi@gmail.com");
            Contact c3 = new Contact("tonton_57", "gregoire.hirtz@fortnite.com");

            listeContacts.ajouterContact(c1);
            listeContacts.ajouterContact(c2);
            listeContacts.ajouterContact(c3);

            // Donc là je récupère l'annuaire de charlemagne
            Registry reg = LocateRegistry.getRegistry("charlemagne.iutnc.univ-lorraine.fr", 3333); 
            
            // Puis on recupere le service repondeur via l'annuaire de charlemagne
            ServiceRepondeur servRep = (ServiceRepondeur) reg.lookup("Repondeur");
            
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