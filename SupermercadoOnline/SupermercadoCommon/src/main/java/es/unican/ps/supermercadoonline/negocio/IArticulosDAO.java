package es.unican.ps.supermercadoonline.negocio;

import java.util.List;
import es.unican.ps.supermercadoonline.dominio.Articulo;

import javax.ejb.Remote;

@Remote
public interface IArticulosDAO {
	public Articulo creaArticulo(Articulo a);
	public Articulo articuloPorNombre(String nombre);
	public List<Articulo> articulos();
	public Articulo modificaArticulo(Articulo a);
	public Articulo eliminaArticulo(Articulo a);
	public Articulo articuloPorId(long id);
}
