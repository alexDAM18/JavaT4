package Exercicis;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassarRutesObjSQLite {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Connection con = null;
		Statement st = null;

		String sentSQL = null;
		ResultSet rs = null;
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream("Rutes.obj"));

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Rutes.sqlite";
			con = DriverManager.getConnection(url);

			st = con.createStatement();

			Ruta r;
			int num_r = 1;

			while (true) {
				r = (Ruta) obj.readObject();
				String nom = r.getNom();
				int des = r.getDesnivell();
				int des_acu = r.getDesnivellAcumulat();
				sentSQL = "INSERT INTO RUTES VALUES(" + num_r + ",'" + nom + "'," + des + "," + des_acu + ")";
				st.executeUpdate(sentSQL);
				for (int j = 0; j < r.length(); j++) {
					int punt_num = j + 1;
					String punt_nom = r.getPuntNom(j);
					double long_punt = r.getPuntLongitud(j);
					double lat_punt = r.getPuntLatitud(j);
					sentSQL = "INSERT INTO PUNTS VALUES(" + num_r + "," + punt_num + ",'" + punt_nom + "'," + long_punt
							+ "," + lat_punt + ")";
					st.executeUpdate(sentSQL);
				}
				num_r++;

			}

		} catch (EOFException ex) {
			System.exit(0);
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
