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
        Produkt produkt = null;

        System.out.println("DODAWANIE PRODUKTOW DO FAKTURY");

        do {
            System.out.println("1.Dodaj produkt z listy");
            System.out.println("2.Dodaj nowy produkt");
            System.out.println("3.Wyjdz");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    Produkt.wyswietlanieWszystkichProduktow();
                    System.out.print("Podaj indeks produktu któy chcesz dodoac do faktury");
                    int wybranyProdukt = scanner.nextInt();
                    Produkt.getProoduktPoZadanymIndex(wybranyProdukt);
                    produkt =Produkt.getProoduktPoZadanymIndex(wybranyProdukt);
                    produkt.dodanieProduktuDoTabeliWystawinychFaktur(faktura,produkt);
                    break;
                case 2:
                    produkt = Produkt.dodanieNowegoProduktu();
                    produkt.dodanieProduktuDoTabeliWystawinychFaktur(faktura,produkt);
                    break;
                case 3:
                    warunek = false;
                    break;
            }

        }while(warunek);
    }
//    awd

    public static void wyswietlanieWszystkichProduktow(){
        //POBIERANIE KONTRACHENTOW Z BAZY DANYCH

        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty;");

            result.next();
            int produkt_id = result.getInt("produkt_id");
            String produkt_name = result.getString("produkt_name");
            float cena_brutto = result.getFloat("cena_brutto");

            System.out.println(produkt_id +"."+ produkt_name + "  cena: " + cena_brutto);


            while(result.next()){
                produkt_id = result.getInt("produkt_id");
                cena_brutto = result.getInt("cena_brutto");
                produkt_name = result.getString("produkt_name");
                System.out.println(produkt_id +"."+ produkt_name + "  cena: " + cena_brutto);
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

    public void dodanieProduktuDoTabeliWystawinychFaktur(Faktura faktura,Produkt produkt){

        //TODO CZY JA NIE POWINIEM TWEGO ROZDZILIC NA OSBNE FUNKCJE? JAK POWINNY WYGLADAC FUNKCJE OBSLUGUJCE CRUDY?
        try {

            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM faktury WHERE nr_faktury='"+ faktura.getNrFaktury()+"';");
            System.out.println(faktura.getNrFaktury());
            System.out.println("SELECT * FROM faktury WHERE nr_faktury='"+ faktura.getNrFaktury()+"';");
            result.next();



            int faktura_id = result.getInt("faktura_id");

            result = QueryExecutor.executeSelect("SELECT * FROM produkty WHERE produkt_name='"+ produkt.getNazwaProduktu()+"';");
//            result.next();
            if(result.next()){
                System.out.println("Sa Dane ");
            } else {
                System.out.println("PUSTY");
            }

            int produkt_id = result.getInt("produkt_id");


            QueryExecutor.executeQuery("INSERT INTO wystawione_faktury VALUES (" + faktura_id + "," + produkt_id + ",1);");


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static Produkt getProoduktPoZadanymIndex(int index){

        Produkt produkt =null;
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM produkty WHERE produkt_id="+ index +";");
            result.next();

            String produkt_name = result.getString("produkt_name");
            Float cenaBrutto = result.getFloat("cena_brutto");

            produkt = new Produkt(produkt_name,cenaBrutto);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return produkt ;
    }



}
