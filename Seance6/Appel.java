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
            Registry regIUT = LocateRegistry.getRegistry("localhost"); /* se connecter à l'annuaire de la machine */

            ServiceAgenda listeContacts = (ServiceAgenda) regIUT.lookup("contacts");


            System.out.println("Liste de contact bien récupérée !");

            Scanner sc = new Scanner(System.in);
            boolean etat = true;
            while(etat){
                System.out.print("Que souhaitez vous faire ?\n[1] Ajouter un contact\n[2] Consulter un contact\n[3]Exit\n");
                System.out.print("[>] ");

                int entree = sc.nextInt();
                sc.nextLine();

                switch(entree){
                    case 1:
                        System.out.print("Pseudonyme : ");
                        String nom = sc.nextLine();
                        System.out.print("Email : ");
                        String email = sc.nextLine();
                        Contact c = new Contact(nom, email);
                        listeContacts.ajouterContact(c);

                        System.out.println("Contact ajouté ! ");
                        break;
                    case 2:
                        System.out.print("Pseudonyme : ");
                        String pseudo = sc.nextLine();
                        System.out.println("Voici les informations du contact : " + listeContacts.accederContact(pseudo));
                        break;
                    case 3:
                        etat = false;
                        break;
                }

            }

            sc.close();


        }catch(ConnectException e){
            System.err.println("Connexion à l'hôte refusée");
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