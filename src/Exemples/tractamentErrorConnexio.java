package Exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tractamentErrorConnexio {

    public static void main(String[] args) {
        boolean connectat = false;
        Connection con = null;
        System.out.println("tractamentErrorConnexio()");

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:mysql://89.36.214.106:3306/geo";

            String usuari = "geo";
            String [] contrasenyes = {"geo0","geo1","geo"};
            
            for (int i = 0; !connectat && i < contrasenyes.length; i++) {
                try {
                    con = DriverManager.getConnection(url, usuari, contrasenyes[i]);
                    connectat = true;
                } catch (SQLException ex) {
                    if (!ex.getSQLState().equals("28000")) {
                        // NO és un error d'autenticació
                        throw ex;
                    }
                }
            }
            if (connectat)
                System.out.println("Connexió efectuada correctament");
            else
                System.out.println("Error en la contrasenya");
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("08001")) {
                System.out.println("S'ha detectat un problema de connexió. Reviseu els cables de xarxa i assegureu-vos que el SGBD està operatiu."
                                + " Si continua sense connectar, aviseu el servei tècnic");

            } else {
                System.out.println("S'ha produït un error inesperat. Truqueu al servei tècnic indicant el següent codi d'error SQL:"
                                + ex.getSQLState());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + "). Truqueu al servei tècnic");
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(tractamentErrorConnexio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}