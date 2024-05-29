import java.util.ArrayList;
public class ServiceDistributeurTableauBlanc implements TableauBlancServeur {
    private ArrayList<ServiceTableauBlanc> tableaux;
    // private ArrayList<Dessin> dessins;

    public ServiceDistributeurTableauBlanc(){
        this.tableaux = new ArrayList<>();
        this.dessins = new ArrayList<>();
    }

    public void enregistrerClient(ServiceTableauBlanc c){
        this.tableaux.add(c);
    }

    public void distribuerMessage(Dessin d){
        for(ServiceTableauBlanc s : this.tableaux){
            s.afficherMessage(d);
        }
    }
}
