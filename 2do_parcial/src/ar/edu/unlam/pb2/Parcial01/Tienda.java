package ar.edu.unlam.pb2.Parcial01;

import java.util.*;

public class Tienda {

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock;
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;

	public Tienda(String cuit, String nombre) {
		this.cuit = cuit;
		this.nombre = nombre;
		this.vendibles = new HashSet<>();
		this.stock = new HashMap<>();
		this.clientes = new ArrayList<>();
		this.ventas = new HashSet<>();
		this.vendedores = new HashSet<>();
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Vendible> getVendibles() {
		return vendibles;
	}

	public void setVendibles(Set<Vendible> vendibles) {
		this.vendibles = vendibles;
	}

	public Map<Producto, Integer> getStock() {
		return stock;
	}

	public void setStock(Map<Producto, Integer> stock) {
		this.stock = stock;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(Set<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public Vendible getVendible(Integer codigo) {
		for (Vendible vendible : vendibles) {
			if (vendible.getCodigo().equals(codigo)) {
				return vendible;
			}
		}
		return null;
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		vendibles.add(producto);
		stock.put(producto, stockInicial);
	}

	public void agregarServicio(Servicio servicio) {
		vendibles.add(servicio);
	}

	public Integer getStock(Producto producto) {
		return stock.get(producto);
	}

	public void agregarStock(Producto producto, Integer incremento){
		Integer currentStock = stock.get(producto);
		if (currentStock != null) {
			stock.put(producto, currentStock + incremento);
		}
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarVendedor(Vendedor vendedor) {
		vendedores.add(vendedor);
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaException {
		if (venta.getVendedor().isDeLicencia()) {
			throw new VendedorDeLicenciaException("El vendedor está de licencia");
		}
		for (Map.Entry<Vendible, Integer> entry : venta.getRenglones().entrySet()) {
			if (entry.getKey() instanceof Producto) {
				Producto producto = (Producto) entry.getKey();
				Integer cantidad = entry.getValue();
				Integer stockActual = stock.get(producto);
				if (stockActual != null) {
					stock.put(producto, stockActual - cantidad);
				}
			}
		}
		ventas.add(venta);
	}


	public Producto obtenerProductoPorCodigo(Integer codigo) {
		for (Vendible vendible : vendibles) {
			if (vendible instanceof Producto && vendible.getCodigo().equals(codigo)) {
				return (Producto) vendible;
			}
		}
		return null;
	}

	public void agregarProductoAVenta(String codigoVenta, Producto producto) throws VendibleInexistenteException {
		Venta venta = obtenerVentaPorCodigo(codigoVenta);
		if (venta != null && vendibles.contains(producto)) {
			venta.agregarRenglon(producto, 1);
			Integer currentStock = stock.get(producto);
			if (currentStock != null) {
				stock.put(producto, currentStock - 1);
			}
		} else {
			throw new VendibleInexistenteException("El producto no existe");
		}
	}


	public void agregarServicioAVenta(String codigoVenta, Servicio servicio) {
		Venta venta = obtenerVentaPorCodigo(codigoVenta);
		if (venta != null && vendibles.contains(servicio)) {
			venta.agregarRenglon(servicio, 1);
		}
	}

	private Venta obtenerVentaPorCodigo(String codigoVenta) {
		for (Venta venta : ventas) {
			if (venta.getCodigo().equals(codigoVenta)) {
				return venta;
			}
		}
		return null;
	}


	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		List<Producto> productos = new ArrayList<>();
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			if (entry.getValue() <= entry.getKey().getPuntoDeReposicion()) {
				productos.add(entry.getKey());
			}
		}
		return productos;
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		List<Cliente> sortedClientes = new ArrayList<>(clientes);
		sortedClientes.sort((cliente1, cliente2) -> cliente2.getRazonSocial().compareTo(cliente1.getRazonSocial()));
		return sortedClientes;
	}

	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {
		Map<Vendedor, Set<Venta>> ventasPorVendedor = new HashMap<>();
		for (Vendedor vendedor : vendedores) {
			Set<Venta> ventasDelVendedor = new HashSet<>();
			for (Venta venta : ventas) {
				if (venta.getVendedor().equals(vendedor)) {
					ventasDelVendedor.add(venta);
				}
			}
			ventasPorVendedor.put(vendedor, ventasDelVendedor);
		}
		return ventasPorVendedor;
	}

	public Double obtenerTotalDeVentasDeServicios() {
		Double total = 0.0;
		for (Venta venta : ventas) {
			for (Map.Entry<Vendible, Integer> entry : venta.getRenglones().entrySet()) {
				if (entry.getKey() instanceof Servicio) {
					total += entry.getKey().getPrecio() * entry.getValue();
				}
			}
		}
		return total;
	}
}