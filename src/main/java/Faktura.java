import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Faktura {

    private static int liczbaFaktur=0;

    private LocalDate   dataWystawienia;
    private Kontrachent kontrachent;
    private String      nrFaktury;
    private double      cenaBrutto;
    private LinkedList<Produkt> produkty = new LinkedList<>();


    public Faktura(LocalDate dataWystawienia, Kontrachent kontrachent) {
        this.dataWystawienia = dataWystawienia;
        this.cenaBrutto =0;
        this.nrFaktury = nadawanieNumeruFakturyWKonstruktorze();
        this.kontrachent = kontrachent;
        liczbaFaktur++;
    }

    public String getNrFaktury() {
        return nrFaktury;
    }

    public void setNrFaktury(String nrFaktury) {
        this.nrFaktury = nrFaktury;
    }

    public Kontrachent getKontrachent() {
        return kontrachent;
    }

    public void setKontrachent(Kontrachent kontrachent) {
        this.kontrachent = kontrachent;
    }

    public static int getLiczbaFaktur() {
        return liczbaFaktur;
    }

    public static void setLiczbaFaktur(int liczbaFaktur) {
        Faktura.liczbaFaktur = liczbaFaktur;
    }

    public LocalDate getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(LocalDate dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }


    public double getCenaBrutto() {
        return cenaBrutto;
    }

    public void setCenaBrutto(double cenaBrutto) {
        this.cenaBrutto = cenaBrutto;
    }

    public LinkedList<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(LinkedList<Produkt> produkty) {
        this.produkty = produkty;
    }

    public static void wyswietlanieFaktur (ArrayList<Faktura> listaFaktur){
        for (Faktura faktura: listaFaktur) {

            System.out.println("Faktura nr:" + listaFaktur.indexOf(faktura));
            System.out.println("Data wystawienia: " + faktura.getDataWystawienia());
            System.out.println("Nabywca: " + faktura.getKontrachent().getNazwaKontrachenta() + " NIP: " + faktura.getKontrachent().getNIP() + " nr Faktury: " + faktura.getNrFaktury());
            System.out.println("CenaBrutto: " + faktura.getCenaBrutto());
            System.out.println();

            int nrProduktu = 1;
            System.out.println("PRODUKTY");
            for (Produkt produkt: faktura.getProdukty()){
                System.out.println(nrProduktu + ".Nazwa: " + produkt.getNazwaProduktu() + " cena brutto: " + produkt.getCenaProduktuNetto());
                nrProduktu++;
            }

            System.out.println();
            System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
            System.out.println();



        }
    }

    public static void wyswietlanieKonkretnejFaktury(ArrayList<Faktura> listaFaktur){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj indeks faktury ktora chcesz wyswietlic: ");
        int nrFaktury = scanner.nextInt();

        Faktura faktura = listaFaktur.get(nrFaktury-1);

        System.out.println("Faktura nr: " + nrFaktury);
        System.out.println("Data wystawienia :" + faktura.getDataWystawienia());
        System.out.println("Nabywca: " + faktura.getKontrachent().getNazwaKontrachenta() + " NIP: " + faktura.getKontrachent().getNIP());
        System.out.println();
        System.out.println("PRODUKTY");
        int nrProduktu = 1;

        for (Produkt produkt: faktura.getProdukty()){
            System.out.println(nrProduktu + ".Nazwa: " + produkt.getNazwaProduktu() + " cena brutto: " + produkt.getCenaProduktuNetto());
            nrProduktu++;
        }

        System.out.println();
        System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
        System.out.println();


    }

    public static void dodanieNowejFaktury (ArrayList<Faktura> listaFaktur){

        Scanner scanner = new Scanner(System.in);

        System.out.println("        Dodawanie nowej faktury");
        System.out.println("1.Data wystawienia ");
        System.out.print("      Rok:");
        int rok = scanner.nextInt();
        System.out.print("      Miesiąc:");
        int miesiac = scanner.nextInt();
        System.out.print("      Dzien:");
        int dzien = scanner.nextInt();

        LocalDate dataWystawiania = LocalDate.of(rok,miesiac,dzien);

        Kontrachent kontrachent;

        System.out.println("2.Dane Kontrachenta ");
        System.out.print("      Nazwa Kontrachenta: ");
        String nazwaKontrachenta = scanner.next();
        System.out.print("      NIP: ");
        int nip = scanner.nextInt();

        kontrachent = new Kontrachent(nazwaKontrachenta,nip);

        Faktura faktura = new Faktura(dataWystawiania,kontrachent);
        listaFaktur.add(faktura);

        Faktura.dodawainieProduktowDoNowejFaktury(faktura);

        System.out.println();
        System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
        System.out.println();


    }

    public static void dodawainieProduktowDoNowejFaktury(Faktura faktura){
        System.out.println("                                                    Dodawanie Produktów");
        System.out.println("Faktura nr: " + liczbaFaktur + " Wystawiona: " + faktura.getDataWystawienia() + " nabywca: " + faktura.getKontrachent().getNazwaKontrachenta() + " NIP: " + faktura.getKontrachent().getNIP());

        System.out.println();


        LinkedList<Produkt> produkty = new LinkedList<>();

        boolean warunek = false;
        int nrProduktu = 0;

        do {
            System.out.println("Produk nr: " + nrProduktu );
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nazwa Produktu: ");
            String nazwaProduktu = scanner.next();
            System.out.print("Cena Produktu brutto: ");
            float cenaBrutto = scanner.nextFloat();
            faktura.setCenaBrutto(faktura.getCenaBrutto()+cenaBrutto);

            produkty.add(new Produkt(nazwaProduktu,cenaBrutto));

            System.out.println();
            System.out.println();
            System.out.println("1.Dodaj kolejny produkt");
            System.out.println("2.Wystaw Fakture");

            int wybor = scanner.nextInt();

            if (wybor == 1) {
                warunek = true;
                nrProduktu++;
            } else if (wybor != 1) {
                warunek = false;
            }

        } while(warunek);

        faktura.setProdukty(produkty);
    }

    private String nadawanieNumeruFakturyWKonstruktorze(){

        String nowyNrFaktury;

        LocalDate dzisiejszaData = LocalDate.now();

        nowyNrFaktury = ("nr faktury: " + getLiczbaFaktur() + "/" + dzisiejszaData.getMonthValue() + "/" + dzisiejszaData.getYear() );

        return nowyNrFaktury;
    }

    public static void dodawnaieNowejFakturyv2(){

        //POBIERANIE DATY
        Scanner scanner = new Scanner(System.in);

        System.out.println("        Dodawanie nowej faktury");
        System.out.println("1.Data wystawienia ");
        System.out.print("      Rok:");
        int rok = scanner.nextInt();
        System.out.print("      Miesiąc:");
        int miesiac = scanner.nextInt();
        System.out.print("      Dzien:");
        int dzien = scanner.nextInt();

        LocalDate dataWystawiania = LocalDate.of(rok,miesiac,dzien);

        //WYBRANIE/DODANIE KONTRACHENTA

        System.out.println("\n\n1.Wyswietl wszystkich kontrachentow\n2.Dodaj nowego");

        int wybor = scanner.nextInt();

        switch (wybor){
            case 1:
                Kontrachent.wyswietlanieWszystkichKontrachentow();
                break;
            case 2:
                Kontrachent.dodanieNowegoKontrachenta();
                break;

        }

//        boolean warunek = false;
//
//        do{
//
//        }while(warunek);

    }

}
