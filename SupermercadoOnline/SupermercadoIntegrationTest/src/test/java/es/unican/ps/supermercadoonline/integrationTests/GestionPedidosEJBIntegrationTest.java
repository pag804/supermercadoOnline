package es.unican.ps.supermercadoonline.integrationTests;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.negocio.GestionPedidosEJB;
import es.unican.ps.supermercadoonline.negocio.IArticulosDAO;
import es.unican.ps.supermercadoonline.negocio.IGestionPedidos;
import es.unican.ps.supermercadoonline.negocio.IPedidosDAO;
import es.unican.ps.supermercadoonline.negocio.IUsuariosDAO;

public class GestionPedidosEJBIntegrationTest {

	private static EJBContainer ec;
	private static IGestionPedidos sut;
	private static IUsuariosDAO userDAO;
	private static IArticulosDAO articulosDAO;
	private static IPedidosDAO pedidosDAO;

	@BeforeClass
	public static void initContainer() {
		Map properties = new HashMap();
		properties.put(EJBContainer.MODULES, new File[]{new File("classes")});
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "C:/Users/pedro/Desktop/glassfish5/glassfish");

		//Creamos el contenedor embebido, que por defecto inicializa todos los EJB que estén en el classpath
		ec = EJBContainer.createEJBContainer(properties);
		// Buscamos el (o los) EJB que necesitemos a través de JNDI
		try {
			System.out.print("");
			sut = (IGestionPedidos) ec.getContext().lookup("java:global/ejb-app/SupermercadoBusiness/GestionPedidosEJB!es.unican.ps.supermercadoonline.negocio.IGestionPedidos");
			userDAO = (IUsuariosDAO) ec.getContext().lookup("java:global/ejb-app/SupermercadoDAO-0_0_1/UsuariosDAO!es.unican.ps.supermercadoonline.negocio.IUsuariosDAO");
			articulosDAO = (IArticulosDAO) ec.getContext().lookup("java:global/ejb-app/SupermercadoDAO-0_0_1/ArticulosDAO!es.unican.ps.supermercadoonline.negocio.IArticulosDAO");
			pedidosDAO = (IPedidosDAO) ec.getContext().lookup("java:global/ejb-app/SupermercadoDAO-0_0_1/PedidosDAO!es.unican.ps.supermercadoonline.negocio.IPedidosDAO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void closeContainer() throws Exception {
		//Cerramos el contenedor (Importante)
		if (ec != null) {
			ec.close();
		}
	}

	@Test
	public void confirmaPedidoTest() {
	
		//Si quisieramos borrar 1-lineas 2-pedido 3-user 4-articulos
		//Creamos la situacion inicial
		Usuario u1 = userDAO.usuarioPorDNI("27516415G");
		Articulo a1 = articulosDAO.articuloPorNombre("Patatas");

		if (u1 == null) {
			u1 = new Usuario("Jose Manuel Garcia Sanchez", "27516415G",  "josemanuel@gmail.com", "Calle Castilla 36", 0, new ArrayList<Pedido>());
			userDAO.creaUsuario(u1);
		}

		if (a1 == null) {
			a1 = new Articulo("Patatas", 20, 1.0);
			articulosDAO.creaArticulo(a1);
		}

		LocalTime horaRecoger = LocalTime.of(17,50);

		//UGP.1a
		//Anhades las lineas al pedido
		sut.iniciaPedido(u1);
		sut.anhadeArticuloAPedido(a1,1);
		Pedido p1 = sut.confirmaPedido(horaRecoger);
		assertEquals(p1.getReferencia(), u1.getDni() + horaRecoger.toString());
		assertTrue(p1.getPrecio()== 0.95); //Se pone esto ya que tiene +10 compras entonces hay descuento. 

		//UGP.1b
		Usuario u2 = new Usuario("Jose Manuel Garcia Bosco", "27516415H",  "josemanuel1@gmail.com", "Calle Castilla 67", 0, new ArrayList<Pedido>());
		sut.iniciaPedido(u2);
		assertEquals(sut.confirmaPedido(horaRecoger), null);

		//UGP.1c
		sut.iniciaPedido(u1);
		assertEquals(sut.confirmaPedido(LocalTime.of(23,0)), null);
	} 

}
