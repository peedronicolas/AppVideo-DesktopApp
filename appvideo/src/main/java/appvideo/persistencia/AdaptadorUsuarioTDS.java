package appvideo.persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Usuario;
import appvideo.modelo.Video;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	// ATRIBUTOS:
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// CONSTRUCTOR:
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// METODOS:
	public static AdaptadorUsuarioTDS getUnicaInstancia() { // Patron sigleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eu = null;

		// Comprobamos si ya existe el usuario
		try {
			eu = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
		}

		if (eu != null)
			return;

		// Registrar primero los atributos que son objetos
		// Registrar las listas de reproduccion
		AdaptadorListaReproduccionTDS adaptadorLR = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		for (ListaReproduccion lr : usuario.getListasReproduccion())
			adaptadorLR.registrarListaReproduccion(lr);

		// Registrar los videos recientes
		AdaptadorVideoTDS adaptadorV = AdaptadorVideoTDS.getUnicaInstancia();
		for (Video v : usuario.getVideosRecientes())
			adaptadorV.registrarVideo(v);

		// Crear entidad
		eu = new Entidad();
		eu.setNombre("usuario");

		// Creamos las propiedades
		ArrayList<Propiedad> props = new ArrayList<>();
		props.add(new Propiedad("nombre", usuario.getNombre()));
		props.add(new Propiedad("apellidos", usuario.getApellidos()));
		props.add(new Propiedad("fNacimiento", dateFormat.format(usuario.getfNacimiento())));
		props.add(new Propiedad("email", usuario.getEmail()));
		props.add(new Propiedad("username", usuario.getUsername()));
		props.add(new Propiedad("password", usuario.getPassword()));
		props.add(new Propiedad("isPremium", usuario.getIsPremium().toString()));
		props.add(
				new Propiedad("listasReproduccion", obtenerCodigosListasReproduccion(usuario.getListasReproduccion())));
		props.add(new Propiedad("videosRecientes", obtenerCodigosVideos(usuario.getVideosRecientes())));
		eu.setPropiedades(props);

		// Registrar entidad
		eu = servPersistencia.registrarEntidad(eu);

		// Asignar identificador unico
		usuario.setCodigo(eu.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		Entidad eu;

		// Cuando se borra un usuario se borran las LR asociadas a el
		AdaptadorListaReproduccionTDS adaptadorLR = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		for (ListaReproduccion lr : usuario.getListasReproduccion())
			adaptadorLR.borrarListaReproduccion(lr);

		eu = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(eu);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eu = servPersistencia.recuperarEntidad(usuario.getCodigo());

		for (Propiedad prop : eu.getPropiedades()) {

			if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());

			} else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(usuario.getApellidos());

			} else if (prop.getNombre().equals("fNacimiento")) {
				prop.setValor(dateFormat.format(usuario.getfNacimiento()));

			} else if (prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());

			} else if (prop.getNombre().equals("username")) {
				prop.setValor(usuario.getUsername());

			} else if (prop.getNombre().equals("password")) {
				prop.setValor(usuario.getPassword());

			} else if (prop.getNombre().equals("isPremium")) {
				prop.setValor(usuario.getIsPremium().toString());

			} else if (prop.getNombre().equals("listasReproduccion")) {
				String listasReproduccion = obtenerCodigosListasReproduccion(usuario.getListasReproduccion());
				prop.setValor(listasReproduccion);

			} else if (prop.getNombre().equals("videosRecientes")) {
				String videosRecientes = obtenerCodigosVideos(usuario.getVideosRecientes());
				prop.setValor(videosRecientes);
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {

		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos recuperar entidad
		Entidad eu = servPersistencia.recuperarEntidad(codigo);

		String nombre = servPersistencia.recuperarPropiedadEntidad(eu, "nombre");
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eu, "apellidos");
		Date fNacimiento = null;
		try {
			fNacimiento = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eu, "fNacimiento"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String email = servPersistencia.recuperarPropiedadEntidad(eu, "email");
		String username = servPersistencia.recuperarPropiedadEntidad(eu, "username");
		String password = servPersistencia.recuperarPropiedadEntidad(eu, "password");
		boolean isPremium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eu, "isPremium"));
		Usuario user = new Usuario(nombre, apellidos, fNacimiento, email, username, password, isPremium);
		user.setCodigo(codigo);

		// IMPORTANTE: Añadir el usuario al pool antes de llamar a otros adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, user);

		// Recuperar propiedades que son objetos llamando a adaptadores
		// Recuperamos y añadimos las LR
		List<ListaReproduccion> listasReproduccion = obtenerListasReproduccionDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eu, "listasReproduccion"));

		for (ListaReproduccion lr : listasReproduccion)
			user.addListaReproudccion(lr);

		// Recuperamos y añadimos los videos recientes
		List<Video> videosRecientes = obtenerVideosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eu, "videosRecientes"));

		for (Video v : videosRecientes)
			user.addVideoReciente(v);

		return user;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		List<Entidad> ev = servPersistencia.recuperarEntidades("usuario");

		for (Entidad e : ev)
			usuarios.add(recuperarUsuario(e.getId()));

		return usuarios;
	}

	// ------------------- Funciones auxiliares -----------------------------
	private String obtenerCodigosVideos(List<Video> videos) {
		String strVideos = "";

		for (Video video : videos)
			strVideos += video.getCodigo() + " ";

		return strVideos.trim();
	}

	private List<Video> obtenerVideosDesdeCodigos(String strVideos) {

		List<Video> videos = new LinkedList<>();

		// Si no hay videos en la lista devolvemos la lista vacia
		if (strVideos == null || strVideos.equals(""))
			return videos;

		StringTokenizer strTok = new StringTokenizer(strVideos, " ");
		AdaptadorVideoTDS adaptadorV = AdaptadorVideoTDS.getUnicaInstancia();

		while (strTok.hasMoreTokens())
			videos.add(adaptadorV.recuperarVideo(Integer.valueOf((String) strTok.nextElement())));

		return videos;
	}

	private String obtenerCodigosListasReproduccion(HashSet<ListaReproduccion> listasReproduccion) {
		String strListasReproduccion = "";

		for (ListaReproduccion lr : listasReproduccion)
			strListasReproduccion += lr.getCodigo() + " ";

		return strListasReproduccion.trim();
	}

	private List<ListaReproduccion> obtenerListasReproduccionDesdeCodigos(String strListasReproduccion) {

		List<ListaReproduccion> listasReproduccion = new LinkedList<>();

		// Si no hay listas de reproduccion devolvemos la lista vacia
		if (strListasReproduccion == null || strListasReproduccion.equals(""))
			return listasReproduccion;

		StringTokenizer strTok = new StringTokenizer(strListasReproduccion, " ");
		AdaptadorListaReproduccionTDS adaptadorLR = AdaptadorListaReproduccionTDS.getUnicaInstancia();

		while (strTok.hasMoreTokens())
			listasReproduccion
					.add(adaptadorLR.recuperarListaReproduccion(Integer.valueOf((String) strTok.nextElement())));

		return listasReproduccion;
	}
}