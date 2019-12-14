import org.neo4j.codegen.bytecode.If;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WczytajMacierz {
    public static void main(String[] args) throws FileNotFoundException {


        File macierz = new File("C:/Users/Karol/Desktop/Macierze/ash85/ash85.mtx");
        Scanner odczyt = new Scanner(macierz);
        String tekst = odczyt.nextLine();
        String[] aktualnaLinia;
        int liczba;
        int iterator = 0;
        int rozmiar = 0;



        while (tekst.startsWith("%")){
            tekst = odczyt.nextLine();
            System.out.println(tekst);
        }

        List<List<Float>> wierzcholek = new ArrayList<List<Float>>();
        aktualnaLinia = tekst.split(" ");
        liczba = Integer.parseInt(aktualnaLinia[0]);
        for (int i = 0; i<liczba; i++){
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


    }
}
