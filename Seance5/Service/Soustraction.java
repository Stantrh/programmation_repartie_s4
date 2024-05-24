import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
public class Soustraction implements ServiceCalcul{
    
    public double calculer(double a, double b) throws RemoteException{
        double res = 0;
        try{
            res = a-b;
            String host = "";
            host = RemoteServer.getClientHost();
            System.out.println("Appel à : " + this.getClass() + " par : " + host);

        } catch (ArithmeticException e){
            // System.out.err("Division par 0");
            e.printStackTrace();
        } catch(ServerNotActiveException e){
            e.printStackTrace();
        }
        return res;
    }
}