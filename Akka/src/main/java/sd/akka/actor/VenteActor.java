package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class VenteActor extends AbstractActor {

    private int stock;
	
	private VenteActor() {
		this.stock = 10;
	}

    //Etat par défault
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Vente.class, Vmessage -> acheter(Vmessage))
				.match(Livraison.class, Lmessage -> restock(Lmessage))
				.build();
	}

	/*
	 * Méthode alternative qui peut être utilisée lors de la réception d'un message.
	 */
	public Receive createReceiveRestock() {
		return receiveBuilder()
                .match(Vente.class, Vmessage -> noStock())
				.match(Livraison.class, Lmessage -> restock(Lmessage))
				.build();
	}
	
    private void acheter(final Vente vente){
        this.stock = this.stock - vente.achat;
        if (this.stock<=0) {
			// Changement du comportement de l'acteur
			getContext().become(createReceiveRestock());
		}
    }

    private void noStock(){
        System.out.println("Stock empty");
    }

    private void restock(final Livraison livre){
        this.stock = this.stock + livre.livraison;
        System.out.println(this.stock + " stock");
        if (this.stock>0) {
			// Changement du comportement de l'acteur
			getContext().become(createReceive());
		}
    }


	public static Props props() {
		return Props.create(VenteActor.class);
	}

	// Définition des messages en inner classes
	public interface Message {}
	
	public static class Vente implements Message {
        public int achat;
		public Vente( int achat) {
            this.achat = achat;
        }
	}

    public static class Livraison implements Message {
        public int livraison;
		public Livraison(int livraison) {
            this.livraison = livraison;
        }
	}
}
