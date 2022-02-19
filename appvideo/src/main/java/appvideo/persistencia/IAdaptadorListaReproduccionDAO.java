package appvideo.persistencia;

import java.util.List;
import appvideo.modelo.ListaReproduccion;

public interface IAdaptadorListaReproduccionDAO {

	public void registrarListaReproduccion(ListaReproduccion listaReproduccion);

	public void borrarListaReproduccion(ListaReproduccion listaReproduccion);

	public void modificarListaReproduccion(ListaReproduccion listaReproduccion);

	public ListaReproduccion recuperarListaReproduccion(int codigo);

	public List<ListaReproduccion> recuperarTodasListasReproduccion();
}
