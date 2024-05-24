import java.rmi.RemoteException;
import java.rmi.Remote;

public interface CompteurInterface extends Remote{
    public void iterer() throws RemoteException;
    public int numero() throws RemoteException;
}