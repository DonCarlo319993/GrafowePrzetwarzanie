import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MtxToCsv {
    public static void main(String[] args) throws FileNotFoundException {
        File macierz = new File("C:/Users/Karol/Desktop/Macierze/bcsstk20/bcsstk20.mtx");
        PrintWriter zapis = new PrintWriter("C:/Users/Karol/Desktop/Macierze/bcsstk20/bcsstk20.csv");
        Scanner odczyt = new Scanner(macierz);
        String tekst = odczyt.nextLine();
        String[] aktualnaLinia;
        String dopisz;

        while (tekst.startsWith("%")){
            tekst = odczyt.nextLine();
            System.out.println(tekst);
        }
        aktualnaLinia = tekst.split(" ");

        while (odczyt.hasNextLine()){
            tekst = odczyt.nextLine();
            aktualnaLinia = tekst.split(" ");
            dopisz = aktualnaLinia[0]+","+aktualnaLinia[1]+","+aktualnaLinia[2];
            zapis.println(dopisz);
        }
        zapis.close();

        GraphDatabaseService db = new GraphDatabaseFactory()
                .newEmbeddedDatabase(new File("C:/Users/Karol/Desktop/Macierze/bcsstk20/grafek/"));

        try ( Transaction tx = db.beginTx())
        {
            Result importanteDeLaNoche = db.execute("USING PERIODIC COMMIT 1" +
                    "LOAD CSV FROM 'file:///C:/Users/Karol/Desktop/Macierze/bcsstk20/bcsstk20.csv'" +
                    "WITH toInteger(row[0]) AS first, toInteger(row[1]) AS second, toFloat(row[2]) AS value" +
                    "MATCH (f:First {first: first}) " +
                    "MATCH (s:Second {second: second})");

            tx.success();
        }

    }
}
