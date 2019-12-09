import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

public class BudowanieBazy {
    public static void main(String[] args) {
        Node firstNode;
        Node secondNode;
        Relationship relationship;

        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("C://Praca_Licencjacka//Baza_Grafowa"));
        firstNode = graphDb.createNode();
        firstNode.setProperty("message", "Hello, ");
        secondNode = graphDb.createNode();
        secondNode.setProperty("message", "World!");



    }




}
