package appvideo.modelo;

import com.google.gson.GsonBuilder;

public class Etiqueta {

	// ATRIBUTOS:
	private String nombre;

	// CONSTRUCTOR:
	public Etiqueta(String nombre) {
		this.nombre = nombre;
	}

	// METODOS:
	public String getNombre() {
		return nombre;
	}

	public String toJson() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
