package es.unican.ps.supermercadoonline.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable { //Se mapea en la tabla Usuario
	
	//Atributos
	private String nombre;
	@Id
	private String dni; //Pk
	private String direccion;
	private String email;
	private int comprasMensuales;
	
	@OneToMany(mappedBy="usuario")
	private List<Pedido> pedidos;//Relacion
	
	//Constructor
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String dni, String direccion, String email, int comprasMensuales,
			List<Pedido> pedidos) {
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.comprasMensuales = comprasMensuales;
		this.pedidos = pedidos;
	}
	
	//Getter y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getComprasMensuales() {
		return comprasMensuales;
	}
	public void setComprasMensuales(int comprasMensuales) {
		this.comprasMensuales = comprasMensuales;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
