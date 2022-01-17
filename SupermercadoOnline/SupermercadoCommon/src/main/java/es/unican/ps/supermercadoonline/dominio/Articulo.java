package es.unican.ps.supermercadoonline.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Articulo")
public class Articulo implements Serializable { //Se mapeará en una tabla que se llama Articulo

	//Atributos
	@Id
	private String nombre;
	private int unidadesStock;
	private double precio;
	
	public Articulo() {
		
	}
	
	//Constructor
	public Articulo(String nombre, int unidadesStock, double precio) {
		this.nombre = nombre;
		this.unidadesStock = unidadesStock;
		this.precio = precio;
	}

	//Getters y setters
	
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
