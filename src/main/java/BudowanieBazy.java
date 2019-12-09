import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

public class BudowanieBazy {
    public static void main(String[] args) {
        Node firstNode;
        Node secondNode;
        Relationship relationship;

        GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("C://Praca_Licencjacka//Baza_Grafowa"));

        try (Transaction tx = graphDb.beginTx())
        {
            firstNode = graphDb.createNode();
            firstNode.setProperty("message", "Hello, ");
            secondNode = graphDb.createNode();
            secondNode.setProperty("message", "World!");

            relationship = firstNode.createRelationshipTo(secondNode, RelTypes.ZNA);
            relationship.setProperty( "message", "brave Neo4j " );

            System.out.print( firstNode.getProperty( "message" ) );
            System.out.print( relationship.getProperty( "message" ) );
            System.out.print( secondNode.getProperty( "message" ) );

            tx.success();
        }






    }




}
