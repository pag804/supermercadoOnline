package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.excepciones.ArticuloExistenteException;
import es.unican.ps.supermercadoonline.excepciones.ArticuloNoExistenteException;

import javax.ejb.Remote;

@Remote
public interface IGestionArticulos {
	public Articulo creaArticulo(Articulo a) throws ArticuloExistenteException;
	public Articulo eliminaArticulo(Articulo a) throws ArticuloNoExistenteException;
	public Articulo modificaStock(Articulo a, int nuevoStock) throws ArticuloNoExistenteException;
}
