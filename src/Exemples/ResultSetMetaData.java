package Exemples;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetMetaData {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://89.36.214.106:5432/geo_ad", "geo_ad","geo_ad");

        DatabaseMetaData dbmd = con.getMetaData();
        System.out.println("Llistat de taules:");
        System.out.println(String.format("%-6s %-7s %-7s %-10s %-10s", "Número", "Catàleg", "Esquema", "Nom", "Tipus"));
        System.out.println("---------------------------------------------");
        ResultSet ll = dbmd.getTables(null, "public", null, null);
        int compt = 1;
        ArrayList<String> taules = new ArrayList<String>();
        while (ll.next()) {
            System.out.println(String.format("%-6d %-7s %-7s %-10s %-10s", (compt++), ll.getString(1), ll.getString(2),ll.getString(3), ll.getString(4)));
            taules.add(ll.getString(3));
        }
        System.out.println();
        System.out.println("Introdueix un número per veure el contingut de la taula (0 per acabar): ");
        BufferedReader ent = new BufferedReader(new InputStreamReader(System.in));
        int opcio = Integer.parseInt(ent.readLine());

        while (opcio != 0) {
            if (opcio < compt && opcio > 0) {
                ResultSet taula = dbmd.getTables(null, "public", taules.get(opcio - 1), null);
                if (taula.next()) {
                    if (taula.getString(4).equals("TABLE")) {
                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + taules.get(opcio - 1));
                        System.out.println("Contingut de la taula " + taules.get(opcio - 1));
                        System.out.println("----------------------------");

                        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                        for (int i = 1; i <= rsmd.getColumnCount(); i++)
                            System.out.print(String.format("%-20.20s",rsmd.getColumnName(i)));
                        System.out.println();
                        System.out.println("------------------------------------------");

                        while (rs.next()) {
                            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                                System.out.print(String.format("%-20.20s ",rs.getString(i)));
                            System.out.println();
                        }
                        rs.close();
                    }
                }
                taula.close();
            }
            System.out.println();
            System.out.println("Introdueix un número per veure el contingut de la taula (0 per acabar): ");
            opcio = Integer.parseInt(ent.readLine());
        }
        ll.close();
        con.close();
    }
}