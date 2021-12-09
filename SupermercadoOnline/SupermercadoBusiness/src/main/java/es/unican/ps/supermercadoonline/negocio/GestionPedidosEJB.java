package es.unican.ps.supermercadoonline.negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.dominio.Pedido;

@Stateful
public class GestionPedidosEJB implements IGestionPedidos {
	
	//@Resource
	//TimerService timerService; 
	
	//@PersistenceContext(unitName = "pedidos") 
	//private EntityManager em;
	
	@EJB
	private IPedidosDAO pedidosDao;
	@EJB
	private IArticulosDAO articulosDao;
	@EJB
	private IUsuariosDAO usuariosDAO;
	
	private Pedido pedido; //Pedido de la sesion
	private Set <Articulo> articulos;
	
	public GestionPedidosEJB(IPedidosDAO pedidosDao, IArticulosDAO articulosDao, IUsuariosDAO usuariosDAO) {
		this.pedidosDao = pedidosDao;
		this.articulosDao = articulosDao;
		this.usuariosDAO = usuariosDAO;
	}
	
	public GestionPedidosEJB() {
		articulos = new HashSet<Articulo>();
	}

	public Pedido confirmaPedido() {
		// TODO Auto-generated method stub
		//Devuelve un new pedido con la lista de articulos
		return null;
	}

	public boolean anhadeArticuloAPedido(Articulo a, int unidades) {
		// TODO Auto-generated method stub
		//Añade un articulo al set
		return false;
	}

	public Pedido consultaPedido() {
		// TODO Auto-generated method stub
		//Devuelve la lista de articulos (cambiar el metodo de retorno)
		return null;
	}
	
	//Metodo con el timer Quien tiene el metodo timeout y quien programa ese timer
}
