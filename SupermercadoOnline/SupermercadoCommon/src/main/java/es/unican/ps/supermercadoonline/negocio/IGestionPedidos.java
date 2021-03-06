package es.unican.ps.supermercadoonline.negocio;

import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.dominio.LineaPedido;

import java.time.LocalTime;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IGestionPedidos {
	public Pedido confirmaPedido(LocalTime horaRecogida);
	public boolean anhadeArticuloAPedido(Articulo a, int unidades);
	public List<LineaPedido> consultaPedido();
	public void iniciaPedido(Usuario u);
}
