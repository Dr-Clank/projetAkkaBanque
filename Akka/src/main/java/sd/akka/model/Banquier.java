package sd.akka.model;

import java.util.HashMap;
import java.util.Map;

public class Banquier {
    
    private int id;
    private String nomBanquier;

    public Banquier(int id, String nomBanquier) {
        this.id = id;
        this.nomBanquier = nomBanquier;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNomBanquier(String nomBanquier) {
        this.nomBanquier = nomBanquier;
    }
    public String getNomBanquier() {
        return nomBanquier;
    }
}
