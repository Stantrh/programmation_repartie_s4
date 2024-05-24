import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.HashMap;
import java.util.Map;

public class ListeContactsExp implements ServiceAgenda {
    private Map<String, Contact> liste = new HashMap<String, Contact>();
    public ListeContactsExp(){ }

    public Contact accederContact(String pseudo) throws RemoteException{
        try{
            String host = "";
            host = RemoteServer.getClientHost();
            System.out.println("Appel à : la méthode accederContact par : " + host);

            if (liste.containsKey(pseudo)) {
                System.out.println("\t Accès à -> " + pseudo);
                return this.liste.get(pseudo);
            } 
            else return null;
        } catch(ServerNotActiveException e){
            e.printStackTrace();
        }
        return null;
    }

    public void ajouterContact(Contact c) throws RemoteException{
        try{

            String host = "";
            host = RemoteServer.getClientHost();
            System.out.println("Appel à : la méthode ajouterContact par : " + host);

            this.liste.put(c.getPseudo(), c);
            System.out.println("\t Ajout de -> " + c);
        }catch(ServerNotActiveException e){
            e.printStackTrace();
        }

    }
}

