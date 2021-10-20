import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void wyswietlanieMenu(ArrayList<Faktura> listaFaktur){

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                        Program do wystawiania faktur               Całkowita lIczba faktur wynosi:" + Faktura.getLiczbaFaktur());
        System.out.println("1.Wyswietl wszystkie faktury");
        System.out.println("2.Dodaj nowa Fakture");
        System.out.println("3.Wyswietl wszystkich kontrachentow z MYSQL");
        System.out.println("4.Wyswietlanie produktów z bazy danych");
        System.out.println("Twój wybór: ");

        Scanner scanner = new Scanner(System.in);
        int liczbaSterujaca =scanner.nextInt();

        switch (liczbaSterujaca){
            case 1:
//                Faktura.wyswietlanieFaktur(listaFaktur);
                Faktura.wyswietlanieFakturZBazyDnaych();

                break;
            case 2:
//                Faktura.dodanieNowejFaktury(listaFaktur);
                Faktura.dodawnaieNowejFakturyv2();

                break;
            case 3:
//                Faktura.wyswietlanieKonkretnejFaktury(listaFaktur);
                Kontrachent.wyswietlanieWszystkichKontrachentow();
                break;
            case 4:
                Produkt.wyswietlanieWszystkichProduktow();
                break;

            case 21:
                System.out.println("Wyjscie z programu");
                System.exit(0);
                break;
        }
    }


    public static void wyswietlanieMenuDodawaniaProduktowDoFaktury(){

    }




}
