public interface ProduktInterface {

    public void dodanieProduktuDoTabeliWystawinychFaktur(Faktura faktura,Produkt produkt,int ilosc);

    public void sprawdzenieCzyProduktZanjdujeSieJuzWBazieDanych(Produkt produkt, Faktura faktura,int ilosc);
}
