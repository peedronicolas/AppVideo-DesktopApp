package appvideo.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import appvideo.modelo.Etiqueta;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorEtiquetaTDS implements IAdaptadorEtiquetaDAO {

	// ATRIBUTOS:
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorEtiquetaTDS unicaInstancia = null;

	// CONSTRUCTOR:
	private AdaptadorEtiquetaTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	// METODOS:
	public static AdaptadorEtiquetaTDS getUnicaInstancia() { // Patron singleton
		if (unicaInstancia == null)
			return new AdaptadorEtiquetaTDS();
		else
			return unicaInstancia;
	}

	@Override
	public void registrarEtiqueta(Etiqueta etiqueta) {

		Entidad ee = null;

		try {
			ee = servPersistencia.recuperarEntidad(etiqueta.getCodigo());
		} catch (NullPointerException e) {
		}

		if (ee != null)
			return;

		// Crear entidad
		ee = new Entidad();
		ee.setNombre("etiqueta");

		// Creamos las propiedades
		ArrayList<Propiedad> props = new ArrayList<>();
		props.add(new Propiedad("nombre", etiqueta.getNombre()));
		ee.setPropiedades(props);

		// Registrar entidad
		ee = servPersistencia.registrarEntidad(ee);

		// Asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		etiqueta.setCodigo(ee.getId());
	}

	@Override
	public void borrarEtiqueta(Etiqueta etiqueta) {
		Entidad ee = servPersistencia.recuperarEntidad(etiqueta.getCodigo());
		servPersistencia.borrarEntidad(ee);
	}

	@Override
	public void modificarEtiqueta(Etiqueta etiqueta) {
		Entidad ee = servPersistencia.recuperarEntidad(etiqueta.getCodigo());

		for (Propiedad prop : ee.getPropiedades()) {

			if (prop.getNombre().equals("nombre"))
				prop.setValor(etiqueta.getNombre());

			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Etiqueta recuperarEtiqueta(int codigo) {

		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Etiqueta) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos recuperar entidad
		Entidad ee = servPersistencia.recuperarEntidad(codigo);

		Etiqueta etiqueta = new Etiqueta(servPersistencia.recuperarPropiedadEntidad(ee, "nombre"));
		etiqueta.setCodigo(codigo);

		PoolDAO.getUnicaInstancia().addObjeto(codigo, etiqueta);

		return etiqueta;
	}

	@Override
	public List<Etiqueta> recuperarTodasEtiquetas() {
		List<Etiqueta> etiquetas = new LinkedList<>();
		List<Entidad> ee = servPersistencia.recuperarEntidades("etiqueta");

		for (Entidad e : ee)
			etiquetas.add(recuperarEtiqueta(e.getId()));

		return etiquetas;
	}
}