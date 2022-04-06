package appvideo.controlador;

import java.util.Date;
import java.util.List;

import appvideo.modelo.CatalogoUsuarios;
import appvideo.modelo.CatalogoVideos;
import appvideo.modelo.Etiqueta;
import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Usuario;
import appvideo.modelo.Video;
import appvideo.persistencia.DAOException;
import appvideo.persistencia.FactoriaDAO;
import appvideo.persistencia.IAdaptadorEtiquetaDAO;
import appvideo.persistencia.IAdaptadorListaReproduccionDAO;
import appvideo.persistencia.IAdaptadorUsuarioDAO;
import appvideo.persistencia.IAdaptadorVideoDAO;

public class ControladorAppVideo {

	// ATRIBUTOS:
	private static ControladorAppVideo unicaInstancia;

	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorEtiquetaDAO adaptadorEtiqueta;
	private IAdaptadorListaReproduccionDAO adaptadorListaReproduccion;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoVideos catalogoVideos;

	private Usuario usuarioActual;

	// CONSTRUCTOR:
	private ControladorAppVideo() {
		inicializarAdaptadores(); // debe ser la primera linea para evitar error de sincronización
		inicializarCatalogos();
	}

	// METODOS:
	public static ControladorAppVideo getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppVideo();
		return unicaInstancia;
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorEtiqueta = factoria.getEtiquetaDAO();
		adaptadorVideo = factoria.getVideoDAO();
		adaptadorListaReproduccion = factoria.getListaReproduccionDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoVideos = CatalogoVideos.getUnicaInstancia();
	}

	public boolean registrarusuario(String nombre, String apellidos, Date fNacimiento, String email, String username,
			String password) {

		// Comprobamos si ya hay un usuario con este 'username', en ese caso no se
		// registra
		if (catalogoUsuarios.getUsuario(username) != null) {
			System.out.println("* Username '" + username + "' no está disponible.");
			return false;
		}

		// Creamos el usuario, lo registramos y lo añadimos al catalogo
		Usuario usuario = new Usuario(nombre, apellidos, fNacimiento, email, username, password, false);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);

		System.out.println("* Usuario '" + username + "' registrado correctamente.");
		return true;
	}

	public boolean login(String username, String password) {

		// Extraemos el usuario del catalogo si existe, null en caso contrario
		Usuario usuario = catalogoUsuarios.getUsuario(username);

		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			System.out.println("* El usuario '" + username + "' se ha logeado en el sistema.");
			return true;
		}

		System.out.println("* El usuario '" + username + "' se ha intentado logear en el sistema.");
		return false;
	}

	public void logout() {
		adaptadorUsuario.modificarUsuario(usuarioActual);
		this.usuarioActual = null;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public ListaReproduccion crearListaReproduccion(String nombre) {
		ListaReproduccion lr = usuarioActual.crearListaReproduccion(nombre.toUpperCase());
		adaptadorListaReproduccion.registrarListaReproduccion(lr);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return lr;
	}

	public boolean eliminarListaReproduccion(ListaReproduccion listaReproduccion) {

		if (usuarioActual.removeListaReproduccion(listaReproduccion)) {
			adaptadorListaReproduccion.borrarListaReproduccion(listaReproduccion);
			adaptadorUsuario.modificarUsuario(usuarioActual);
			return true;
		}

		return false;
	}

	public void addVideoToList(ListaReproduccion listaReproduccion, Video video) {
		usuarioActual.addVideoToList(listaReproduccion, video);
		adaptadorListaReproduccion.modificarListaReproduccion(listaReproduccion);
	}

	public List<ListaReproduccion> getAllListasReproduccion() {
		return usuarioActual.getListasReproduccion();
	}

	public ListaReproduccion getListaReproduccion(String nombre) {
		return usuarioActual.getListaReproduccion(nombre);
	}

	public List<Video> getVideoRecientes() {
		return usuarioActual.getVideosRecientes();
	}

	public void addVideoReciente(Video video) {
		usuarioActual.addVideoReciente(video);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}

	public List<Video> getAllVideos() {
		return catalogoVideos.getVideos();
	}

	public Video getVideo(String titulo) {
		return catalogoVideos.getVideo(titulo);
	}

	public Etiqueta getEtiqueta(String nombre) {

		for (Etiqueta etiqueta : adaptadorEtiqueta.recuperarTodasEtiquetas())
			if (etiqueta.getNombre().equals(nombre))
				return etiqueta;

		return null;
	}

	public List<Etiqueta> getAllEtiquetas() {
		return adaptadorEtiqueta.recuperarTodasEtiquetas();
	}

	public void registrarVideo(Video v) {

		Etiqueta e1 = null;
		for (Etiqueta e : v.getEtiquetas()) {
			e1 = getEtiqueta(e.getNombre());
			if (e1 != null)
				e.setCodigo(e1.getCodigo());
		}

		catalogoVideos.addVideo(v);
		adaptadorVideo.registrarVideo(v);
	}
}