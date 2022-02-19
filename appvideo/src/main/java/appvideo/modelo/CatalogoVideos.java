package appvideo.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import appvideo.persistencia.DAOException;
import appvideo.persistencia.FactoriaDAO;
import appvideo.persistencia.IAdaptadorVideoDAO;

public class CatalogoVideos {

	// ATRIBUTOS:
	private Map<String, Video> videos;
	private static CatalogoVideos unicaInstancia = new CatalogoVideos();
	private IAdaptadorVideoDAO adaptadorVideo;
	private FactoriaDAO dao;

	// CONSTRUCTOR:
	public CatalogoVideos() {
		try {

			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorVideo = dao.getVideoDAO();
			videos = new HashMap<>();
			this.cargarCatalogo();

		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	// METODOS:
	public static CatalogoVideos getUnicaInstancia() {
		return unicaInstancia;
	}

	// Devulve todos los videos
	public List<Video> getVideos() {
		return new ArrayList<Video>(videos.values());
	}

	public Video getVideo(int codigo) {

		for (Video v : videos.values())
			if (v.getCodigo() == codigo)
				return v;

		return null;
	}

	public Video getVideo(String titulo) {
		return videos.get(titulo);
	}

	public void addVideo(Video video) {
		videos.put(video.getTitulo(), video);
	}

	public void removeVideo(Video video) {
		videos.remove(video.getTitulo());
	}

	// Recupera todos los videos para trabajar con ellos en memoria
	private void cargarCatalogo() throws DAOException {
		List<Video> videosBD = adaptadorVideo.recuperarTodosVideos();
		for (Video video : videosBD)
			videos.put(video.getTitulo(), video);
	}
}