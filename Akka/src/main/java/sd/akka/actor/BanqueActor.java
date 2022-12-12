package sd.akka.actor;

import sd.akka.model.*;
import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.pattern.Patterns;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletionStage;

public class BanqueActor extends AbstractActor {
    //acteur suivant
    public ActorRef actorRef;

    private Map<Integer, ActorRef> banquiers;

	// Méthode servant à déterminer le comportement de l'acteur 
    //lorsqu'il reçoit un message
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(GetSolde.class, message -> demanderSolde(message))
                .build();
    }

    private BanqueActor(ArrayList<Banquier> bnks){
        this.banquiers = new HashMap<>();
        for(Banquier bk: bnks){
            this.banquiers.put(bk.getId(),getContext().actorOf(BanquierActor.props(bk)));
        }
    }   

    private void demanderSolde(final GetSolde gt){
        System.out.println("Message banque reçus : "+gt.Client.getId());
        if(gt.Client.getIdBanquier()!= 0){
            //Forward le message vers le banquier SI il est présent
            if(this.banquiers.containsKey(gt.Client.getIdBanquier())){
                System.out.println("coucou");
                ActorRef banquierActor = this.banquiers.get(gt.Client.getIdBanquier());
                banquierActor.forward(new BanquierActor.DemandeDeSolde(gt.Client.getId()), this.getContext());
            }else{
                // le banquier n'est pas disponible
            }
        }else{
            //Assigne un Banquier au client
            if(this.banquiers.size()!=0){
                
            }
        }
        
    }

    private boolean checkIfClientHasBanquier(){
        boolean result = true;
        return result;
    }

    public static Props props(ArrayList<Banquier> bnks) {
		return Props.create(BanqueActor.class, bnks);
	}

	public interface Message {}
	
	public static class GetSolde implements Message {
        private Client Client;
		public GetSolde(Client Client) {
            this.Client = Client;
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
