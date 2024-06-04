import java.rmi.RemoteException;
import java.util.ArrayList;

public class DessinThread extends Thread{
    private Dessin dessin;
    private ServiceTableauBlanc service;
    private ArrayList<ServiceTableauBlanc> liste;

    public DessinThread(Dessin d, ServiceTableauBlanc s, ArrayList<ServiceTableauBlanc> l){
        this.dessin = d;
        this.service = s;
        this.liste = l;
    }

    @Override
    public void run() {
        try{
            this.service.afficherMessage(this.dessin);
        }catch(RemoteException e){
            e.printStackTrace();
            this.liste.remove(service);
        }
        
        
    }
}
