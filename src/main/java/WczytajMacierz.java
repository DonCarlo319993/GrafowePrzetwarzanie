import org.neo4j.codegen.bytecode.If;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WczytajMacierz {
    public static void main(String[] args) throws FileNotFoundException {


        File macierz = new File("C:/Users/Karol/Desktop/Macierze/ash85/ash85.mtx");
        Scanner odczyt = new Scanner(macierz);
        String tekst = odczyt.nextLine();
        String[] aktualnaLinia;
        int liczbaWierzch;
        int liczbaRel;
        int iterator = 0;
        int rozmiar = 0;
        int indeks;
        float temp;



        while (tekst.startsWith("%")){
            tekst = odczyt.nextLine();
            System.out.println(tekst);
        }

        List<List<Float>> wierzcholek = new ArrayList<List<Float>>();
        aktualnaLinia = tekst.split(" ");
        liczbaWierzch = Integer.parseInt(aktualnaLinia[0]);
        liczbaRel = Integer.parseInt(aktualnaLinia[2]);
        for (int i = 0; i<liczbaWierzch; i++){
            wierzcholek.add(new LinkedList<Float>());
        }


        System.out.println("\nOd tego momentu zaczyna się macierz: \n");

        while (odczyt.hasNextLine()){
            tekst = odczyt.nextLine();
            System.out.println(tekst);
            aktualnaLinia = tekst.split(" ");
            List<Float> aktualnaLiniaInt = new ArrayList<>();



            if (aktualnaLinia.length < 3) {
                aktualnaLiniaInt.add(Float.parseFloat(aktualnaLinia[aktualnaLinia.length - 1]));
                aktualnaLiniaInt.add((float) 1);
            }else {
                aktualnaLiniaInt.add(Float.parseFloat(aktualnaLinia[aktualnaLinia.length - 2]));
                aktualnaLiniaInt.add(Float.parseFloat(aktualnaLinia[aktualnaLinia.length - 1]));

            }


            iterator = Integer.parseInt(aktualnaLinia[0]);
            if (wierzcholek.get(iterator).isEmpty()) {
                wierzcholek.add(iterator, aktualnaLiniaInt);
                rozmiar++;
//                wierzcholek.remove(rozmiar);


            }else {
                List<Float> scalona = new ArrayList<>(wierzcholek.get(iterator));
                scalona.addAll(aktualnaLiniaInt);
                wierzcholek.set(iterator, scalona);

            }
        }

        System.out.println("\n\n");

        for (int i = 0; i < rozmiar - 1; i++){
            wierzcholek.remove(wierzcholek.size()-1);
        }

        for (int i = 0; i<wierzcholek.size(); i++){
            System.out.println("Wierzchołek "+i+": "+wierzcholek.get(i));
        }

        //Tutaj będę tworzył bazę grafową

        List<Node> nodes = new ArrayList<>();
        List<Relationship> relacje = new ArrayList<>();
        Relationship testowa;

        GraphDatabaseService graf = new GraphDatabaseFactory().newEmbeddedDatabase(new File("C://Praca_Licencjacka//Baza_Grafowa"));


        try(Transaction tx = graf.beginTx()) {
            for (int i = 0; i < liczbaWierzch; i++) {
                nodes.add(i, graf.createNode());
            }
            for (int i = 0; i<nodes.size()-1; i++){
                nodes.get(i).setProperty("value", i);
            }

/*            System.out.println(nodes.get(6).getProperty("value"));
            System.out.println(Math.round(wierzcholek.get(2).get(0)));

            testowa = nodes.get(2).createRelationshipTo(nodes.get(3), RelTypes.RELACJA);
            relacje.add(testowa);
            testowa = nodes.get(5).createRelationshipTo(nodes.get(7), RelTypes.RELACJA);
            relacje.add(testowa);*/


            for (int i=1; i<liczbaWierzch; i++){
                System.out.println(wierzcholek.get(i).get(0));
                temp = wierzcholek.get(i).get(0);
                indeks = (int) temp;
                testowa = nodes.get(i).createRelationshipTo(nodes.get(indeks), RelTypes.RELACJA);
                testowa.setProperty("value", wierzcholek.get(i).get(1));
//                relacje.add(testowa);
//                testowa = nodes.get(i).createRelationshipTo(nodes.get()), RelTypes.RELACJA;

//                relacje.add(i, nodes.get(i).createRelationshipTo(nodes.get(indeks), RelTypes.RELACJA));

            }





            tx.success();
        }






    }
}
