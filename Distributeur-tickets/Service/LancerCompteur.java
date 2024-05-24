import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class LancerCompteur{

    public static void main (String args[]) throws RemoteException {
        
        Distributeur compteur = new Distributeur(); /* Créer une instance de Compteur */
        ServiceDistributeur rd = (ServiceDistributeur) UnicastRemoteObject.exportObject(compteur,0); 
        /* Un_port = un entier particulier ou 0 pour auto-assigné */
        Registry reg = LocateRegistry.getRegistry("localhost"); /* se connecter à l'annuaire de la machine */
        reg.rebind("LeDistributeur", rd); /* Enregistrement de la référence sous le nom "CompteurDistant" */
        System.out.println("Service Compteur bien lancé !");
    }
}