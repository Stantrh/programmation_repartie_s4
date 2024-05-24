import java.io.Serializable;

public class Contact implements Serializable{
    private String pseudo;
    private String email;
    
    public Contact(String p, String e) {
       this.pseudo = p;
       this.email = e;
    }

    public String toString() {
       return this.pseudo + " : " + this.email;
    }
    
    public String getPseudo() {
       return this.pseudo;
    }
    
    public String getEmail() {
       return this.email;
    }
}