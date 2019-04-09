package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class exempleEmpleatsConsultarTaula {

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        String sentenciaSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:Empleats.sqlite";
            con = DriverManager.getConnection(url);

            st = con.createStatement();

            sentenciaSQL = "SELECT * FROM EMPLEAT WHERE sou > 1100";
            rs = st.executeQuery(sentenciaSQL);

            System.out.println("Núm. \tNom \tDep \tEdat \tSou");
            System.out.println("-----------------------------------------");

            while (rs.next()) {
                System.out.print(rs.getInt(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getInt(3) + "\t");
                System.out.print(rs.getInt(4) + "\t");
                System.out.println(rs.getDouble(5));
            }

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");

        } finally {
            try {
                if(rs!=null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el ResultSet per alguna raó");
            }
            try {
                if (st != null && !st.isClosed()) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el Statement per alguna raó");
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el Connection per alguna raó");
            }
        }
    }
}