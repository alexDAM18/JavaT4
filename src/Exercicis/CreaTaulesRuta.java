package Exercicis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreaTaulesRuta {
	   public static void main(String[] args) {
	  Connection con = null;
      Statement st = null;
      String sentSQL = null;
      String sentSQL2 = null;
      
      try {
          Class.forName("org.sqlite.JDBC");

          String url = "jdbc:sqlite:Rutes.sqlite";
          con = DriverManager.getConnection(url);

          st = con.createStatement();

          sentSQL = "CREATE TABLE RUTES(" +
                  "num_r INTEGER CONSTRAINT cp_emp PRIMARY KEY, " +
                  "nom_r TEXT, " +
                  "desn INTEGER, " +
                  "desn_ac INTEGER " +
              ")";
          
          sentSQL2 = "CREATE TABLE PUNTS(" +
                  "num_r INTEGER, " +
                  "num_p INTEGER, " +
                  "nom_p TEXT, " +
                  "latitud REAL, " +
                  "longitud REAL, "+ 
                  "PRIMARY KEY(num_r, num_p), "+ 
                  "CONSTRAINT ce_r_p FOREIGN KEY(num_r) REFERENCES RUTES(num_r) "+
              ")";
      
          st.executeUpdate(sentSQL);
          st.executeUpdate(sentSQL2);
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