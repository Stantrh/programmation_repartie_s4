import java.rmi.Remote;
import java.rmi.RemoteException;
public class Distributeur implements ServiceDistributeur{
    private int num;

    public Distributeur(int n){
        this.num = n;
    }

    public Distributeur(){
        this.num = 0;
    }
    
    public int DonnerTicket() throws RemoteException{
        this.num++;
        System.out.println(this.num); // Le service doit écrire sur son terminal le numéro de ticket envoyé à chaque requête.
        return this.num;
    }
}