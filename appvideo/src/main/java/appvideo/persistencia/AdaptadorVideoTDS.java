package appvideo.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import appvideo.modelo.Etiqueta;
import appvideo.modelo.Video;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoTDS implements IAdaptadorVideoDAO {

	// ATRIBUTOS:
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorVideoTDS unicaInstancia = null;

	// CONSTRUCTOR:
	private AdaptadorVideoTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// METODOS:
	public static AdaptadorVideoTDS getUnicaInstancia() { // Patron sigleton
		if (unicaInstancia == null)
			return new AdaptadorVideoTDS();
		else
			return unicaInstancia;
	}

	@Override
	public void registrarVideo(Video video) {

		Entidad ev = null;

		try {
			ev = servPersistencia.recuperarEntidad(video.getCodigo());
		} catch (NullPointerException e) {
		}

		if (ev != null)
			return;

		// Registrar primero los atributos que son objetos
		// Registrar las etiquetas de un video
		AdaptadorEtiquetaTDS adaptadorE = AdaptadorEtiquetaTDS.getUnicaInstancia();
		for (Etiqueta e : video.getEtiquetas())
			adaptadorE.registrarEtiqueta(e);

		// Crear entidad
		ev = new Entidad();
		ev.setNombre("video");

		// Creamos las propiedades
		ArrayList<Propiedad> props = new ArrayList<>();
		props.add(new Propiedad("titulo", video.getTitulo()));
		props.add(new Propiedad("URL", video.getURL()));
		props.add(new Propiedad("numReproducciones", String.valueOf(video.getNumReproducciones())));
		props.add(new Propiedad("etiquetas", obtenerCodigosEtiquetas(video.getEtiquetas())));
		ev.setPropiedades(props);

		// Registrar entidad
		ev = servPersistencia.registrarEntidad(ev);

		// Asignar identificador unico
		video.setCodigo(ev.getId());
	}

	@Override
	public void borrarVideo(Video video) {
		Entidad ev;

		// Primero borramos las etiquetas asociadas al video
		AdaptadorEtiquetaTDS adaptadorE = AdaptadorEtiquetaTDS.getUnicaInstancia();
		for (Etiqueta etiqueta : video.getEtiquetas())
			adaptadorE.borrarEtiqueta(etiqueta);

		ev = servPersistencia.recuperarEntidad(video.getCodigo());
		servPersistencia.borrarEntidad(ev);
	}

	@Override
	public void modificarVideo(Video video) {
		Entidad ev = servPersistencia.recuperarEntidad(video.getCodigo());

		for (Propiedad prop : ev.getPropiedades()) {

			if (prop.getNombre().equals("titulo")) {
				prop.setValor(video.getTitulo());

			} else if (prop.getNombre().equals("URL")) {
				prop.setValor(video.getURL());

			} else if (prop.getNombre().equals("C")) {
				prop.setValor(String.valueOf(String.valueOf(video.getNumReproducciones())));

			} else if (prop.getNombre().equals("etiquetas")) {
				String etiquetas = obtenerCodigosEtiquetas(video.getEtiquetas());
				prop.setValor(etiquetas);
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Video recuperarVideo(int codigo) {

		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Video) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos recuperar entidad
		Entidad ev = servPersistencia.recuperarEntidad(codigo);

		Video video = new Video(servPersistencia.recuperarPropiedadEntidad(ev, "titulo"),
				servPersistencia.recuperarPropiedadEntidad(ev, "URL"),
				Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ev, "numReproducciones")));
		video.setCodigo(codigo);

		// IMPORTANTE: Añadir la venta al pool antes de llamar a otros adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, video);

		// Recuperar propiedades que son objetos llamando a adaptadores
		List<Etiqueta> etiquetas = obtenerEtiquetasDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(ev, "etiquetas"));

		// Añadimos las etiquetas al video
		for (Etiqueta e : etiquetas)
			video.addEtiqueta(e);

		return video;
	}

	@Override
	public List<Video> recuperarTodosVideos() {
		List<Video> videos = new LinkedList<>();
		List<Entidad> ev = servPersistencia.recuperarEntidades("video");

		for (Entidad e : ev)
			videos.add(recuperarVideo(e.getId()));

		return videos;
	}

	// ------------------- Funciones auxiliares -----------------------------
	private String obtenerCodigosEtiquetas(List<Etiqueta> etiquetas) {
		String strEtiquetas = "";

		for (Etiqueta etiqueta : etiquetas)
			strEtiquetas += etiqueta.getCodigo() + " ";

		return strEtiquetas.trim();
	}

	private List<Etiqueta> obtenerEtiquetasDesdeCodigos(String strEtiquetas) {

		List<Etiqueta> etiquetas = new LinkedList<>();

		// Si no hay etiquetas devolvemos la lista vacia
		if (strEtiquetas == null || strEtiquetas.equals(""))
			return etiquetas;

		StringTokenizer strTok = new StringTokenizer(strEtiquetas, " ");
		AdaptadorEtiquetaTDS adaptadorE = AdaptadorEtiquetaTDS.getUnicaInstancia();

		while (strTok.hasMoreTokens())
			etiquetas.add(adaptadorE.recuperarEtiqueta(Integer.valueOf((String) strTok.nextElement())));

		return etiquetas;
	}
}