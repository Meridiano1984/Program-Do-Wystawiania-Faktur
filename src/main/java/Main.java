import DataBase.DatabaseInitialization;
import com.sun.jdi.connect.spi.TransportService;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class Main {


    public static void  main(String[] args){

        //KOMENDY DO INICJALIZACJI BAZY DANYCH MYSQL
        //TODO dodac inicjalizacje schema w MYSQL a nie robic to recznie



        DatabaseInitialization databaseInitialization = new DatabaseInitialization();

        //KOMENDA DO USOWANIA POPRZEDNIEJ BAZY DANYCH
        databaseInitialization.usuwanieTabel();

        databaseInitialization.tworzenieBazyDanychv2();
        databaseInitialization.dodaniePrzykładowychdanych();



        // DODANIE PARU KONTRACHENTOW

        LinkedList<Kontrachent> listaKontrachentow = new LinkedList<>();
        listaKontrachentow.add(new Kontrachent("MIRBUDEX","1234567890"));
        listaKontrachentow.add(new Kontrachent("Biedronka", "334512967"));
        listaKontrachentow.add(new Kontrachent("Oracle", "815462967"));


        // DODANIE PARU PRODUKTOW
        LinkedList<Produkt> listaProduktow = new LinkedList<>();
        listaProduktow.add(new Produkt("Mleko", 2.50f));
        listaProduktow.add(new Produkt("Czekolada", 10.00f));
        listaProduktow.add(new Produkt("Samochód", 10000.00f));

        //DODANIE 10 NOWYCH FAKTUR
        int liczbaFakturDoWprowadzenia = 10;
        LocalDate dataWystawienia = LocalDate.of(2021,8,11);
        String  nabywca =  new String("Mirbux");
        int     cenaBrutto = 0;

        ArrayList<Faktura> listaFaktur = new ArrayList<>(liczbaFakturDoWprowadzenia);
        for(int i  =0; i< liczbaFakturDoWprowadzenia; i++) {
            listaFaktur.add(new Faktura(dataWystawienia, listaKontrachentow.get(1)));
        }


        do{
            Menu.wyswietlanieMenu(listaFaktur);
            Faktura.setLiczbaFaktur(listaFaktur.size());
        }while (true);

    }
}
