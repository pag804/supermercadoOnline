package es.unican.ps.supermercadoonline.dominio;

public class LineaPedido {

	//Atributos
	private long id;
	private int cantidad;
	private Articulo articulo;
	
	//Constructor
	public LineaPedido(int cantidad, Articulo articulo) {
		this.cantidad = cantidad;
		this.articulo = articulo;
	}
	
	//Getter y setters
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
}
