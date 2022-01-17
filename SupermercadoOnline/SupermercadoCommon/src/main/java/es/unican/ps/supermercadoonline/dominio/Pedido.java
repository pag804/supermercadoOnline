package es.unican.ps.supermercadoonline.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name="Pedido")
public class Pedido implements Serializable {
	
	//Atributos
	@Id
	@GeneratedValue
	private long id;//PK
	private String referencia;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedido estado; //El enumerado se almacena como un string
	
	@Column(columnDefinition = "DATE")
	private LocalDate fecha;
	@Column(columnDefinition = "TIME")
	private LocalTime horaRecogida;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="pedido_FK")
	private List<LineaPedido> lineasPedido; //Relacion
	
	@ManyToOne
	@JoinColumn(name="usuario_FK")
	private Usuario usuario; //FK
	private double precio;
	
	//Constructor
	public Pedido() {
		
	}
	
	public Pedido(String referencia, EstadoPedido estado, LocalDate fecha, LocalTime horaRecogida,
			List<LineaPedido> lineasPedido, Usuario usuario) {
		this.referencia = referencia;
		this.estado = estado;
		this.fecha = fecha;
		this.horaRecogida = horaRecogida;
		this.lineasPedido = lineasPedido;
		this.usuario = usuario;
		this.precio = 0;
	}
	
	
	public Pedido (LocalTime horaRecogida) {
		this.horaRecogida = horaRecogida;
	}
	
	
	//Getter y Setters
	public String getReferencia() {
		return referencia;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHoraRecogida() {
		return horaRecogida;
	}
	public void setHoraRecogida(LocalTime horaRecogida) {
		this.horaRecogida = horaRecogida;
	}
	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}
	public void setLineasPedido(List<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
