package util.bd;

import java.io.Serializable;

public class PuntGeo implements Serializable {

	private static final long serialVersionUID = (1L);

	private String nom;
	private Coordenades coord;

	public PuntGeo(String nom, Coordenades coord) {
		this.nom = nom;
		this.coord = coord;
	}

	public PuntGeo(String nom, double latitud, double longitud) {
		this.nom = nom;
		coord = new Coordenades(latitud, longitud);
	}

	public String getNom() {
		return nom;
	}

	public Coordenades getCoord() {
		return coord;
	}

	public double getLatidud() {
		return coord.getLatidud();
	}

	public double getLongitud() {
		return coord.getLongitud();
	}

}
