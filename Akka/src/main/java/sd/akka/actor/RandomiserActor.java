package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.pattern.Patterns;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletionStage;

public class RandomiserActor extends AbstractActor {
    //acteur suivant
    public ActorRef nextActor;
    public static final String ALPHABET = "abcdefghijjkmnlopqrstuvwxyz";

    public int myRank;
    public String myString;

	// Méthode servant à déterminer le comportement de l'acteur 
    //lorsqu'il reçoit un message
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(RandomiseString.class, message -> randomiseString(message))
                .build();
    }

    private RandomiserActor(int newRank){

        this.myRank = newRank;
        //si mon rang est 0 affiche, sinon envoie au prochain
        if(newRank !=0){
            this.nextActor = getContext().actorOf(RandomiserActor.props(newRank));
        }
    }   

    private void randomiseString(final RandomiseString message){
        //récupère la longueur du message
        int stringLength = message.messageToRandomise.length();

        //randomise les lettres et les messages
        Random r = new Random();
        int selectedLetter = r.nextInt(ALPHABET.length());
        int selectedChar = r.nextInt(stringLength);
        
        //créer la chaine randomiser
        message.messageToRandomise = message.messageToRandomise.substring(0, selectedChar) + ALPHABET.charAt(selectedLetter) + message.messageToRandomise.substring(selectedChar + 1);
        
        System.out.println(message.messageToRandomise);
        if(myRank !=0){
            nextActor.forward(message,getContext()); //forward de mail
        }else{
            ActorRef actorRef = this.getSender(); //permet de récupéré l'émétteur INITIAL
            actorRef.tell(message.messageToRandomise, getSelf()); //message, nous
        }
        //nextActor.tell(new RandomiserActor.RandomiseString(), this.getSelf());
        //if forward, contient l'émétteur du message et permet d'utiliser "getSender()"
    }

    public static Props props(int nextRank) {
        nextRank= nextRank-1;
		return Props.create(RandomiserActor.class, nextRank);
	}

    // Définition des messages en inner classes
	public interface Message {}
	
    //Va randomiser une lettre
	public static class RandomiseString implements Message {
        public String messageToRandomise;
		public RandomiseString(String messageToRandomise) {
            this.messageToRandomise = messageToRandomise;
        }
	}
}
