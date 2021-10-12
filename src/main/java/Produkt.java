import DataBase.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Produkt {

    //TODO ZDECYDUJ SIE CZY CEN PRODUKTU PRUTTO CZY NETTO

    public static int iloscvProduktow = 0;

    private String  nazwaProduktu;
    private float cenaProduktuBrutto;

    public Produkt(String nazwaProduktu, float cenaProduktuNetto) {
        this.nazwaProduktu = nazwaProduktu;
        this.cenaProduktuBrutto = cenaProduktuNetto;
        iloscvProduktow++;
    }

    public static int getIloscvProduktow() {
        return iloscvProduktow;
    }

    public static void setIloscvProduktow(int iloscvProduktow) {
        Produkt.iloscvProduktow = iloscvProduktow;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public float getCenaProduktuBrutto() {
        return cenaProduktuBrutto;
    }

    public void setCenaProduktuBrutto(int cenaProduktuNetto) {
        this.cenaProduktuBrutto = cenaProduktuNetto;
    }

    public static void dodawanieProduktowDoFakturyv2(Faktura faktura){

        Scanner scanner = new Scanner(System.in);
        boolean warunek = true;

        System.out.println("DODAWANIE PRODUKTOW DO FAKTURY");

        do {
            System.out.println("1.Dodaj produkt z listy");
            System.out.println("2.Dodaj nowy produkt");
            System.out.println("3.Wyjdz");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    Produkt.wyswietlanieWszystkichProduktow();
                    break;
                case 2:
                    Produkt.dodanieNowegoProduktu();
                    break;
                case 3:
                    warunek = false;
                    break;
            }

        }while(warunek);
    }

    public static void wyswietlanieWszystkichProduktow(){
        //POBIERANIE KONTRACHENTOW Z BAZY DANYCH

        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty;");

            result.next();
            String produkt_name = result.getString("produkt_name");
            float cena_brutto = result.getFloat("cena_brutto");

            System.out.println(cena_brutto +"."+ produkt_name);


            while(result.next()){
                cena_brutto = result.getInt("cena_brutto");
                produkt_name = result.getString("produkt_name");
                System.out.println(cena_brutto +"."+ produkt_name);
//                System.out.println( produkt_name);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Produkt dodanieNowegoProduktu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dodoanie nowego produktu:");
        System.out.print("Nazwa: ");
        String nazwaProduktu = scanner.next();
        System.out.print("Cena: ");
        float cenaBruutProduktu = scanner.nextFloat();

        Produkt produkt = new Produkt(nazwaProduktu,cenaBruutProduktu);
        produkt.dodanieProduktuDoBazyDanych(produkt);

        return produkt;

    }


    public void dodanieProduktuDoBazyDanych(Produkt produkt){
        QueryExecutor.executeQuery("INSERT INTO produkty (produkt_name,cena_brutto) VALUES('"+produkt.getNazwaProduktu()+"','"+produkt.getCenaProduktuBrutto()+"');");

    }




}
