package sd.akka.model;

public class Compte {
    private int id;
    private Client clientProprietaire;
    private float solde;

    public Compte(int id, Client clientProprietaire) {
        this.id = id;
        this.clientProprietaire = clientProprietaire;
        this.solde = 0;
    }
    public Compte(int id, Client clientProprietaire, float solde) {
        this.id = id;
        this.clientProprietaire = clientProprietaire;
        this.solde = solde;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Client getClientProprietaire() {
        return clientProprietaire;
    }
    public void setClientProprietaire(Client clientProprietaire) {
        this.clientProprietaire = clientProprietaire;
    }

    public float getSolde() {
        return solde;
    }
    public void setSolde(float solde) {
        this.solde = solde;
    }
}
