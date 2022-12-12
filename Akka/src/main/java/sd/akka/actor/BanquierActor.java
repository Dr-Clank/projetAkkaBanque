package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import sd.akka.model.*;

import java.time.Duration;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletionStage;

public class BanquierActor extends AbstractActor {
    //acteur suivant
    public ActorRef actorRef;

    public Banquier BanquierInfo;

	// Méthode servant à déterminer le comportement de l'acteur 
    //lorsqu'il reçoit un message
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(DemandeDeSolde.class,message -> demandeDeSolde(message))
                .build();
    }

    private BanquierActor(Banquier BanquierInfo){
        this.BanquierInfo = BanquierInfo;
    }   

    private void demandeDeSolde(final DemandeDeSolde demande){
        System.out.println("identifiant recus par banquier : "+demande.idClient);
        this.getSender().tell(new ClientActor.RecevoirSolde(250), getSelf());
    }

    public static Props props(Banquier banquierInfo) {
		return Props.create(BanquierActor.class, banquierInfo);
	}

    // Définition des messages en inner classes
	public interface Message {}
	
    //Va randomiser une lettre
	public static class DemandeDeSolde implements Message {
        private int idClient;
		public DemandeDeSolde(int idClient) {
            this.idClient = idClient;
        }
	}

    
}
