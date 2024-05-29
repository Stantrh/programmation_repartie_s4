import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.ArrayList;
public class ServiceDistributeurTableauBlanc implements ServiceDistributeur {
    private ArrayList<ServiceTableauBlanc> tableaux;
    private ArrayList<Dessin> dessins; // Pour stocker tous les dessins ayant été réalisés jusqu'à là, pour renvoyer aux nouveaux clients.

    public ServiceDistributeurTableauBlanc(){
        this.tableaux = new ArrayList<>();
        this.dessins = new ArrayList<>();
    }

    public void enregistrerClient(ServiceTableauBlanc c) throws RemoteException{
        this.tableaux.add(c);
        // Avec cette boucle, lorsqu'un nouveau client s'ajoute à la liste du serveur central, alors le service central lui retourne tous les points qu'il a déjà enregistrés au précédent.
        for(Dessin d : this.dessins){
            c.afficherMessage(d);
        }
    }

    public void distribuerMessage(Dessin d) throws RemoteException{
        // A chaque fois que le serveur reçoit un dessin, alors il en informe tous ses clients
        // Si le client ne répond pas, alors il le supprime de la liste
        
        this.dessins.add(d);
        
        for(ServiceTableauBlanc s : this.tableaux){
            try{
                s.afficherMessage(d);
            }catch(RemoteException e){ // si jamais une exception de type "ConnectException" est déclenchée lorsqu'un client est déconnecté mais toujours présent dans la liste
            // alors on l'enlève de la liste, comme ça, le serveur ne se bloque pas.
                this.tableaux.remove(s);
                e.printStackTrace();
            }
            
        }
    }
}
