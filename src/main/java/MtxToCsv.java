import org.neo4j.cypher.internal.v3_5.ast.LoadCSV;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.index.internal.gbptree.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MtxToCsv {
    public static void main(String[] args) throws FileNotFoundException {
        File macierz = new File("C:/Users/Karol/Desktop/Macierze/Stranke94/Stranke94.mtx");
        PrintWriter zapis = new PrintWriter("C:/Users/Karol/Desktop/Macierze/Stranke94/Stranke94.csv");
        Scanner odczyt = new Scanner(macierz);
        String tekst = odczyt.nextLine();
        String[] aktualnaLinia;
        String dopisz;
        int rozmiarM;

        while (tekst.startsWith("%")){
            tekst = odczyt.nextLine();
            System.out.println(tekst);
        }
        aktualnaLinia = tekst.split(" ");
        rozmiarM = Integer.parseInt(aktualnaLinia[0]);

        while (odczyt.hasNextLine()){
            tekst = odczyt.nextLine();
            aktualnaLinia = tekst.split(" ");
            dopisz = aktualnaLinia[0]+","+aktualnaLinia[1]+","+aktualnaLinia[2];
            zapis.println(dopisz);
        }
        zapis.close();

        GraphDatabaseService db = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder(new File("C:/Users/Karol/Desktop/Macierze/Stranke94/grafek/"))
                .setConfig(GraphDatabaseSettings.allow_file_urls, "true").newGraphDatabase();


        try ( Transaction tx = db.beginTx())
        {
            String query;

            for (int i = 1; i<=rozmiarM; i++) {
                query = "CREATE (:Pierwszy {PierwszyId:"+i+" } )";
                Result zapytanko = db.execute(query);
            }

            Result importanteDeLaNoche =
                    db.execute("LOAD CSV FROM 'file:///C:/Users/Karol/Desktop/Macierze/Stranke94/Stranke94.csv' " +
                            "AS row" +
                            " MATCH (a:Pierwszy {PierwszyId: toInteger(row[0])}), (b:Pierwszy {PierwszyId: toInteger(row[1])}) " +
                            " CREATE (a)-[rel:zawiera {value:row[2]}]->(b)" +
                            " RETURN a, b");


            tx.success();
        }

    }
}
