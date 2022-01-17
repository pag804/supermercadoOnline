package es.unican.ps.supermercadoonline.DAOS;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.negocio.IUsuariosDAO;

@Stateless
public class UsuariosDAO implements IUsuariosDAO,Serializable {

	@PersistenceContext(unitName="SupermercadoCommon")
	private EntityManager em;

	public UsuariosDAO() {
	}
	
	public Usuario creaUsuario(Usuario u) {
		try {
			em.persist(u);
		} catch(Exception e) {
			return null;
		}
		return u;
	}

	public Usuario usuarioPorDNI(String dni) { //Se hace con find
		return em.find(Usuario.class, dni);
	}

	public List<Usuario> usuarios() {
		Query q = em.createQuery("SELECT u FROM Usuario u"); //SI no se crea lista y se iguala y se devuelve.
		return q.getResultList();
	}

	public Usuario modificaUsuario(Usuario u) { 
		try {
			return em.merge(u);
		} catch (Exception e) {
			return null;
		}
	}

	public Usuario eliminaUsuario(Usuario u) { //Tener cuidado con managed, detached...
		try {
			Usuario u1 =  em.find(Usuario.class, u.getDni());
		    if (u1 != null) {
		      em.remove(u);
		    }
		} catch (Exception e) {
			return null;
		}
		return u;
	}

}
