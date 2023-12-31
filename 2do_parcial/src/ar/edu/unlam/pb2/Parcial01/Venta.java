package ar.edu.unlam.pb2.Parcial01;

import java.util.HashMap;
import java.util.Map;

public class Venta {

	private String codigo;
	private Cliente cliente;
	private Vendedor vendedor;
	private Map<Vendible, Integer> renglones;

	public Venta(String codigo, Cliente cliente, Vendedor vendedor) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.renglones = new HashMap<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Map<Vendible, Integer> getRenglones() {
		return renglones;
	}

	public void setRenglones(Map<Vendible, Integer> renglones) {
		this.renglones = renglones;
	}

	public void agregarRenglon(Vendible vendible, Integer cantidad) {
		renglones.put(vendible, cantidad);
	}
	
	@Override
	public String toString() {
		return "Venta: " + codigo + "\n" + "Cliente" + cliente + "\n" + "Vendedor" + vendedor + "\n";
	}
}