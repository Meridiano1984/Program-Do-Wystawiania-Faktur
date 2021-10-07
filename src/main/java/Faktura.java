import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Faktura {

    private static int liczbaFaktur=0;

    private Date        dataWystawienia;
    private String      nabywca;
    private double      cenaBrutto;
    private LinkedList<Produkt> produkty = new LinkedList<>();


    public Faktura(Date dataWystawienia, String nabywca) {
        this.dataWystawienia = dataWystawienia;
        this.cenaBrutto =0;
        this.nabywca = nabywca;
        liczbaFaktur++;
    }

    public static int getLiczbaFaktur() {
        return liczbaFaktur;
    }

    public static void setLiczbaFaktur(int liczbaFaktur) {
        Faktura.liczbaFaktur = liczbaFaktur;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public String getNabywca() {
        return nabywca;
    }

    public void setNabywca(String nabywca) {
        this.nabywca = nabywca;
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
            System.out.println("Nabywca: " + faktura.getNabywca());
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
        System.out.println("Nabywca: " + faktura.getNabywca());
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
        System.out.print("    Rok:");
        int rok = scanner.nextInt();
        System.out.print("    Miesiąc:");
        int miesiac = scanner.nextInt();
        System.out.print("    Dzien:");
        int dzien = scanner.nextInt();

        Date dataWystawienia = new Date();
        dataWystawienia.setYear(rok);
        dataWystawienia.setMonth(miesiac-1);
        dataWystawienia.setDate(dzien-1);

        System.out.print("2.Nabywca: ");
        String nabywca = scanner.next();

        Faktura faktura = new Faktura(dataWystawienia,nabywca);
        listaFaktur.add(faktura);

        Faktura.dodawainieProduktowDoNowejFaktury(faktura);

        System.out.println();
        System.out.println("CENA BRUTTO WYNOSI: " + faktura.getCenaBrutto());
        System.out.println();


    }

    public static void dodawainieProduktowDoNowejFaktury(Faktura faktura){
        System.out.println("                                                    Dodawanie Produktów");
        System.out.println("Faktura nr: " + liczbaFaktur + " Wystawiona: " + faktura.getDataWystawienia() + " nabywca: " + faktura.getNabywca());

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

}
