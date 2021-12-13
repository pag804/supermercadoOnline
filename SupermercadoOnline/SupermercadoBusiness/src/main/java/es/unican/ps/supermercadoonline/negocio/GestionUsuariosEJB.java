package es.unican.ps.supermercadoonline.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.excepciones.UsuarioExistenteException;

@Stateless
public class GestionUsuariosEJB implements IGestionUsuarios {

	//DAOs que utiliza
	@EJB
	private IUsuariosDAO usuariosDao;
	
	//Constructor
	public GestionUsuariosEJB(IUsuariosDAO usuariosDao) {
		this.usuariosDao = usuariosDao;
	}
	
	

	public GestionUsuariosEJB() {
	}



	public Usuario registrarse(Usuario u) throws UsuarioExistenteException {
		if (usuariosDao.usuarioPorDNI(u.getDni()) != null) {
			throw new UsuarioExistenteException("El usuario ya existe");
		}
		return usuariosDao.creaUsuario(u);
	}

	public boolean login(String dni) {
		if (usuariosDao.usuarioPorDNI(dni) == null) {
			return false;
		} else {
			return true;
		}
	}

}
