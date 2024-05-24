import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

public interface ServiceCalcul extends Remote{
    public double calculer(double a, double b) throws RemoteException;
}