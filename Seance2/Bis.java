import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
public class Bis{


    public static void main(String[] args)throws RemoteException, NotBoundException{

        Registry reg = LocateRegistry.getRegistry("localhost"); /* se connecter à l'annuaire de la machine */
        
        CompteurInterface c = (CompteurInterface) reg.lookup("CompteurDistant");
        System.out.println("REF SHE" + c);

        Scanner sc = new Scanner(System.in);
        String entree = sc.nextLine();
        while(!entree.equals("q")){ 
            if(entree.equals("n")) c.iterer();
            System.out.println("Valeur du compteur : " + c.numero(  ));
            entree = sc.nextLine();
        }
    }
}