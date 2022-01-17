package es.unican.ps.supermercadoonline.DAOS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.negocio.IArticulosDAO;

@Stateless
public class ArticulosDAO implements IArticulosDAO,Serializable{
	
	@PersistenceContext(unitName="SupermercadoCommon")
	private EntityManager em;
	
	
	public ArticulosDAO() {
	}
	
	public ArticulosDAO(EntityManager em) {
		this.em = em;
	}

	public Articulo creaArticulo(Articulo a) {
		try {
			em.persist(a);
		} catch(Exception e) {
			return null;
		}
		return a;
	}

	public Articulo articuloPorNombre(String nombre) {
		return em.find(Articulo.class, nombre);
	}

	public List<Articulo> articulos() {
		Query q = em.createQuery("SELECT a FROM Articulo a"); //SI no se crea lista y se iguala y se devuelve.
		List<Articulo> arrayList = new ArrayList<Articulo>();
		arrayList= q.getResultList();
		return arrayList;
	}

	public Articulo modificaArticulo(Articulo a) {
		try {
			return em.merge(a);
		} catch (Exception e) {
			return null;
		}
	}

	public Articulo eliminaArticulo(Articulo a) {
		try {
			Articulo a1 =  em.find(Articulo.class, a.getNombre());
		    if (a1 != null) {
		      em.remove(a);
		    }
		} catch (Exception e) {
			return null;
		}
		return a;
	}

}
