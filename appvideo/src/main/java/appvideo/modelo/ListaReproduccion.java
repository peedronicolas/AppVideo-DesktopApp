package appvideo.modelo;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class ListaReproduccion {

	private int codigo;

	// ATRIBUTOS:
	private String nombre;
	private ArrayList<Video> videos;

	// CONSTRUCTOR:
	public ListaReproduccion(String nombre) {
		this.nombre = nombre;
		videos = new ArrayList<>();
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

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void addVideo(Video video) {
		videos.add(video);
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
