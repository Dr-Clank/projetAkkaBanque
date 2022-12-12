package sd.akka;

import java.sql.*;
import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import sd.akka.actor.*;
import sd.akka.model.*;

public class App {
	public static void main(String[] args) {
        //Jdb mysql tuto
        //ConnectMySql connection = new ConnectMySql();
        //Connection conn = connection.getConn();
        //Create statement par fonction et fermeture (mettre en await)

		ActorSystem actorSystem = ActorSystem.create();
        ArrayList<Banquier> banquierDispo = new ArrayList<Banquier>();
        banquierDispo.add(new Banquier(1, "Jhon"));
        banquierDispo.add(new Banquier(2, "Roger"));
        banquierDispo.add(new Banquier(3, "Albert"));
        banquierDispo.add(new Banquier(4, "Svetlana"));
        
        Banque bank = new Banque(actorSystem.actorOf(BanqueActor.props(banquierDispo)));
        bank.getBanqueActor();
        for(int i = 1; i<=10; i++) {
            Client clientObj = new Client(i,"Max",2);
            ActorRef clientActor = actorSystem.actorOf(ClientActor.props(clientObj, bank));
            clientActor.tell(new ClientActor.DemanderSolde(), ActorRef.noSender());
        }
      
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        // Arrêt du système d'acteurs
        actorSystem.terminate();
	}
}
