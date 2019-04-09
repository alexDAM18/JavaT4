package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class provaSQLiteCreacioTaula {

    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String url = "jdbc:sqlite:proveta.sqlite";
       
        //Class.forName("org.sqlite.JDBC");

        Connection con = DriverManager.getConnection(url);
        
        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO T1 VALUES('HOLA')");
        st.close();
        
        con.close();
    }
}