import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
 import java.rmi.server.ServerNotActiveException;
public class Appel{
    public static void main(String[] args) throws RemoteException, NotBoundException, IndexOutOfBoundsException, ServerNotActiveException{

        Registry reg = LocateRegistry.getRegistry(args[0]); /* se connecter à l'annuaire de la machine */
        
        ServiceDistributeur c = (ServiceDistributeur) reg.lookup("LeDistributeur");
        System.out.println("REF SHE " + c);

        Scanner sc = new Scanner(System.in);
        String entree = sc.nextLine();
        while(!entree.equals("q")){ 
            if(entree.equals("n")){
                System.out.println("Valeur du compteur : " + c.DonnerTicket());
                entree = sc.nextLine();
            }
        }
    }
}