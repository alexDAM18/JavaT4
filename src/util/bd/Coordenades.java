package util.bd;

import java.io.Serializable;

public class Coordenades implements Serializable {

	private static final long serialVersionUID = (1L);

	private double latidud;
	private double longitud;

	public Coordenades(double latitud, double longitud) {

		this.latidud = latitud;
		this.longitud = longitud;
	}

	public double getLatidud() {
		return latidud;
	}

	public void setLatidud(double latidud) {
		this.latidud = latidud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

}
