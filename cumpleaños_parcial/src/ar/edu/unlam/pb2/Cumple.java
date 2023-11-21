package ar.edu.unlam.pb2;

public class Cumple extends Evento{
	
	private final static String CUMPLE_NAME = "El cumple de ";

	public Cumple(Agasajado agasajado) {
		super(CUMPLE_NAME + agasajado.getNombre());
		this.add(agasajado);
	}



	

}
