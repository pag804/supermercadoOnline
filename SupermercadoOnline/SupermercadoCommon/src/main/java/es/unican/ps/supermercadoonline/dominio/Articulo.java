package es.unican.ps.supermercadoonline.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Articulo implements Serializable { //Se mapeará en una tabla que se llama Articulo

	//Atributos
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private int unidadesStock;
	private double precio;
	
	//Constructor
	public Articulo(String nombre, int unidadesStock, double precio) {
		this.nombre = nombre;
		this.unidadesStock = unidadesStock;
		this.precio = precio;
	}

	//Getters y setters
	public long getid() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getUnidadesStock() {
		return unidadesStock;
	}
	
	public void setUnidadesStock(int unidadesStock) {
		this.unidadesStock = unidadesStock;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
