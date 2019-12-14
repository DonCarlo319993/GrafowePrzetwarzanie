import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WczytajMacierz {
    public static void main(String[] args) throws FileNotFoundException {


        File macierz = new File("C:/Users/Karol/Desktop/Macierze/Stranke94/Stranke94.mtx");
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

        List<List<Integer>> wierzcholek = new ArrayList<List<Integer>>();
        aktualnaLinia = tekst.split(" ");
        liczba = Integer.parseInt(aktualnaLinia[0]);
        for (int i = 0; i<liczba; i++){
            wierzcholek.add(new LinkedList<Integer>());
        }


        System.out.println("\nOd tego moment zaczyna się macierz: \n");

        while (odczyt.hasNextLine()){
            tekst = odczyt.nextLine();
            System.out.println(tekst);
            aktualnaLinia = tekst.split(" ");
            List<Integer> aktualnaLiniaInt = new ArrayList<>();

            for (int i = 0; i<2; i++){
                aktualnaLiniaInt.add(Integer.parseInt(aktualnaLinia[i+1]));

            }
            iterator = Integer.parseInt(aktualnaLinia[0]);
            if (wierzcholek.get(iterator).isEmpty()) {
                wierzcholek.add(iterator, aktualnaLiniaInt);
                rozmiar++;
//                wierzcholek.remove(rozmiar);


            }else {
                List<Integer> scalona = new ArrayList<>(wierzcholek.get(iterator));
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
