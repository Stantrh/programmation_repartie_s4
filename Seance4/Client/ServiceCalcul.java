import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ServiceCalcul extends Remote{
    public double calculer(double a, double b) throws RemoteException;
}