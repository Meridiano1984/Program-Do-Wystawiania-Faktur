package DataBase;

import static DataBase.QueryExecutor.executeQuery;

public class DatabaseInitialization {

    public DatabaseInitialization() {
    }

    public void tworzenieBazydanych (){

        String Query;

        try {
             Query = """
                    CREATE TABLE faktury (\s
                     faktura_id INT PRIMARY KEY,
                     data_wystawienia DATE,
                     cenaBrutto FLOAT(2)
                     );
                            """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("TABELA FAKTURY JUZ ISTNIEJE");
        }


        try {
            Query = """
                     CREATE TABLE kontrachenci (\s
                     kontrachent_id INT PRIMARY KEY,
                     kontrachent_name VARCHAR(40),
                     nip VARCHAR(15)
                     );
                    """;

            executeQuery(Query);
        }catch (RuntimeException e){
            System.out.println("Tabela KONTRACHENCI JUZ ISTNIEJE");
        }

        try {
            Query = """ 
                     CREATE TABLE produkty ( 
                     produkt_id INT PRIMARY KEY,
                     produkt_name VARCHAR(40),
                     cena_brutto FLOAT(2)
                     );
                    """;

            executeQuery(Query);
        } catch (RuntimeException e){
            System.out.println("Tabela produkty juz istnieje");
        }


        //TODO ZAPROJEKTOWAC BAZEDANYCH TAK ZE KAZDA FAKTURA MA JEDNEGO KONTRACHENTA (faktura i kontrachent ot primary key) I WIELE PRODUKTOW COS JAKBY TABLICA PRODUKTOW


            Query = """
                    CREATE TABLE wystawione_faktury ( 
                     faktura_id INT,
                     kontrachent_id INT,
                     produkt_id INT,
                     PRIMARY KEY(faktura_id, produkt_id),
                     FOREIGN KEY(faktura_id) REFERENCES faktury(faktura_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(kontrachent_id) REFERENCES kontrachenci(kontrachent_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     FOREIGN KEY(produkt_id) REFERENCES produkty(produkt_id) ON DELETE CASCADE ON UPDATE CASCADE
                     );
                    """;
            executeQuery(Query);


    }


    public void dodaniePrzykładowychdanych(){


            executeQuery("INSERT INTO kontrachenci VALUES(1,'Biedronka',1234567890);");
            executeQuery("INSERT INTO kontrachenci VALUES(2,'Autocentrum',2134567890);");
            executeQuery("INSERT INTO kontrachenci VALUES(3,'PGE-Belchatow',3354618291);");
            executeQuery("INSERT INTO kontrachenci VALUES(4,'Kamilex',3466789008);");
            executeQuery("INSERT INTO kontrachenci VALUES(5,'II LO Radomsko',1111144444);");
            executeQuery("INSERT INTO kontrachenci VALUES(6,'Fitnes Centrum',3334445559);");
            executeQuery("INSERT INTO kontrachenci VALUES(7,'Riff',1231231234);");

            System.out.println("Dodano juz kontrachentow");



            executeQuery("INSERT INTO produkty VALUES(1,'Mleko',2.50);");
            executeQuery("INSERT INTO produkty VALUES(2,'Laptop',2500)");
            executeQuery("INSERT INTO produkty VALUES(3,'Jeansy',250);");
            executeQuery("INSERT INTO produkty VALUES(4,'Windows',4000);");
            executeQuery("INSERT INTO produkty VALUES(5,'Bulka',0.39);");
            executeQuery("INSERT INTO produkty VALUES(6,'Margherita',12.50);");

            System.out.println("Dodano produkty");



            executeQuery("INSERT INTO faktury VALUES(1,'2021-12-23',0);");
            executeQuery("INSERT INTO faktury VALUES(2,'2022-01-01',0);");


            System.out.println("Dodano faktury");



            executeQuery("INSERT INTO wystawione_faktury VALUES(1,1,1);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(1,1,2);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(1,1,5);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(1,1,3);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(2,3,4);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(2,3,1);");
            executeQuery("INSERT INTO wystawione_faktury VALUES(2,3,2);");

            System.out.println("Dodano wystawione Faktury");


    }


    public void usuwanieTabel(){


            executeQuery("DROP TABLE wystawione_faktury;");
            executeQuery("DROP TABLE produkty;");
            executeQuery("DROP TABLE kontrachenci;");
            executeQuery("DROP TABLE faktury;");

    }
}
