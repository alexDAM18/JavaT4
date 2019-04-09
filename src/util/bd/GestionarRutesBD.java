package util.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionarRutesBD {

	Connection con = null;

	public GestionarRutesBD() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:Rutes.sqlite";
		String sentSQL = null;
		String sentSQL2 = null;
		con = DriverManager.getConnection(url);

		Statement st = con.createStatement();

		sentSQL = "CREATE TABLE IF NOT EXISTS RUTES(" + "num_r INTEGER CONSTRAINT cp_emp PRIMARY KEY, " + "nom_r TEXT, "
				+ "desn INTEGER, " + "desn_ac INTEGER " + ")";

		sentSQL2 = "CREATE TABLE IF NOT EXISTS PUNTS(" + "num_r INTEGER, " + "num_p INTEGER, " + "nom_p TEXT, "
				+ "latitud REAL, " + "longitud REAL, " + "PRIMARY KEY(num_r, num_p), "
				+ "CONSTRAINT ce_r_p FOREIGN KEY(num_r) REFERENCES RUTES(num_r) " + ")";

		st.executeUpdate(sentSQL);
		st.executeUpdate(sentSQL2);
		st.close();
	}

	public void close() throws SQLException {
		con.close();
	}

	public void inserir(Ruta r) throws SQLException {
		Statement st1 = con.createStatement();
		String sentSQLid = null;
		sentSQLid = "SELECT MAX(num_r) FROM RUTES";

		ResultSet rs = st1.executeQuery(sentSQLid);
		int id = rs.getInt(1) + 1;

		String sentSQL;
		String nom_r = r.getNom();
		int desn = r.getDesnivell();
		int desn_ac = r.getDesnivellAcumulat();

		sentSQL = "INSERT INTO RUTES VALUES (" + id + ",'" + nom_r + "'," + desn + "," + desn_ac + ")";
		System.out.println(sentSQL);

		for (int i = 0; i < r.length(); i++) {
			int num_p = i + 1;
			String nom_p = r.getPuntNom(i);
			double lat = r.getPuntLatitud(i);
			double lon = r.getPuntLongitud(i);

			sentSQL = "INSERT INTO PUNTS VALUES(" + id + ", " + num_p + ",'" + nom_p + "'," + lat + "," + lon + ")";
			System.out.println(sentSQL);
		}

	}

	public Ruta buscar(int i) throws SQLException {
		Statement str = con.createStatement();
		String sqlRuta;
		String sqlPunts;
		sqlRuta = "SELECT * FROM RUTES WHERE num_r = " + i;
		ResultSet rs = str.executeQuery(sqlRuta);

		Ruta r = new Ruta();
		r.setNom(rs.getString(2));
		r.setDesnivell(rs.getInt(3));
		r.setDesnivellAcumulat(rs.getInt(4));

		Statement str2 = con.createStatement();
		sqlPunts = "SELECT * FROM PUNTS p JOIN RUTES r ON r.num_r = p.num_r WHERE r.num_r = " + i;
		ResultSet rs2 = str2.executeQuery(sqlPunts);

		while (rs2.next()) {
			r.addPunt(rs2.getString(3), rs2.getDouble(4), rs2.getDouble(5));
		}
		str.close();
		rs.close();
		str2.close();
		rs2.close();
		return r;

	}

	public ArrayList<Ruta> llistat() throws SQLException {
		ArrayList<Ruta> rutes = new ArrayList<>();

		Statement str = con.createStatement();
		String sqlRuta = "SELECT * FROM RUTES";
		ResultSet rs = str.executeQuery(sqlRuta);

		while (rs.next()) {
			Ruta r = new Ruta();
			r.setNom(rs.getString(2));
			r.setDesnivell(rs.getInt(3));
			r.setDesnivellAcumulat(rs.getInt(4));



			Statement str2 = con.createStatement();
			String sqlPunts = "SELECT * FROM PUNTS p WHERE p.num_r = " + rs.getInt(1);
			ResultSet rs2 = str2.executeQuery(sqlPunts);

			while (rs2.next()) {
				r.addPunt(rs2.getString(3), rs2.getDouble(4), rs2.getDouble(5));
			}
			rutes.add(r);
			str2.close();
			rs2.close();
		}

		str.close();
		rs.close();

		return rutes;

	}

	public void esborrar(int i) throws SQLException {

		Statement str = con.createStatement();
		String sqlRuta;
		String sqlPunts;
		sqlPunts = "DELETE FROM PUNTS WHERE num_r = " + i;
		sqlRuta = "DELETE FROM RUTES WHERE num_r = " + i;
		str.executeUpdate(sqlPunts);
		str.executeUpdate(sqlRuta);
		str.close();

	}

	public void guardar(Ruta r) throws SQLException {
		Statement st = con.createStatement();
		String sqlRuta;
		sqlRuta = "SELECT * FROM RUTES";
		ResultSet rs = st.executeQuery(sqlRuta);

		while (rs.next()) {
			if(!r.getNom().equals(rs.getString(2))) {
				inserir(r);
			}
		}
		rs.close();
		st.close();
	}

}
