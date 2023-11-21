package ar.edu.unlam.pb2;

public class Invitado extends Usuario {

	private Boolean asistencia; 
	
	public Invitado(String mail, String nombre, Integer edad) {
		super(mail, nombre, edad);
		this.setAsistencia(false);
	}

	public Boolean isAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Boolean asistencia) {
		this.asistencia = asistencia;
	}

}
