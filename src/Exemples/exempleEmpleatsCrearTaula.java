package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class exempleEmpleatsCrearTaula {

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        String sentSQL = null;

        try {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:Empleats.sqlite";
            con = DriverManager.getConnection(url);

            st = con.createStatement();

            sentSQL = "CREATE TABLE EMPLEAT(" +
                    "num INTEGER CONSTRAINT cp_emp PRIMARY KEY, " +
                    "nom TEXT, " +
                    "depart INTEGER, " +
                    "edat INTEGER, " +
                    "sou REAL " +
                ")";

            st.executeUpdate(sentSQL);
            st.close();

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