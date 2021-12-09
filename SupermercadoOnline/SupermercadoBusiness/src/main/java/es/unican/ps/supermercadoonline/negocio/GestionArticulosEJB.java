package es.unican.ps.supermercadoonline.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.excepciones.ArticuloExistenteException;
import es.unican.ps.supermercadoonline.excepciones.ArticuloNoExistenteException;

@Stateless
public class GestionArticulosEJB implements IGestionArticulos {
	
	//DAOs que utiliza
	@EJB
	private IArticulosDAO articulosDao;
	
	//Constructor
	public GestionArticulosEJB(IArticulosDAO articulosDao) {
		this.articulosDao = articulosDao;
	}

	public Articulo creaArticulo(Articulo a) throws ArticuloExistenteException {
		if (articulosDao.articuloPorNombre(a.getNombre()) != null) {
			throw new ArticuloExistenteException("El articulo ya existe");
		}
		return articulosDao.creaArticulo(a);
	}

	public Articulo eliminaArticulo(Articulo a) throws ArticuloNoExistenteException {
		if (articulosDao.articuloPorNombre(a.getNombre()) == null) {
			throw new ArticuloNoExistenteException("El articulo no existe");
		}
		return articulosDao.eliminaArticulo(a);
	}

	public Articulo modificaStock(Articulo a, int nuevoStock) throws ArticuloNoExistenteException {
		if (articulosDao.articuloPorNombre(a.getNombre()) == null) {
			throw new ArticuloNoExistenteException("El articulo no existe");
		}
		a.setUnidadesStock(nuevoStock);
		return articulosDao.modificaArticulo(a);
	}

}
