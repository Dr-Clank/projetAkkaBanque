package sd.akka.model;

import java.util.HashMap;
import java.util.Map;

public class Banquier {
    
    private int id;
    private String nomBanquier;
    private Map<Integer, Client> clients;

    public Banquier(int id, String nomBanquier) {
        this.id = id;
        this.nomBanquier = nomBanquier;
        this.clients = new HashMap<Integer, Client>();
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
    
    private boolean addClient(Client client){
        boolean result = false;
        if(client!=null){
            this.clients.put(client.getId(), client);
            result = true;
        }
        return result;
    }

    private boolean removeClient(Client client){
        boolean result = false;
        if(client!=null){
            this.clients.remove(client.getId());
            result = true;
        }
        return result;
    }
}
