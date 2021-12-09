package es.unican.ps.supermercadoonline.excepciones;

public class ArticuloNoExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ArticuloNoExistenteException(String msg) {
		super(msg);
	}
	
}
