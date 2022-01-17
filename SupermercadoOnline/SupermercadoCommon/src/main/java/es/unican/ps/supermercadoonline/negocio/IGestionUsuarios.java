package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.excepciones.UsuarioExistenteException;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IGestionUsuarios {
	public Usuario registrarse(Usuario u) throws UsuarioExistenteException;
	public Usuario login(String dni);
}
