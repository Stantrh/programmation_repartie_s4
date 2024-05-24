import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.StubNotFoundException;

public class LancerListeContacts{
    public static void main (String args[]) {

        try{

            ListeContacts listeContacts = new ListeContacts(); /* Créer une instance de ListeContacts */ 
            // On crée les contacts
            Contact c1 = new Contact("Talmo", "neuille@freure.com");
            Contact c2 = new Contact("Augerau", "stephi.lopipi@gmail.com");
            Contact c3 = new Contact("tonton_57", "gregoire.hirtz@fortnite.com");

            listeContacts.ajouterContact(c1);
            listeContacts.ajouterContact(c2);
            listeContacts.ajouterContact(c3);

            Registry reg = LocateRegistry.getRegistry("localhost"); /* se connecter à l'annuaire de la machine */
            
            ServiceAgenda rdListeContacts = (ServiceAgenda) UnicastRemoteObject.exportObject(listeContacts,0); 
            /* Un_port = un entier particulier ou 0 pour auto-assigné */

            reg.rebind("contacts", rdListeContacts); /* Enregistrement de la référence  */

        }catch(StubNotFoundException e){
            System.err.println("Stub not of correct class");
        }catch(RemoteException e){
            e.printStackTrace();
        }

        
    }
}