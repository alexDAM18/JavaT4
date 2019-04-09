package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class consultaPostgreSQL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String url = "jdbc:oracle:thin:@94.177.240.173:1521:orcl";
        String usuari = "scott";
        String password = "tiger";

        Connection con = DriverManager.getConnection(url, usuari, password);
        
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM DEPT");
        while (rs.next()){
            System.out.print(rs.getInt(1) + "\t");
            System.out.println(rs.getString(2));
        }
        st.close();
   
        con.close();
    }
}