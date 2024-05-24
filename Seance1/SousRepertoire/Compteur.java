import java.rmi.Remote;
import java.rmi.RemoteException;
public class Compteur implements CompteurInterface{
    private int num;

    public Compteur(int n){
        this.num = n;
    }

    public Compteur(){
        this.num = 0;
    }

    public void iterer(){
        this.num++;
    }
    
    public int numero() throws RemoteException{
        return this.num;
    }
}