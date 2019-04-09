package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class consultaMySQL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String url = "jdbc:mysql://89.36.214.106:3306/factura";
        String usuari = "factura";
        String password = "factura";

        Connection con = DriverManager.getConnection(url, usuari, password);
        
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM poble");
        while (rs.next()){
            System.out.print(rs.getInt(1) + "\t");
            System.out.println(rs.getString(2));
        }
        st.close();
        
        con.close();
    }
}