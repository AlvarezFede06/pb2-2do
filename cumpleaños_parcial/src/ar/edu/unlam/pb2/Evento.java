package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Evento {
	
	private String nombreEvento; 
	private Usuario organizador;
	private List<Agasajado> agasajados;
	private List<Invitado> invitados;
	
	public Evento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
		this.agasajados = new LinkedList<>();
		this.invitados = new LinkedList<>();
	} 
	
	public void add(Agasajado agasajado) {
		agasajados.add(agasajado);
	}
	
	public void addInvitado(Invitado invitado) {
		invitados.add(invitado);
	}

	public Usuario getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Usuario organizador) {
		this.organizador = organizador;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public List<Invitado> getInvitados() {
		return invitados;
	}

	public void setInvitados(List<Invitado> invitados) {
		this.invitados = invitados;
	}

	


	

}
