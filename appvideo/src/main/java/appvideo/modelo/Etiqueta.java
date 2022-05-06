package appvideo.modelo;

import com.google.gson.GsonBuilder;

public class Etiqueta {

	private int codigo;

	// ATRIBUTOS:
	private String nombre;

	// CONSTRUCTOR:
	public Etiqueta(String nombre) {
		this.nombre = nombre.toUpperCase();
	}

	// METODOS:
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}