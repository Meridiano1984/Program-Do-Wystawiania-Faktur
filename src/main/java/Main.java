import com.sun.jdi.connect.spi.TransportService;

import java.util.*;

public class Main {


    public static void  main(String[] args){


        // DODANIE PARU PRODUKTOW
        LinkedList<Produkt> listaProduktow = new LinkedList<>();
        listaProduktow.add(new Produkt("Mleko", 2.50f));
        listaProduktow.add(new Produkt("Czekolada", 10.00f));
        listaProduktow.add(new Produkt("Samochód", 10000.00f));

        //DODANIE 10 NOWYCH FAKTUR
        int liczbaFakturDoWprowadzenia = 10;
        Date    dataWystawienia = new Date(2021,8,11);
        String  nabywca =  new String("Mirbux");
        int     cenaBrutto = 0;

        ArrayList<Faktura> listaFaktur = new ArrayList<>(liczbaFakturDoWprowadzenia);
        for(int i  =0; i< liczbaFakturDoWprowadzenia; i++) {
            listaFaktur.add(new Faktura(dataWystawienia, nabywca));
        }


        do{
            Menu.wyswietlanieMenu(listaFaktur);
            Faktura.setLiczbaFaktur(listaFaktur.size());
        }while (true);

    }
}
