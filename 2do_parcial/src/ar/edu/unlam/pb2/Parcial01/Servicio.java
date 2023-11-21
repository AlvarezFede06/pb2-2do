package ar.edu.unlam.pb2.Parcial01;

public class Servicio implements Vendible {
	
	private Integer codigo;
	private String nombre;
	private Double precio;
	private String fechaDeInicio;
	private String fechaDeFinalizacion;

	public Servicio(Integer codigo, String nombre, Double precio, String fechaDeInicio, String fechaDeFinalizacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
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

	public String getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public String getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}

	public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
	}
}