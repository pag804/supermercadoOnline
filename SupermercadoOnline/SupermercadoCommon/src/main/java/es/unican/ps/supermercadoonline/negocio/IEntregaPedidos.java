package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Pedido;
import javax.ejb.Remote;

@Remote
public interface IEntregaPedidos {
	public Pedido entregarPedido(String dni, Pedido p);
	public Pedido procesarPedido();
}
