package appvideo.controlador;

import appvideo.modelo.CatalogoUsuarios;
import appvideo.modelo.CatalogoVideos;
import appvideo.modelo.Usuario;
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
	public ControladorAppVideo() {
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
}