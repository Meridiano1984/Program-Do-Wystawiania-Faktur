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
                 faktura INT PRIMARY KEY,
                 kontrachent VARCHAR(100),
                 cenaBrutto VARCHAR(40),
                 );
                        """;



        try{
            Connection connection =DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.execute(Query);

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}