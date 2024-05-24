import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.*;
	


public class Appel{
    public static void main(String[] args) throws RemoteException, NotBoundException, IndexOutOfBoundsException, ServerNotActiveException{

        try{
            Registry regIUT = LocateRegistry.getRegistry("charlemagne.iutnc.univ-lorraine.fr", 3333); /* se connecter à l'annuaire de la machine */
            Registry regCalcul = LocateRegistry.getRegistry(args[0]);

            ServiceCalcul addition = (ServiceCalcul) regCalcul.lookup("Addition");
            ServiceCalcul division = (ServiceCalcul) regCalcul.lookup("Division");
            ServiceMeteo meteo = (ServiceMeteo) regIUT.lookup("meteo");

            System.out.println("Service météo bien récupéré. Voici les options disponibles : date, station, max, min, moyennne, maj");

            Scanner sc = new Scanner(System.in);

            String entree = sc.nextLine();
            while(!entree.equals("q")){
                switch(entree){
                    case "date":
                        System.out.println(meteo.date());
                        break;
                    case "station":
                        System.out.println(meteo.station());
                        break;
                    case "max":
                        System.out.println(meteo.max());
                        break;
                    case "min":
                        System.out.println(meteo.min());
                        break;
                    case "moyenne":
                        System.out.println(meteo.moyenne());
                        break;
                    case "maj":
                        meteo.maj();
                        break;
                    case "diff":
                        // On calcule la moyenne de juste cette journée en appelant notre calcul 
                        double somme = addition.calculer(meteo.min(), meteo.max()); // min + max
                        double res = division.calculer(somme, 2); // min + max / 2
                        // Puis on peut comparer les deux valeurs : 
                        System.out.println("Température moyenne annuelle en ce jour : " + meteo.moyenne() + "\nTempérature moyenne aujourd'hui spécifiquement : " + res);
                        break;
                    default:
                        System.out.println("Option invalide. Options disponibles : date, station, max, min, moyennne, maj");
                        break;
                }
                meteo.maj();
                entree = sc.nextLine();
            }

        }catch(ConnectException e){
            System.err.println("Connection à l'hôte refusée");
        }catch(UnknownHostException e){
            System.err.println("Hôte inconnue");
        }catch(ConnectIOException e){
            System.err.println("Erreur IO lors de la création de la connexion");
        }catch(MarshalException e){
            System.err.println("I/O error marshaling transport header, marshaling call header, or marshaling arguments.");
        }catch(NoSuchObjectException e){
            System.err.println("Objet demandé dans l'annuaire non disponible.");
        }

    }
}