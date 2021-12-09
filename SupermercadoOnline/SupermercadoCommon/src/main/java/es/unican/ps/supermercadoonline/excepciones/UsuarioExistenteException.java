package es.unican.ps.supermercadoonline.excepciones;

public class UsuarioExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String msg) {
		super(msg);
	}
	
}
