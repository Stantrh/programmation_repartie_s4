import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
 import java.rmi.server.ServerNotActiveException;
public class Appel{
    public static void main(String[] args) throws RemoteException, NotBoundException, IndexOutOfBoundsException, ServerNotActiveException{

        try{
            Registry reg = LocateRegistry.getRegistry(args[0]); /* se connecter à l'annuaire de la machine */
            
            ServiceCalcul ad = (ServiceCalcul) reg.lookup("AdditionDistante");
            ServiceCalcul sd = (ServiceCalcul) reg.lookup("SoustractionDistante");
            ServiceCalcul dd = (ServiceCalcul) reg.lookup("DivisionDistante");
            ServiceCalcul md = (ServiceCalcul) reg.lookup("MultiplicationDistante");
            // System.out.println("REF SHE " + c);

            Scanner sc = new Scanner(System.in);
            String entree = sc.nextLine();
            while(!entree.equals("q")){ 
                String[] calculs = entree.split(" ");
                double arg1 = Double.valueOf(calculs[0]);
                double arg2 = Double.valueOf(calculs[2]);
                Double total = null;
                switch(calculs[1]){
                    case "+":
                        total = ad.calculer(arg1, arg2);
                        break;
                    case "-":
                        total = sd.calculer(arg1, arg2);
                        break;
                    case "*":
                        total = md.calculer(arg1, arg2);
                        break;
                    case "/":
                        total = dd.calculer(arg1, arg2);
                        break;
                    default:
                        break;
                }
                
                System.out.println("Résultat de l'opération : " + total);
                entree = sc.nextLine();
                }   
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
        }catch(ActivateFailedException e){
            System.err.println("Thrown by RMI runtime when activation fails during a remote call to an activatable object");
        }

    }
}