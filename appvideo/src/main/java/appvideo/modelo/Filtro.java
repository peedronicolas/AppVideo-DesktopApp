package appvideo.modelo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import appvideo.controlador.ControladorAppVideo;

// Patron ESTRATEGIA
public interface Filtro {

	public FiltrosName getName();

	// Recibira una lista de videos y devolvera otra la cual excluya los que no
	// permita el filtro
	public List<Video> aplicarFiltroVideos(List<Video> videos);
}

// Devolvemos toda la lista de videos sin eliminar ninguno
class FiltroSinFiltro implements Filtro {

	@Override
	public List<Video> aplicarFiltroVideos(List<Video> videos) {
		return videos;
	}

	@Override
	public FiltrosName getName() {
		return FiltrosName.SIN_FILTRO;
	}
}

// Elimina aquellos videos que tienen la etiqueta adultos, pero solo si el usuario es menor de edad, si es mayor se devuelven todos
class FiltroAdultos implements Filtro {

	@Override
	public List<Video> aplicarFiltroVideos(List<Video> videos) {

		if (ControladorAppVideo.getUnicaInstancia().getUsuarioActual().isMayorEdad())
			return videos;

		Etiqueta etq = ControladorAppVideo.getUnicaInstancia().getEtiqueta("ADULTOS");
		return videos.stream().filter(video -> !video.getEtiquetas().contains(etq)).collect(Collectors.toList());
	}

	@Override
	public FiltrosName getName() {
		return FiltrosName.MENORES;
	}
}

// Elimina aquellos videos que han sido reproducidos menos de 5 veces
class FiltroImpopulares implements Filtro {

	@Override
	public List<Video> aplicarFiltroVideos(List<Video> videos) {
		return videos.stream().filter(video -> video.getNumReproducciones() >= 5).collect(Collectors.toList());
	}

	@Override
	public FiltrosName getName() {
		return FiltrosName.IMPOPULARES;
	}
}

// Elimina aquellos videos que estan en alguna LR del usuario.
class FiltroMisListas implements Filtro {

	@Override
	public List<Video> aplicarFiltroVideos(List<Video> videos) {

		// Obtenemos el conjunto de todos los videos que estan en alguna lista de
		// reproduccion del usuario
		Set<Video> videosListasReproduccion = ControladorAppVideo.getUnicaInstancia().getAllListasReproduccion()
				.stream().map(lr -> lr.getVideos()).flatMap(v -> v.stream()).collect(Collectors.toSet());

		return videos.stream().filter(v -> !videosListasReproduccion.contains(v)).collect(Collectors.toList());
	}

	@Override
	public FiltrosName getName() {
		return FiltrosName.MIS_LISTAS;
	}
}

// Elimina aquellos videos que tienen mas de 16 caracteres de longitud en el titulo
class FiltroNombresLargos implements Filtro {

	@Override
	public List<Video> aplicarFiltroVideos(List<Video> videos) {
		return videos.stream().filter(video -> video.getTitulo().length() <= 16).collect(Collectors.toList());
	}

	@Override
	public FiltrosName getName() {
		return FiltrosName.NOMBRES_LARGOS;
	}
}