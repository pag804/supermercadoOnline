package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.excepciones.ArticuloExistenteException;
import es.unican.ps.supermercadoonline.excepciones.ArticuloNoExistenteException;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IGestionArticulos {
	public Articulo creaArticulo(Articulo a) throws ArticuloExistenteException;
	public Articulo eliminaArticulo(Articulo a) throws ArticuloNoExistenteException;
	public Articulo modificaStock(Articulo a, int nuevoStock) throws ArticuloNoExistenteException;
	public List<Articulo> articulos();
}
