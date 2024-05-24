import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class LancerCompteur{

    public static void main (String args[]) throws RemoteException {
        Compteur compteur = new Compteur(); /* Créer une instance de Compteur */
        CompteurInterface rd = (CompteurInterface) UnicastRemoteObject.exportObject(compteur,4667); 
        /* Un_port = un entier particulier ou 0 pour auto-assigné */
        Registry reg = LocateRegistry.createRegistry(1099); /* Création de l'annuaire */
        reg.rebind("CompteurDistant", rd); /* Enregistrement de la référence sous le nom "CompteurDistant" */
    }
}