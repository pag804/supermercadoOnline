package es.unican.ps.supermercadoonline.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.dominio.LineaPedido;
import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.excepciones.UsuarioExistenteException;
import es.unican.ps.supermercadoonline.negocio.IGestionArticulos;
import es.unican.ps.supermercadoonline.negocio.IGestionPedidos;
import es.unican.ps.supermercadoonline.negocio.IGestionUsuarios;

@Named
@SessionScoped
public class supermercadoBean implements Serializable {

	@EJB
	private IGestionUsuarios gestionUsuarios;
	@EJB
	private IGestionArticulos gestionArticulos;
	@EJB
	private IGestionPedidos gestionPedidos;

	private String dni;

	private List<Articulo> articulos = new ArrayList<Articulo>();

	private List<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();

	private  String horaRecogida;

	private int unidades;
	private Articulo articuloSeleccionado;

	private String referencia;
	private double precio;

	private int hora;
	private int minutos;



	public supermercadoBean() {


	}

	public String entrar() {
		Usuario u1 = gestionUsuarios.login(this.dni);
		if(u1 != null) {
			gestionPedidos.iniciaPedido(u1);
			articulos=gestionArticulos.articulos();
			return "articulos.jsf";
		} else {
			return "login.jsf"; //Gestionar error
		}
	}


	public String registro() { //Checkear
		Usuario u1 = new Usuario();
		u1.setDni(this.dni);
		try {
			if (gestionUsuarios.registrarse(u1) != null) {
				gestionPedidos.iniciaPedido(u1);
				articulos=gestionArticulos.articulos();
				return "articulos.jsf";
			} else {
				return "login.jsf";
			}
		} catch (UsuarioExistenteException e) {
			return "login.jsf";
		}
	}



	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public String seleccionarArticulo(Articulo articulo) {
		setArticuloSeleccionado(articulo);
		return "articulo-seleccionado.jsf";
	}

	public String verCarro() {
		return "carrito.jsf";
	}

	public String volver() {
		return "articulos.jsf";
	}

	public String addtocart() {
		gestionPedidos.anhadeArticuloAPedido(articuloSeleccionado, unidades);
		lineasPedido = gestionPedidos.consultaPedido();
		precio = this.calculaPrecio();
		return "articulos.jsf";
	}

	public String seguircomprando() {
		return "articulos.jsf";
	}

	public String confirmarcarro() {
		LocalTime h = LocalTime.of(hora, minutos);
		horaRecogida = h.toString();
		Pedido p = gestionPedidos.confirmaPedido(h);
		if (p == null) {
			return "carrito.jsf";
		} else {
			referencia = p.getReferencia();
			precio = p.getPrecio();
			return "pedido-realizado.jsf";
		}

	}

	public String inicio() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.jsf";
	}

	//Getters y setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(List<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Articulo getArticuloSeleccionado() {
		return articuloSeleccionado;
	}

	public void setArticuloSeleccionado(Articulo articuloSeleccionado) {
		this.articuloSeleccionado = articuloSeleccionado;
	}

	public String getHoraRecogida() {
		return horaRecogida;
	}

	public void setHoraRecogida(String horaRecogida) {
		this.horaRecogida = horaRecogida;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	private double calculaPrecio() {
		double precioT = 0.0;
		for (LineaPedido lp : lineasPedido) {
			precioT += lp.getArticulo().getPrecio() * lp.getCantidad();
		}
		return precioT;
	}


}
