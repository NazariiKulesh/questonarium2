package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingelton {
    private static String user = "postgres";
    private static String password = "postgres";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;
    public static Connection getConnection(){
        try{
        if(connection==null || connection.isClosed()){
            connection = DriverManager.getConnection(url, user, password);
        }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        return connection;
    }
}
