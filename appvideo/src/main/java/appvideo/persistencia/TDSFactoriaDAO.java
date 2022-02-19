package appvideo.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
	}

	@Override
	public IAdaptadorEtiquetaDAO getEtiquetaDAO() {
		return AdaptadorEtiquetaTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return AdaptadorVideoTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaReproduccionDAO getListaReproduccionDAO() {
		return AdaptadorListaReproduccionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}
}
