import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

public class LancerServiceCalcul{

    public static void main (String args[]) throws RemoteException {
        Addition addition = new Addition();
        Soustraction soustraction = new Soustraction();
        Multiplication multiplication = new Multiplication();
        Division division = new Division();
        ServiceCalcul ra = (ServiceCalcul) UnicastRemoteObject.exportObject(addition,0); 
        ServiceCalcul rs = (ServiceCalcul) UnicastRemoteObject.exportObject(soustraction,0); 
        ServiceCalcul rm = (ServiceCalcul) UnicastRemoteObject.exportObject(multiplication,0); 
        ServiceCalcul rd = (ServiceCalcul) UnicastRemoteObject.exportObject(division,0); 
        
        
        /* Un_port = un entier particulier ou 0 pour auto-assigné */
        Registry reg = LocateRegistry.getRegistry("localhost"); /* Création de l'annuaire */
        reg.rebind("AdditionDistante", ra); 
        reg.rebind("SoustractionDistante", rs);
        reg.rebind("MultiplicationDistante", rm); 
        reg.rebind("DivisionDistante", rd);  
        
    }
}