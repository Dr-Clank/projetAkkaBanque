package sd.akka.model;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import sd.akka.actor.BanqueActor;
import java.util.HashMap;
import java.util.Map;

public class Banque {
    private ActorRef bankActor;

    public Banque(ActorRef bankActor){
        this.bankActor = bankActor;
    }

    public ActorRef getBanqueActor() {
        return bankActor;
    }

    public void setBanqueActor(ActorRef banqueActor) {
        this.bankActor = bankActor;
    }
    
}
