package es.unican.ps.supermercadoonline.excepciones;

public class ArticuloExistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ArticuloExistenteException(String msg) {
		super(msg);
	}
	
}
