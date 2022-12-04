package sd.akka.model;

public class Client {
    private int id;
    private String nomClient;
    private int idBanquier;

    public Client(int id, String nomClient) {
        this.id = id;
        this.nomClient = nomClient;
        this.idBanquier = 0;
    }

    public Client(int id, String nomClient, int idBanquier) {
        this.id = id;
        this.nomClient = nomClient;
        this.idBanquier = idBanquier;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    public String getNomClient() {
        return nomClient;
    }

    public int getIdBanquier() {
        return idBanquier;
    }

    public void setIdBanquier(int idBanquier) {
        this.idBanquier = idBanquier;
    }
}
