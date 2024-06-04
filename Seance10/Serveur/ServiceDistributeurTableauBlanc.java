import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceDistributeurTableauBlanc implements ServiceDistributeur {
    
    private ArrayList<ServiceTableauBlanc> tableaux;
    private ArrayList<Dessin> dessins; // Pour stocker tous les dessins ayant été réalisés jusqu'à là, pour renvoyer aux nouveaux clients.
    private final ExecutorService executorService;

    public ServiceDistributeurTableauBlanc(){
        this.tableaux = new ArrayList<>();
        this.dessins = new ArrayList<>();
        this.executorService = Executors.newCachedThreadPool();
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

            synchronized (this) {
                this.dessins.add(d);
            }

// Classe un point un service tableau, 
// le run tu appelles distribuer message du service tableau

            // Utiliser des threads pour exécuter les appels aux clients de manière asynchrone
            for (ServiceTableauBlanc s : this.tableaux) {
                    DessinThread process = new DessinThread(d, s, this.tableaux);

                    process.start();

                    // this.tableaux.remove(s);
                    
                    
                
                    // Si jamais une exception de type "ConnectException" est déclenchée lorsqu'un client est déconnecté mais toujours présent dans la liste
                    // alors on l'enlève de la liste, comme ça, le serveur ne se bloque pas.
                    
            

            // for (ServiceTableauBlanc s : new ArrayList<>(this.tableaux)) { // Créer une copie pour éviter les modifications concurrentes
            //     executorService.submit(() -> {
            //         try {
            //             s.afficherMessage(d);
            //         } catch (RemoteException e) {
            //             // Si jamais une exception de type "ConnectException" est déclenchée lorsqu'un client est déconnecté mais toujours présent dans la liste
            //             // alors on l'enlève de la liste, comme ça, le serveur ne se bloque pas.
            //             synchronized (this) {
            //                 this.tableaux.remove(s);
            //             }
            //             e.printStackTrace();
            //         }
            //     });
            // }
            }


    }   

    public ArrayList<ServiceTableauBlanc> getServiceTableauBlancs(){
        return this.tableaux;
    }
}
