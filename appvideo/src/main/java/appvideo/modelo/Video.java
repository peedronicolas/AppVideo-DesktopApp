package appvideo.modelo;

import java.util.LinkedList;

import com.google.gson.GsonBuilder;

public class Video {

	// ATRIBUTOS:
	private String titulo;
	private String URL;
	private int numReproducciones;

	private LinkedList<Etiqueta> etiquetas;

	// CONSTRUCTOR:
	public Video(String titulo, String URL, int numReproducciones) {
		this.titulo = titulo;
		this.URL = URL;
		this.numReproducciones = numReproducciones;

		this.etiquetas = new LinkedList<>();
	}

	// METODOS:
	public String getTitulo() {
		return titulo;
	}

	public String getURL() {
		return URL;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public LinkedList<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void addEtiqueta(String nombreEtiqueta) {
		etiquetas.add(new Etiqueta(nombreEtiqueta.toUpperCase()));
	}

	public void incrementarNumReproducciones() {
		numReproducciones++;
	}

	public String toJson() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
