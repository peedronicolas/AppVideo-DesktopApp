package appvideo.persistencia;

//Define una factoria abstracta que devuelve todos los DAO de la aplicacion

public abstract class FactoriaDAO {

	private static FactoriaDAO unicaInstancia;
	public static final String DAO_TDS = "appvideo.persistencia.TDSFactoriaDAO";

	/**
	 * Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO
	 */
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null)
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return unicaInstancia;
	}

	public static FactoriaDAO getInstancia() throws DAOException {
		if (unicaInstancia == null)
			return getInstancia(FactoriaDAO.DAO_TDS);
		else
			return unicaInstancia;
	}

	/* Constructor */
	protected FactoriaDAO() {
	}

	// Metodos factoria que devuelven adaptadores que implementen estos interfaces
	public abstract IAdaptadorEtiquetaDAO getEtiquetaDAO();

	public abstract IAdaptadorVideoDAO getVideoDAO();

	public abstract IAdaptadorListaReproduccionDAO getListaReproduccionDAO();

	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
}