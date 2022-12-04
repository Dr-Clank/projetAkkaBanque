package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.pattern.Patterns;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletionStage;

public class BanqueActor extends AbstractActor {
    //acteur suivant
    public ActorRef nextActor;

    public String myString;

	// Méthode servant à déterminer le comportement de l'acteur 
    //lorsqu'il reçoit un message
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                //.match(RandomiseString.class, message -> randomiseString(message))
                .build();
    }

    private BanqueActor(){
    }   

    // private void randomiseString(final RandomiseString message){
       
    // }

    public static Props props(int nextRank) {
        nextRank= nextRank-1;
		return Props.create(RandomiserActor.class, nextRank);
	}

    // Définition des messages en inner classes
	public interface Message {}
	
    //Va randomiser une lettre
	public static class GetSolde implements Message {
		public GetSolde() {
        }
	}

    public static class Retirer implements Message {
        public float valeurARetirer;
		public Retirer(float valeurARetirer) {
            this.valeurARetirer = valeurARetirer;
        }
	}

    public static class Deposer implements Message {
        public float valeurADeposer;
		public Deposer(float valeurADeposer) {
            this.valeurADeposer = valeurADeposer;
        }
	}
}
