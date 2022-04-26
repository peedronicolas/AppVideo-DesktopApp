package appvideo.modelo;

import java.util.LinkedList;

import com.google.gson.GsonBuilder;

public class Video {

	private int codigo;

	// ATRIBUTOS:
	private String titulo;
	private String URL;
	private int numReproducciones;

	private LinkedList<Etiqueta> etiquetas;

	// CONSTRUCTOR:
	public Video(String titulo, String URL) {
		this.titulo = titulo;
		this.URL = URL;
		this.numReproducciones = 0;
		this.etiquetas = new LinkedList<>();
	}

	public Video(String titulo, String URL, int numReproducciones) {
		this.titulo = titulo;
		this.URL = URL;
		this.numReproducciones = numReproducciones;
		this.etiquetas = new LinkedList<>();
	}

	// METODOS:
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getURL() {
		return URL;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public void incrementarNumReproducciones() {
		numReproducciones++;
	}

	public LinkedList<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public boolean addEtiqueta(Etiqueta etiqueta) {

		if (etiquetas.contains(etiqueta))
			return false;

		etiquetas.add(etiqueta);
		return true;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}