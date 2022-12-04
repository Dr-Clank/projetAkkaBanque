package sd.akka;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import sd.akka.actor.RandomiserActor;

public class App {
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create();

        ActorRef randomiserActor = actorSystem.actorOf(RandomiserActor.props(5));
        //randomiserActor.tell(new RandomiserActor.RandomiseString( "Lamastico"), ActorRef.noSender());
        CompletionStage<Object> result = Patterns.ask(randomiserActor,new RandomiserActor.RandomiseString( "Lamastico"), Duration.ofSeconds(5));
        try {
			String out = (String)result.toCompletableFuture().get();
            System.out.println(out +" main reçus");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
      
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        // Arrêt du système d'acteurs
        actorSystem.terminate();
	}
}
