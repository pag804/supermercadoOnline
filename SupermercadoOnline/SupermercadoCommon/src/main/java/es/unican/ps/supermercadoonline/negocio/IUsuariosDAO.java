package es.unican.ps.supermercadoonline.negocio;

import java.util.List;
import es.unican.ps.supermercadoonline.dominio.Usuario;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IUsuariosDAO {
	public Usuario creaUsuario(Usuario u);
	public Usuario usuarioPorDNI(String dni);
	public List<Usuario> usuarios();
	public Usuario modificaUsuario(Usuario u);
	public Usuario eliminaUsuario(Usuario u);
}
