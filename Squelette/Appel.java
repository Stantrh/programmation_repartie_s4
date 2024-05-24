import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.ConnectException;
import java.rmi.activation.ActivateFailedException;
public class Appel{


    public static void main(String[] args){

        try{
        Registry reg = LocateRegistry.getRegistry("localhost"); /* se connecter à l'annuaire de la machine */
        
        CompteurInterface c = (CompteurInterface) reg.lookup("CompteurDistant");

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
        }catch(ActivateFailedException e){
            System.err.println("Thrown by RMI runtime when activation fails during a remote call to an activatable object");
        }

    }
}