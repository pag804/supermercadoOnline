package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Articulo;
import javax.ejb.Remote;

@Remote
public interface IGestionPedidos {
	public Pedido confirmaPedido();
	public boolean anhadeArticuloAPedido(Articulo a);
	public Pedido consultaPedido();
}
