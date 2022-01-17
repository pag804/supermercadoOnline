package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Pedido;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IEntregaPedidos {
	public Pedido entregarPedido(String dni, Pedido p);
	public Pedido procesarPedido();
}
