import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
public class Addition implements ServiceCalcul{
    
    public double calculer(double a, double b)throws RemoteException{
        String host = "";
        try{
            host = RemoteServer.getClientHost();
            System.out.println("Appel à : " + this.getClass() + " par : " + host);
        }catch(ServerNotActiveException e) { 
            e.printStackTrace();
        }
        return a+b;
    }
}