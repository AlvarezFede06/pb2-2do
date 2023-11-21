package ar.edu.unlam.pb2.Parcial01;

public class Producto implements Vendible {
	
	private Integer codigo;
	private String nombre;
	private Double precio;
	private Integer puntoDeReposicion;
	
	public Producto(Integer codigo, String nombre, Double precio, Integer puntoDeReposicion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.puntoDeReposicion = puntoDeReposicion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getPuntoDeReposicion() {
		return puntoDeReposicion;
	}

	public void setPuntoDeReposicion(Integer puntoDeReposicion) {
		this.puntoDeReposicion = puntoDeReposicion;
	}
}