package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import sd.akka.model.*;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletionStage;

public class ClientActor extends AbstractActor {
    //acteur suivant
    public Banque bank;

    public Client clientInfo;

	// Méthode servant à déterminer le comportement de l'acteur 
    //lorsqu'il reçoit un message
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(DemanderSolde.class,message -> demanderSolde())
                .match(RecevoirSolde.class,message -> recevoirSolde(message))
                .build();
    }

    private ClientActor(Client clientInfo, Banque bank){
        this.clientInfo = clientInfo;
        this.bank = bank;
    }   

    private void demanderSolde(){
        this.bank.getBanqueActor().tell(new BanqueActor.GetSolde(this.clientInfo), getSelf());
    }

    private void recevoirSolde(final RecevoirSolde rs){
        System.out.println("Client no "+this.clientInfo.getId()+" "+rs.montantRecus+"€");
    }

    public static Props props(Client clientInfo, Banque bank) {
		return Props.create(ClientActor.class, clientInfo, bank);
	}

    // Définition des messages en inner classes
	public interface Message {}
	
    //Va randomiser une lettre
	public static class DemanderSolde implements Message {
		public DemanderSolde() {
        }
	}

    //Va randomiser une lettre
	public static class RecevoirSolde implements Message {
        public float montantRecus;
		public RecevoirSolde(float montant ) {
            this.montantRecus = montant;
        }
	}
}
