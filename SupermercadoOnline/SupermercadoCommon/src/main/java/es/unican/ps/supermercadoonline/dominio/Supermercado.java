package es.unican.ps.supermercadoonline.dominio;

import java.util.List;
import java.time.LocalTime;


public class Supermercado { //No es necesario persistir esta entidad
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private List<Articulo> articulos; 
	private List<Usuario> usuarios; 
	
	//Constructor
	public Supermercado(LocalTime horaApertura, LocalTime horaCierre, List<Articulo> articulos,
			List<Usuario> usuarios) {
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.articulos = articulos;
		this.usuarios = usuarios;
	}
	
	//Getter y setters
	public LocalTime getHoraApertura() {
		return horaApertura;
	}
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}
	public LocalTime getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
	public List<Articulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
