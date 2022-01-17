package es.unican.ps.supermercadoonline.DAOS;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.negocio.IPedidosDAO;

@Stateless
public class PedidosDAO implements IPedidosDAO,Serializable{

	@PersistenceContext(unitName="SupermercadoCommon")
	private EntityManager em;
	
	

	public PedidosDAO() {
	}

	public Pedido creaPedido(Pedido p) {
		try {
			em.persist(p);
		} catch(EntityExistsException e) {
			return null;
		}
		return p;
	}

	public Pedido pedidoPorReferencia(String referencia) {
		Query q = em.createQuery("SELECT p FROM Pedido p WHERE p.REFERENCIA = :ref");
		q.setParameter("ref", referencia);
		try {
			return (Pedido) q.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}

	public List<Pedido> pedidos() {
		Query q = em.createQuery("SELECT p FROM Pedido p"); //SI no se crea lista y se iguala y se devuelve.
		return q.getResultList();
	}

	public Pedido modificaPedido(Pedido p) {
		try {
			return em.merge(p);
		} catch (Exception e) {
			return null;
		}
	}

	public Pedido eliminaPedido(Pedido p) {
		try {
			Pedido p1 =  em.find(Pedido.class, p.getId());
		    if (p1 != null) {
		      em.remove(p);
		    }
		} catch (Exception e) {
			return null;
		}
		return p;
	}

	public Pedido pedidoPorId(long id) {
		return em.find(Pedido.class, id);
	}

}
