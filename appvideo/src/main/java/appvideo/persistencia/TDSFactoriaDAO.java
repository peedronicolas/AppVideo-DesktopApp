package appvideo.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
	}

	@Override
	public IAdaptadorVideoDAO getVideoDAO() {
		return AdaptadorVideoTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorEtiquetaDAO getEtiquetaDAO() {
		return AdaptadorEtiquetaTDS.getUnicaInstancia();
	}
}
