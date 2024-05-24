import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ListeContacts implements ServiceAgenda {
    private Map<String, Contact> liste = new HashMap<String, Contact>();
    public ListeContacts(){ }

    public Contact accederContact(String pseudo) throws RemoteException{

        if (liste.containsKey(pseudo)) {
            System.out.println("\t Accès à -> " + pseudo);
            return this.liste.get(pseudo);
        } 
        else return null;

    }

    public void ajouterContact(Contact c) throws RemoteException{

        this.liste.put(c.getPseudo(), c);
        System.out.println("\t Ajout de -> " + c);


    }
}

