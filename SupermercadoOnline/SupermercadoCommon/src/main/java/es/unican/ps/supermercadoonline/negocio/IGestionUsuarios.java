package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.excepciones.UsuarioExistenteException;


import javax.ejb.Remote;

@Remote
public interface IGestionUsuarios {
	public Usuario registrarse(Usuario u) throws UsuarioExistenteException;
	public boolean login(String dni);
}
