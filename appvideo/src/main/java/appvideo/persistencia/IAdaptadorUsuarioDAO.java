package appvideo.persistencia;

import java.util.List;
import appvideo.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario usuario);

	public void borrarUsuario(Usuario usuario);

	public void modificarUsuario(Usuario usuario);

	public Usuario recuperarUsuario(int codigo);

	public List<Usuario> recuperarTodosUsuarios();
}
