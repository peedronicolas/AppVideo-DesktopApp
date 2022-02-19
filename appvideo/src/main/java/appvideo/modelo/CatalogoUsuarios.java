package appvideo.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import appvideo.persistencia.DAOException;
import appvideo.persistencia.FactoriaDAO;
import appvideo.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	// ATRIBUTOS:
	private Map<String, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private FactoriaDAO dao;

	// CONSTRUCTOR:
	public CatalogoUsuarios() {
		try {

			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			usuarios = new HashMap<>();
			this.cargarCatalogo();

		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	// METODOS:
	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	// Devulve todos los usuarios
	public List<Usuario> getUsuarios() {
		return new ArrayList<Usuario>(usuarios.values());
	}

	public Usuario getUsuario(int codigo) {
		for (Usuario user : usuarios.values())
			if (user.getCodigo() == codigo)
				return user;

		return null;
	}

	public Usuario getUsuario(String username) {
		return usuarios.get(username);
	}

	public void addUsuario(Usuario usuario) {
		usuarios.put(usuario.getUsername(), usuario);
	}

	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario.getUsername());
	}

	// Recupera todos los usuarios para trabajar con ellos en memoria
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosDB = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario user : usuariosDB)
			usuarios.put(user.getUsername(), user);
	}
}