package es.unican.ps.supermercadoonline.dominio;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido {
	
	//Atributos
	private long id;//PK
	private String referencia;
	private EstadoPedido estado;
	private LocalDate fecha;
	private LocalTime horaRecogida;
	private List<LineaPedido> lineasPedido;
	private Usuario usuario;
	
	//Constructor
	public Pedido(String referencia, EstadoPedido estado, LocalDate fecha, LocalTime horaRecogida,
			List<LineaPedido> lineasPedido, Usuario usuario) {
		this.referencia = referencia;
		this.estado = estado;
		this.fecha = fecha;
		this.horaRecogida = horaRecogida;
		this.lineasPedido = lineasPedido;
		this.usuario = usuario;
	}
	
	//Getter y Setters
	public String getReferencia() {
		return referencia;
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
	
	
	
}
