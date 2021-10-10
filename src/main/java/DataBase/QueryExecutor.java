package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {

    static public ResultSet executeSelect(String selectQuery){
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    static public void executeQuery(String query){

        try{
            Connection connection =DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }


    private void tworzenieBazydanych (){

        String Query = """
                CREATE TABLE faktury (\s
                 faktura_id INT PRIMARY KEY,
                 cenaBrutto INT,
                 );
                        """;

        executeQuery(Query);

        Query = """
                 CREATE TABLE kontrachenci (\s
                 kontrachent_id INT PRIMARY KEY,
                 kontrachent_name VARCHAR(40),
                 nip INT,
                 );
                """;

        executeQuery(Query);

        Query = """ 
                 CREATE TABLE produkty ( 
                 produkt_id INT PRIMARY KEY,
                 produkt_name VARCHAR(40),
                 cena_brutto FLOAT(2),
                 );
                """;

        executeQuery(Query);

        Query = """
                CREATE TABLE wystawione_faktury ( 
                 faktura_id INT,
                 kontrachent_id INT,
                 produkt_id INT,
                 PRIMARY KEY(faktura_id, kontrachent_id),
                 FOREIGN KEY(faktura_id) REFERENCES faktury(faktura_id) ON DELETE CASCADE,
                 FOREIGN KEY(kontrachent_id) REFERENCES kontrachenci(kontrachent_id) ON DELETE CASCADE
                 );
                """;

    }
}