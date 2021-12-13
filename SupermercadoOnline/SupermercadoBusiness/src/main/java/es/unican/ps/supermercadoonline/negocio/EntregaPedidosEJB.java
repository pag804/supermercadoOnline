package es.unican.ps.supermercadoonline.negocio;

import java.time.LocalTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercadoonline.dominio.EstadoPedido;
import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Usuario;

@Stateless
public class EntregaPedidosEJB implements IEntregaPedidos {
	
	@EJB
	private IPedidosDAO pedidosDao;
	@EJB
	private IUsuariosDAO usuariosDao;
	
	public EntregaPedidosEJB(IPedidosDAO pedidosDao, IUsuariosDAO usuariosDao) {
		this.pedidosDao = pedidosDao;
		this.usuariosDao = usuariosDao;
	}
	
	public EntregaPedidosEJB() {
	}



	public Pedido entregarPedido(String dni, Pedido p) {
		Usuario u = usuariosDao.usuarioPorDNI(dni);
		if ((u==null) || (pedidosDao.pedidoPorReferencia(p.getReferencia()).getUsuario().getDni() != dni)) {
			return null;
		}
		p.setEstado(EstadoPedido.ENTREGADO);
		pedidosDao.modificaPedido(p);
		u.setComprasMensuales(u.getComprasMensuales()+1);
		usuariosDao.modificaUsuario(u);
		return p;
	}

	public Pedido procesarPedido() {
		List <Pedido> listaPedidos = pedidosDao.pedidos();
		Pedido pedidoMasImportante = new Pedido(LocalTime.MAX);
		for (int i = 0; i<listaPedidos.size();i++) {
			Pedido p = listaPedidos.get(i);
			if (p.getEstado() == EstadoPedido.REALIZADO && p.getHoraRecogida().isBefore(pedidoMasImportante.getHoraRecogida())) {
				pedidoMasImportante = p;
			}
		}
		pedidoMasImportante.setEstado(EstadoPedido.PROCESANDO);
		pedidosDao.modificaPedido(pedidoMasImportante);
		return pedidoMasImportante;
	}

}
