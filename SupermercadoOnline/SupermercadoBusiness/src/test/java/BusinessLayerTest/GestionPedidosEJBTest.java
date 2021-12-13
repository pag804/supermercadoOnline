package BusinessLayerTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.unican.ps.supermercadoonline.dominio.Articulo;
import es.unican.ps.supermercadoonline.dominio.EstadoPedido;
import es.unican.ps.supermercadoonline.dominio.LineaPedido;
import es.unican.ps.supermercadoonline.dominio.Pedido;
import es.unican.ps.supermercadoonline.dominio.Usuario;
import es.unican.ps.supermercadoonline.negocio.GestionPedidosEJB;
import es.unican.ps.supermercadoonline.negocio.IArticulosDAO;
import es.unican.ps.supermercadoonline.negocio.IPedidosDAO;
import es.unican.ps.supermercadoonline.negocio.IUsuariosDAO;

public class GestionPedidosEJBTest {

	private static GestionPedidosEJB sut;

	@Mock
	private static IArticulosDAO articulosDaoMock;

	@Mock
	private static IUsuariosDAO usuariosDaoMock;
	
	@Mock
	private static IPedidosDAO pedidosDaoMock;
	
	private Usuario u1, u2;
	private LocalTime horaRecoger = null;
	

	@Before
	public void setUp() throws Exception {
		//Creamos 3 mocks
		articulosDaoMock = mock(IArticulosDAO.class);
		usuariosDaoMock = mock(IUsuariosDAO.class) ;
		pedidosDaoMock = mock(IPedidosDAO.class);
		
		u1 = new Usuario("Jose Manuel Garcia Sanchez", "27516415G",  "josemanuel@gmail.com", "Calle Castilla 36", 0, new ArrayList<Pedido>());
		u2 = null;
		horaRecoger = LocalTime.of(17,50);
		
		when(usuariosDaoMock.usuarioPorDNI("27516415G")).thenReturn(u1);
		when(usuariosDaoMock.usuarioPorDNI("11111111A")).thenReturn(null);
		List<LineaPedido> articulosPorPedido = new ArrayList<LineaPedido>();
		articulosPorPedido.add(new LineaPedido(1, new Articulo("Patatas", 20, 1.0)));
		when(pedidosDaoMock.creaPedido((Pedido)any()))
			.thenReturn(new Pedido(u1.getDni() + horaRecoger.toString(), EstadoPedido.REALIZADO, LocalDate.now(), horaRecoger, articulosPorPedido, u1));
		
		sut = new GestionPedidosEJB(pedidosDaoMock, articulosDaoMock, usuariosDaoMock);
	}

	@Test
	public void confirmaPedidoTest() {
		//UGP.1a
		//Anhades las lineas al pedido
		sut.iniciaPedido(u1);
		assertEquals(sut.confirmaPedido(horaRecoger).getReferencia(), u1.getDni() + horaRecoger.toString());
		
		//UGP.1b
		sut.iniciaPedido(u2);
		assertEquals(sut.confirmaPedido(horaRecoger), null);
		
		//UGP.1c
		sut.iniciaPedido(u1);
		assertEquals(sut.confirmaPedido(LocalTime.of(23,0)), null);
		
		//Verificamos que se llama a ciertos métodos de los mocks las veces correspondientes
		verify(pedidosDaoMock, times(1)).creaPedido((Pedido)any());
		
		verify(usuariosDaoMock, times(1)).modificaUsuario((Usuario)any());
	}

}
