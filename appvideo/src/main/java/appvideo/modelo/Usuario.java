package appvideo.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;

public class Usuario {

	private static final int MAX_NUM_VIDEOS_RECIENTES = 5;

	// ATRIBUTOS:
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String username;
	private String password;
	private Boolean isPremium = false;

	private HashSet<ListaReproduccion> listasReproduccion;
	private LinkedList<Video> videosRecientes;

	// CONSTRUCTOR:
	public Usuario(String nombre, String apellidos, Date fNacimiento, String email, String username, String password,
			Boolean isPremium) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fNacimiento = fNacimiento;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isPremium = isPremium;

		this.listasReproduccion = new HashSet<ListaReproduccion>();
		this.videosRecientes = new LinkedList<>();
	}

	// METODOS:
	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Date getfNacimiento() {
		return fNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean isPremium() {
		return isPremium;
	}

	public HashSet<ListaReproduccion> getListasReproduccion() {
		return listasReproduccion;
	}

	public ListaReproduccion createAddListaReproduccion(String nombre) {

		// Comprobamos si ya existe la lista de reproduccion, en ese caso la devolvemos
		for (ListaReproduccion lr : listasReproduccion)
			if (lr.getNombre().equals(nombre))
				return lr;

		// Si no exixste la creamos, la añadimos a la lista y la devolvemos
		ListaReproduccion lr = new ListaReproduccion(nombre);
		listasReproduccion.add(lr);
		return lr;
	}

	public boolean removeListaReproduccion(ListaReproduccion lr) {
		if (listasReproduccion.contains(lr)) {
			listasReproduccion.remove(lr);
			return true;
		}

		return false;
	}

	public boolean addVideoToList(ListaReproduccion lr, Video v) {

		// Si el video esta ya en la lista no lo añadimos
		for (Video video : lr.getVideos())
			if (video.equals(v))
				return false;

		// Si no lo añadimos a la lista
		lr.addVideo(v);
		return true;
	}

	public void addVideoReciente(Video video) {

		// Para evitar que hayan repetidos. En caso de que ya este en la lista lo
		// quitamos para añadirlo al principio
		if (videosRecientes.contains(video))
			videosRecientes.remove(video);

		// Añadimos el video al principio de la lista
		this.videosRecientes.add(0, video);

		// Al introducir un video nuevo eliminamos el ultimo de la lista
		if (videosRecientes.size() >= MAX_NUM_VIDEOS_RECIENTES + 1)
			videosRecientes.remove(MAX_NUM_VIDEOS_RECIENTES);
	}

	public String toJson() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
