package appvideo.persistencia;

import java.util.List;
import appvideo.modelo.Etiqueta;

public interface IAdaptadorEtiquetaDAO {

	public void registrarEtiqueta(Etiqueta etiqueta);

	public void borrarEtiqueta(Etiqueta etiqueta);

	public void modificarEtiqueta(Etiqueta etiqueta);

	public Etiqueta recuperarEtiqueta(int codigo);

	public List<Etiqueta> recuperarTodasEtiquetas();
}