package es.unican.ps.supermercadoonline.negocio;

import java.util.List;
import es.unican.ps.supermercadoonline.dominio.Pedido;
import javax.ejb.Remote;

@Remote
public interface IPedidosDAO {
	public Pedido creaPedido(Pedido p);
	public Pedido pedidoPorReferencia(String referencia);
	public List<Pedido> pedidos();
	public Pedido modificaPedido(Pedido p);
	public Pedido eliminaPedido(Pedido p);
	public Pedido pedidoPorId(long id);
}
