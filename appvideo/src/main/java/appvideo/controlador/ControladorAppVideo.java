package appvideo.controlador;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

	private final static int MAX_VIDEOS_TENDENCIAS = 10;

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

	// ---------------------------- METODOS DE USUARIO ----------------------------

	public boolean registrarusuario(String nombre, String apellidos, Date fNacimiento, String email, String username,
			String password) {

		// Comprobamos si ya hay un usuario con este 'username', en ese caso no se
		// registra
		if (catalogoUsuarios.getUsuario(username) != null) {
			return false;
		}

		// Creamos el usuario, lo registramos y lo añadimos al catalogo
		Usuario usuario = new Usuario(nombre, apellidos, fNacimiento, email, username, password, false);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);

		return true;
	}

	public boolean login(String username, String password) {

		// Extraemos el usuario del catalogo si existe, null en caso contrario
		Usuario usuario = catalogoUsuarios.getUsuario(username);

		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}

		return false;
	}

	public void logout() {
		adaptadorUsuario.modificarUsuario(usuarioActual);
		this.usuarioActual = null;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public Boolean isUserPremium() {
		return usuarioActual.isPremium();
	}

	public void covertUserPremium() {
		usuarioActual.setIsPremium(true);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}

	// ---------------------- METODOS LISTAS DE REPRODUCCION ----------------------

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
		listaReproduccion.addVideo(video);
		adaptadorListaReproduccion.modificarListaReproduccion(listaReproduccion);
	}

	public void removeVideoToList(ListaReproduccion listaReproduccion, Video video) {
		listaReproduccion.removeVideo(video);
		adaptadorListaReproduccion.modificarListaReproduccion(listaReproduccion);
	}

	public List<ListaReproduccion> getAllListasReproduccion() {
		return usuarioActual.getListasReproduccion();
	}

	public List<String> getAllListasReproduccionNames() {
		LinkedList<String> nombresListasReproduccion = new LinkedList<>();
		for (ListaReproduccion lr : getAllListasReproduccion())
			nombresListasReproduccion.add(lr.getNombre());
		return nombresListasReproduccion;
	}

	public ListaReproduccion getListaReproduccion(String nombre) {
		return usuarioActual.getListaReproduccion(nombre);
	}

	// ---------------------------- METODOS DE VIDEOS ----------------------------

	public Video getVideo(String titulo) {
		return catalogoVideos.getVideo(titulo);
	}

	public List<Video> getAllVideos() {
		return catalogoVideos.getVideos();
	}

	public List<Video> getVideosMasVistos() {

		// Obtenemos los videos del sistema, quitamos los que no han sido reproducidos
		// y ordenamos de mayor a menor segun el num de reproducciones

		return getAllVideos().stream().filter(video -> !(video.getNumReproducciones() == 0))
				.sorted((v1, v2) -> Integer.compare(v2.getNumReproducciones(), v1.getNumReproducciones()))
				.limit(MAX_VIDEOS_TENDENCIAS).collect(Collectors.toList());
	}

	public List<Video> getVideosBusqueda(String texto, LinkedList<Etiqueta> etiquetas) {

		// Obtenemos los videos del sistema y nos quedamos con aquellos lo cuales
		// contienen la variable texto como subcadena de su titulo y ademas tienen las
		// etiquetas que han sido seleccionadas como filtro

		return getAllVideos().stream().filter(video -> video.getTitulo().toLowerCase().contains(texto.toLowerCase()))
				.filter(video -> video.getEtiquetas().containsAll(etiquetas)).collect(Collectors.toList());
	}

	public List<Video> getVideosListaReproduccion(ListaReproduccion listaReproduccion) {
		return listaReproduccion.getVideos();
	}

	public List<Video> getVideosRecientes() {
		return usuarioActual.getVideosRecientes();
	}

	public void addVideoReciente(Video video) {
		usuarioActual.addVideoReciente(video);
		adaptadorUsuario.modificarUsuario(usuarioActual);
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

	public void incrementarNumReproduccionesVideo(Video video) {
		video.incrementarNumReproducciones();
		adaptadorVideo.modificarVideo(video);
	}

	// -------------------------- METODOS DE ETIQUETAS --------------------------

	public List<Etiqueta> getAllEtiquetas() {
		return adaptadorEtiqueta.recuperarTodasEtiquetas();
	}

	public Etiqueta getEtiqueta(String nombre) {

		for (Etiqueta etiqueta : adaptadorEtiqueta.recuperarTodasEtiquetas())
			if (etiqueta.getNombre().equals(nombre.toUpperCase()))
				return etiqueta;

		return null;
	}

	public boolean addEtiquetaToVideo(Video video, String etiqueta) {

		Etiqueta etq = getEtiqueta(etiqueta);

		if (video.getEtiquetas().contains(etq))
			return false;

		if (etq == null) {
			etq = new Etiqueta(etiqueta);
			adaptadorEtiqueta.registrarEtiqueta(etq);
		}

		video.addEtiqueta(etq);
		adaptadorVideo.modificarVideo(video);
		return true;
	}

	// ----------------------------- OTROS METODOS -----------------------------

	public void generatePDF(String path) {

		FileOutputStream archivo;
		try {

			archivo = new FileOutputStream(path + "/" + usuarioActual.getUsername() + "_listasVideos.pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();

			// Añadimos el logo
			Image logo = Image.getInstance("./src/main/java/appvideo/recursos/yt-logo-p.png");
			logo.setAlignment(Chunk.ALIGN_MIDDLE);
			documento.add(logo);

			// Añadimos el encabezado
			Paragraph encabezado = new Paragraph("APP Video - PNG", FontFactory.getFont("Dialog", 20, Font.BOLDITALIC));
			encabezado.setAlignment(Element.ALIGN_CENTER);
			documento.add(encabezado);

			documento.add(Chunk.NEWLINE);

			documento.add(new Paragraph("Listas de Reproducción del usuario '" + usuarioActual.getUsername() + "'.",
					FontFactory.getFont("Dialog", 13, Font.BOLD)));

			documento.add(new Paragraph("_______________________________________________",
					FontFactory.getFont("Dialog", 20, Font.BOLDITALIC)));

			documento.add(Chunk.NEWLINE);

			LinkedList<ListaReproduccion> listasReproduccion = (LinkedList<ListaReproduccion>) ControladorAppVideo
					.getUnicaInstancia().getAllListasReproduccion();
			if (listasReproduccion.isEmpty())
				documento.add(new Paragraph("Este usuario no tiene ninguna Lista de Reproducción disponible."));
			else
				for (ListaReproduccion lr : listasReproduccion) {

					PdfPTable tabla = new PdfPTable(1);
					tabla.addCell(new Paragraph(lr.getNombre(), FontFactory.getFont("Dialog", 12, Font.BOLD)));

					ArrayList<Video> videos = lr.getVideos();
					if (videos.isEmpty())
						tabla.addCell("Esta lista no contiene ningún video.");
					else
						for (Video v : videos)
							tabla.addCell(v.getTitulo() + " - " + v.getNumReproducciones() + " reproducciones.");

					documento.add(tabla);
					documento.add(new Paragraph(" "));
				}

			documento.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}