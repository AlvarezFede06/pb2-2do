
package ar.edu.unlam.pb2.Parcial01;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TiendaTest {

	@Test
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Vendedor vendedor = new Vendedor("12345678", "Vendedor Test");
		vendedor.setDeLicencia(true);
		Cliente cliente = new Cliente("20-87654321-7", "Cliente Test");
		Venta venta = new Venta("V001", cliente, vendedor);
		try {
			tienda.agregarVenta(venta);
			fail("Debería haber lanzado una excepción");
		} catch (VendedorDeLicenciaException e) {
			assertEquals("El vendedor está de licencia", e.getMessage());
		}
	}

	@Test
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Producto producto = new Producto(1, "Producto Test", 100.0, 10);
		try {
			tienda.agregarProductoAVenta("V001", producto);
			fail("Debería haber lanzado una excepción");
		} catch (VendibleInexistenteException e) {
			assertEquals("El producto no existe", e.getMessage());
		}
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Producto producto1 = new Producto(1, "Producto 1", 100.0, 10);
		Producto producto2 = new Producto(2, "Producto 2", 200.0, 20);
		tienda.agregarProducto(producto1, 5);
		tienda.agregarProducto(producto2, 30);
		List<Producto> productos = tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion();
		assertTrue(productos.contains(producto1));
		assertFalse(productos.contains(producto2));
	}

	@Test
	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Cliente cliente1 = new Cliente("20-87654321-7", "Cliente A");
		Cliente cliente2 = new Cliente("20-12345678-9", "Cliente B");
		tienda.agregarCliente(cliente1);
		tienda.agregarCliente(cliente2);
		List<Cliente> clientes = tienda.obtenerClientesOrdenadosPorRazonSocialDescendente();
		assertEquals(cliente2, clientes.get(0));
		assertEquals(cliente1, clientes.get(1));
	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Vendedor vendedor1 = new Vendedor("12345678", "Vendedor 1");
		Vendedor vendedor2 = new Vendedor("87654321", "Vendedor 2");
		Cliente cliente = new Cliente("20-87654321-7", "Cliente Test");
		Venta venta1 = new Venta("V001", cliente, vendedor1);
		Venta venta2 = new Venta("V002", cliente, vendedor2);
		try {
			tienda.agregarVenta(venta1);
			tienda.agregarVenta(venta2);
		} catch (VendedorDeLicenciaException e) {
			e.printStackTrace();
		}
		Map<Vendedor, Set<Venta>> ventasPorVendedor = tienda.obtenerVentasPorVendedor();
		Set<Venta> ventasVendedor1 = ventasPorVendedor.get(vendedor1);
		Set<Venta> ventasVendedor2 = ventasPorVendedor.get(vendedor2);
		if (ventasVendedor1 != null) {
			assertTrue(ventasVendedor1.contains(venta1));
		}
		if (ventasVendedor2 != null) {
			assertTrue(ventasVendedor2.contains(venta2));
		}
	}


	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Servicio servicio = new Servicio(1, "Servicio Test", 100.0, "01/01/2023", "31/12/2023");
		tienda.agregarServicio(servicio);
		Vendedor vendedor = new Vendedor("12345678", "Vendedor Test");
		Cliente cliente = new Cliente("20-87654321-7", "Cliente Test");
		Venta venta = new Venta("V001", cliente, vendedor);
		venta.agregarRenglon(servicio, 1);
		try {
			tienda.agregarVenta(venta);
		} catch (VendedorDeLicenciaException e) {
			e.printStackTrace();
		}
		Double total = tienda.obtenerTotalDeVentasDeServicios();
		assertEquals(100.0, total, 0.01);
	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() {
		Tienda tienda = new Tienda("30-12345678-9", "Tienda Test");
		Producto producto = new Producto(1, "Producto Test", 100.0, 10);
		tienda.agregarProducto(producto, 10);
		Vendedor vendedor = new Vendedor("12345678", "Vendedor Test");
		Cliente cliente = new Cliente("20-87654321-7", "Cliente Test");
		Venta venta = new Venta("V001", cliente, vendedor);
		venta.agregarRenglon(producto, 1);
		try {
			tienda.agregarVenta(venta);
		} catch (VendedorDeLicenciaException e) {
			e.printStackTrace();
		}
		Integer stock = tienda.getStock(producto);
		assertEquals(9, stock.intValue());
	}


}
