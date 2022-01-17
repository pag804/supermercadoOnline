package DAOLayerTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.unican.ps.supermercadoonline.DAOS.ArticulosDAO;
import es.unican.ps.supermercadoonline.dominio.Articulo;

public class ArticulosDAOUnitTest {
	
	private static ArticulosDAO sut;

	@Mock
	private static EntityManager emMock;
	
	//Situación inicial en la que se tiene un artículo (1 kg de patatas, 30 unidades de stock y 5 euros de precio)
	@Before
	public void setUp() throws Exception {
		emMock = mock(EntityManager.class);
		sut = new ArticulosDAO(emMock);
	}

	@Test
	public void testCreaArticulo() {
		//UID.1a Articulo que no existe se crea correctamente.
		Articulo pan = new Articulo("Pan", 30, 0.3);
		doNothing().when(emMock).persist((Articulo)any());
		Articulo testA = sut.creaArticulo(pan);
		assertEquals(testA.getNombre(), "Pan");
		
		//UID.1b Se intenta crear un articulo que ya existe.
		Articulo patatas = new Articulo("1 kg de patatas", 30, 5);
		doThrow(EntityExistsException.class).when(emMock).persist((Articulo)any());
		Articulo testB = sut.creaArticulo(patatas);
		assertEquals(testB, null);

		//Verificamos que se llama a ciertos métodos de los mocks las veces correspondientes
		verify(emMock, times(2)).persist((Articulo)any());
	}

}
