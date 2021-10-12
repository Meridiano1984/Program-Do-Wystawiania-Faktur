import DataBase.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Kontrachent {

    static  private int liczbaKontrachentow=0;
    private String nazwaKontrachenta;
    private int NIP;

    public Kontrachent(String nazwaKontrachenta, int NIP) {
        this.nazwaKontrachenta = nazwaKontrachenta;
        this.NIP = NIP;
        liczbaKontrachentow++;
    }


    public static int getLiczbaKontrachentow() {
        return liczbaKontrachentow;
    }

    public static void setLiczbaKontrachentow(int liczbaKontrachentow) {
        Kontrachent.liczbaKontrachentow = liczbaKontrachentow;
    }

    public String getNazwaKontrachenta() {
        return nazwaKontrachenta;
    }

    public void setNazwaKontrachenta(String nazwaKontrachenta) {
        this.nazwaKontrachenta = nazwaKontrachenta;
    }

    public int getNIP() {
        return NIP;
    }

    public void setNIP(int NIP) {
        this.NIP = NIP;
    }

    public static void wyswietlanieWszystkichKontrachentow(){
        //POBIERANIE KONTRACHENTOW Z BAZY DANYCH

        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM kontrachenci;");

            result.next();
            int kontrachent_id = result.getInt("kontrachent_id");
            String kontrachent_name = result.getString("kontrachent_name");

            System.out.println(kontrachent_id +"."+ kontrachent_name);


            while(result.next()){
                kontrachent_id = result.getInt("kontrachent_id");
                kontrachent_name = result.getString("kontrachent_name");
                System.out.println(kontrachent_id +"."+ kontrachent_name);
//                System.out.println( kontrachent_name);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void dodanieNowegoKontrachenta(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("2.Dane nowego Kontrachenta ");
        System.out.print("      Nazwa Kontrachenta: ");
        String nazwaKontrachenta = scanner.next();
        System.out.print("      NIP: ");
        int nip = scanner.nextInt();

        Kontrachent kontrachent = new Kontrachent(nazwaKontrachenta,nip);
        kontrachent.dodanieKontrachentaDoBazyDanych(kontrachent);



    }


    public void dodanieKontrachentaDoBazyDanych(Kontrachent kontrachent){

        QueryExecutor.executeQuery("INSERT INTO kontrachenci (kontrachent_name,nip) VALUES('"+kontrachent.getNazwaKontrachenta()+"','"+kontrachent.getNIP()+"');");

    }
}


