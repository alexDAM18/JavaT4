package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class exempleEmpleatsModificarTaula {

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        String sentSQL = null;

        try {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:Empleats.sqlite";
            con = DriverManager.getConnection(url);

            st = con.createStatement();

            sentSQL = "UPDATE EMPLEAT SET sou = sou * 1.05";
            st.executeUpdate(sentSQL);

            sentSQL = "UPDATE EMPLEAT SET depart=20 WHERE num = 3";
            st.executeUpdate(sentSQL);

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");

        } finally {
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