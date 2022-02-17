package appvideo.modelo;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class ListaReproduccion {

	// ATRIBUTOS:
	private String nombre;
	private ArrayList<Video> videos;

	// CONSTRUCTOR:
	public ListaReproduccion(String nombre) {
		this.nombre = nombre;
		videos = new ArrayList<>();
	}

	// METODOS:
	public String getNombre() {
		return nombre;
	}

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void addVideo(Video video) {
		videos.add(video);
	}

	public String toJson() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
