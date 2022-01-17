package es.unican.ps.supermercadoonline.negocio;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.dominio.EstadoPedido;
import es.unican.ps.supermercadoonline.dominio.LineaPedido;
import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Usuario;

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
	private IUsuariosDAO usuariosDao;

	private List <LineaPedido> articulosPedidos;
	private Usuario usuarioPedido;

	public GestionPedidosEJB(IPedidosDAO pedidosDao, IArticulosDAO articulosDao, IUsuariosDAO usuariosDao) {
		this.pedidosDao = pedidosDao;
		this.articulosDao = articulosDao;
		this.usuariosDao = usuariosDao;
		articulosPedidos = new ArrayList<LineaPedido>();
	}

	public GestionPedidosEJB() {
		articulosPedidos = new ArrayList<LineaPedido>();
	}

	public Pedido confirmaPedido(LocalTime horaRecogida) { //Horas se pasan como parámetro 
		if (horaRecogida.isBefore(LocalTime.of(9, 0))|| horaRecogida.isAfter(LocalTime.of(21, 0))) { //Se comprueba la hora
			return null;
		}
		//Se comprueba si existe el usuario
		try {
			if (usuariosDao.usuarioPorDNI(usuarioPedido.getDni())==null) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		
		//Devuelve un new pedido con la lista de articulos
		LocalDate date = LocalDate.now();
		Pedido pedido = new Pedido(usuarioPedido.getDni() + horaRecogida.toString(), EstadoPedido.REALIZADO, date, horaRecogida, articulosPedidos, usuarioPedido); // Metodo para coger el usuario en la vista
		double precioT = 0;
		for (LineaPedido lineaPedido : articulosPedidos) {
			precioT += lineaPedido.getArticulo().getPrecio() * lineaPedido.getCantidad();
		}
		
		if (usuarioPedido.getComprasMensuales() > 10) {
			precioT = 0.95*precioT;
		}
		pedido.setPrecio(precioT);
		if (pedidosDao.creaPedido(pedido) == null) {
			return null;
		};
		//Sumar compras mensuales
		usuarioPedido.setComprasMensuales(usuarioPedido.getComprasMensuales() + 1);
		usuariosDao.modificaUsuario(usuarioPedido);
		return pedido;
	}

	public boolean anhadeArticuloAPedido(Articulo a, int unidades) {
		Articulo a1 = articulosDao.articuloPorNombre(a.getNombre());
		if (a1 != null && (a1.getUnidadesStock() >= unidades || unidades > 0)) {
			LineaPedido lp = new LineaPedido(unidades, a1);
			articulosPedidos.add(lp);
			a1.setUnidadesStock(a1.getUnidadesStock() - unidades);
			articulosDao.modificaArticulo(a1);
			return true;
		}
		return false;
	}

	public List <LineaPedido> consultaPedido() { //Devuelves las lineas de pedidos
		return articulosPedidos;
	}
	
	public void iniciaPedido(Usuario u) { //La vista al pulsar el boton de login llama a este metodo
		usuarioPedido = u;
	}

	//Metodo con el timer Quien tiene el metodo timeout y quien programa ese timer Aki el metodo, el timer en otro lao
}
