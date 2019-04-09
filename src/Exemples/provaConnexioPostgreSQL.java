package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class provaConnexioPostgreSQL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String url = "jdbc:postgresql://89.36.214.106:5432/geo_ad";
        String usuari = "geo_ad";
        String password = "geo_ad";

        //Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection(url, usuari, password);
        System.out.println("Connexió completada");
        con.close();
    }
}