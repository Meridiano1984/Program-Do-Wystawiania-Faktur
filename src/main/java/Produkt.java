public class Produkt {

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

    public float getCenaProduktuNetto() {
        return cenaProduktuBrutto;
    }

    public void setCenaProduktuNetto(int cenaProduktuNetto) {
        this.cenaProduktuBrutto = cenaProduktuNetto;
    }



}
