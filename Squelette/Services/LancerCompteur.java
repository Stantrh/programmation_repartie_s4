import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer; /* permet d'obtenir des infos sur la machine cliente du service */
import java.rmi.registry.LocateRegistry;
import java.rmi.StubNotFoundException;

public class LancerCompteur{

    public static void main (String args[]) {

        try{

        Compteur compteur = new Compteur(); /* Créer une instance de Compteur */
        CompteurInterface rd = (CompteurInterface) UnicastRemoteObject.exportObject(compteur,0); 
        /* Un_port = un entier particulier ou 0 pour auto-assigné */
        Registry reg = LocateRegistry.getRegistry("localhost"); /* se connecter à l'annuaire de la machine */
        reg.rebind("CompteurDistant", rd); /* Enregistrement de la référence sous le nom "CompteurDistant" */

        }catch(StubNotFoundException e){
            System.err.println("Stub not of correct class");
        }catch(RemoteException e){
            e.printStackTrace();
        }

        
    }
}