package util.bd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilitzarRutesBD {

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		// Creació del gestionador
		GestionarRutesBD gRutes = new GestionarRutesBD();

		// Inserció d'una nova Ruta
		String[] noms = { "Les Useres", "Les Torrocelles", "Lloma Bernat", "Xodos (Molí)", "El Marinet", "Sant Joan" };
		double[] latituds = { 40.158126, 40.196046, 40.219210, 40.248003, 40.250977, 40.251221 };
		double[] longituds = { -0.166962, -0.227611, -0.263560, -0.296690, -0.316947, -0.354052 };

		Ruta r = new Ruta();
		r.setNom("Pelegrins de Les Useres");
		r.setDesnivell(896);
		r.setDesnivellAcumulat(1738);
		for (int i = 0; i < 6; i++) {
			r.addPunt(noms[i], latituds[i], longituds[i]);
		}

		gRutes.inserir(r);

		// Llistat de totes les rutes
		ArrayList<Ruta> llista = gRutes.llistat();
		for (int i = 0; i < llista.size(); i++) {
			llista.get(i).mostraRuta();
		}

		// Buscar una ruta determinada
		//gRutes.buscar(1).mostraRuta();
		gRutes.esborrar(1);

		// gRutes.close();
	}
}