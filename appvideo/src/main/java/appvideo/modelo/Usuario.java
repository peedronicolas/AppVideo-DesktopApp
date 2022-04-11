package appvideo.modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.GsonBuilder;

public class Usuario {

	private static final int MAX_NUM_VIDEOS_RECIENTES = 5;

	private int codigo;

	// ATRIBUTOS:
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String username;
	private String password;
	private Boolean isPremium = false;

	private LinkedList<ListaReproduccion> listasReproduccion;
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

		this.listasReproduccion = new LinkedList<>();
		this.videosRecientes = new LinkedList<>();
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

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public List<ListaReproduccion> getListasReproduccion() {
		return listasReproduccion;
	}

	public ListaReproduccion getListaReproduccion(String nombre) {

		for (ListaReproduccion lr : listasReproduccion)
			if (lr.getNombre().equals(nombre))
				return lr;

		return null;
	}

	public ListaReproduccion crearListaReproduccion(String nombre) {

		for (ListaReproduccion lr : listasReproduccion)
			if (lr.getNombre().equals(nombre))
				return lr;

		ListaReproduccion l = new ListaReproduccion(nombre);
		listasReproduccion.add(l);
		return l;
	}

	public void addListaReproudccion(ListaReproduccion listaReproduccion) {
		listasReproduccion.add(listaReproduccion);
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

	public List<Video> getVideosRecientes() {
		return videosRecientes;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
