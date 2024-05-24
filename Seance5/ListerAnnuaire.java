import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.UnknownHostException;

public class ListerAnnuaire{
    public static void main(String[] args) throws RemoteException{

        Registry reg = null;
        int port = 0;
        if(args.length > 0){
            if(args.length > 1){
                port = Integer.valueOf(args[1]);
            }
            reg = LocateRegistry.getRegistry(args[0], port);
        }else{
            reg = LocateRegistry.getRegistry("localhost");
        }
        
        String[] services = reg.list();
        for(String s : services){
            System.out.println(s);
        }
    }
}