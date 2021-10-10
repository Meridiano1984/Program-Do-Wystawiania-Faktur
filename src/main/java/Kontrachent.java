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
}
