import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ServiceAgenda extends Remote {

    public Contact accederContact(String pseudo) throws RemoteException;

    public void ajouterContact(Contact c) throws RemoteException;
}
