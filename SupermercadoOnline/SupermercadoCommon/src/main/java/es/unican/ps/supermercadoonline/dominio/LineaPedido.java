package es.unican.ps.supermercadoonline.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="LineaPedido")
public class LineaPedido implements Serializable { //Se mapea en una tabla LineaPedido

	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private int cantidad;
	@OneToOne
    @JoinColumn(name = "articulo_FK")
	private Articulo articulo; //Fk
	
	public LineaPedido() {
		
	}
	
	//Constructor
	public LineaPedido(int cantidad, Articulo articulo) {
		this.cantidad = cantidad;
		this.articulo = articulo;
	}
	
	
	
	//Getter y setters
	public int getCantidad() {
		return cantidad;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
