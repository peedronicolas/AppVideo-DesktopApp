package appvideo.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import appvideo.modelo.ListaReproduccion;
import appvideo.modelo.Video;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaReproduccionTDS implements IAdaptadorListaReproduccionDAO {

	// ATRIBUTOS:
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaReproduccionTDS unicaInstancia = null;

	// CONSTRUCTOR:
	private AdaptadorListaReproduccionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// METODOS:
	public static AdaptadorListaReproduccionTDS getUnicaInstancia() { // Patron sigleton
		if (unicaInstancia == null)
			return new AdaptadorListaReproduccionTDS();
		else
			return unicaInstancia;
	}

	@Override
	public void registrarListaReproduccion(ListaReproduccion listaReproduccion) {
		Entidad elr = null;

		// Comprobamos si ya existe la LR
		try {
			elr = servPersistencia.recuperarEntidad(listaReproduccion.getCodigo());
		} catch (NullPointerException e) {
		}

		if (elr != null)
			return;

		// Registrar primero los atributos que son objetos
		// Registrar los videos, aunque cuando un video se añade a una lista en teoria
		// es porque esta registrado ya en el sistema, por lo que el metodo no se
		// llegara a ejecutar completamente
		AdaptadorVideoTDS adaptadorV = AdaptadorVideoTDS.getUnicaInstancia();
		for (Video v : listaReproduccion.getVideos())
			adaptadorV.registrarVideo(v);

		// Crear entidad
		elr = new Entidad();
		elr.setNombre("listaReproduccion");

		// Creamos las propiedades
		ArrayList<Propiedad> props = new ArrayList<>();
		props.add(new Propiedad("nombre", listaReproduccion.getNombre()));
		props.add(new Propiedad("videos", obtenerCodigosVideos(listaReproduccion.getVideos())));
		elr.setPropiedades(props);

		// Registrar entidad
		elr = servPersistencia.registrarEntidad(elr);

		// Asignar identificador unico
		listaReproduccion.setCodigo(elr.getId());
	}

	@Override
	public void borrarListaReproduccion(ListaReproduccion listaReproduccion) {
		Entidad elr;

		// En principio cuando se borra una lista de reproduccion no se borran los
		// videos del sistema asociados a ella

		elr = servPersistencia.recuperarEntidad(listaReproduccion.getCodigo());
		servPersistencia.borrarEntidad(elr);
	}

	@Override
	public void modificarListaReproduccion(ListaReproduccion listaReproduccion) {
		Entidad elr = servPersistencia.recuperarEntidad(listaReproduccion.getCodigo());

		for (Propiedad prop : elr.getPropiedades()) {

			if (prop.getNombre().equals("nombre")) {
				prop.setValor(listaReproduccion.getNombre());

			} else if (prop.getNombre().equals("videos")) {
				String videos = obtenerCodigosVideos(listaReproduccion.getVideos());
				prop.setValor(videos);
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public ListaReproduccion recuperarListaReproduccion(int codigo) {

		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ListaReproduccion) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos recuperar entidad
		Entidad elr = servPersistencia.recuperarEntidad(codigo);

		ListaReproduccion lr = new ListaReproduccion(servPersistencia.recuperarPropiedadEntidad(elr, "nombre"));
		lr.setCodigo(codigo);

		// IMPORTANTE: Añadir la LR al pool antes de llamar a otros adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, lr);

		// Recuperar propiedades que son objetos llamando a adaptadores
		List<Video> videos = obtenerVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(elr, "videos"));

		// Añadimos los videos a la LR
		for (Video v : videos)
			lr.addVideo(v);

		return lr;
	}

	@Override
	public List<ListaReproduccion> recuperarTodasListasReproduccion() {
		List<ListaReproduccion> listasReproduccion = new LinkedList<>();
		List<Entidad> ev = servPersistencia.recuperarEntidades("listaReproduccion");

		for (Entidad e : ev)
			listasReproduccion.add(recuperarListaReproduccion(e.getId()));

		return listasReproduccion;
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
}