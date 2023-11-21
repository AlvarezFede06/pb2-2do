package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlanificadorDeEventos {

	private Set<Usuario> usuarios;
	private Map<String, Evento> eventos;

	public PlanificadorDeEventos() {
		super();
		this.usuarios = new HashSet<>();
		this.eventos = new HashMap<>();
	}

	public void add(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public Usuario getUsuario(String mail) {
		for (Usuario usuario : usuarios) {

			if (usuario.getMail().equals(mail))
				return usuario;

		}
		return null;
	}

	public void crear(Usuario organizador, Evento evento) {
		evento.setOrganizador(organizador);

		this.eventos.put(evento.getNombreEvento(), evento);

	}

	public void crear(Usuario organizador, String nombreDelEvento) {
		Evento evento = new Evento(nombreDelEvento);

		crear(organizador, evento);

	}

	public Integer getCantidadDeUsuarios() {
		// TODO Auto-generated method stub
		return this.usuarios.size();
	}

	public Integer getCantidadDeEventos() {
		return eventos.size();
	}

	public Integer getCantidadDeCumpleanios() {

		Integer contCumple = 0;

		for (Map.Entry<String, Evento> entry : eventos.entrySet()) {
			String key = entry.getKey();
			Evento val = entry.getValue();

			if (val instanceof Cumple) {

				contCumple++;
			}
		}
		return contCumple;
	}

	public Integer getCantidadDeCasamientos() {

		return getCantidadDeEventos() - getCantidadDeCumpleanios();
	}

	public Evento getEvento(String nombreDelEvento) {

		return eventos.get(nombreDelEvento);
	}

	public void invitar(Evento evento, Invitado invitado) {
		evento.addInvitado(invitado);
	}

	public Integer getCantidadDeInvitados() {
		Integer contInvitados = 0;

		for (Usuario usuario : usuarios) {

			if (usuario instanceof Invitado) {
				contInvitados++;
			}
		}

		return contInvitados;
	}

	public void confirmar(Evento evento, Usuario invitado) throws UsuarioNoInvitadoException {
		Usuario encontrado = buscarInvitado(invitado);

		if (encontrado == null) {
			throw new UsuarioNoInvitadoException("EL USUARIO NO ESTA INVITADO");
		}

		((Invitado) encontrado).setAsistencia(true);

	}

	private Usuario buscarInvitado(Usuario invitado) {

		for (Usuario usuario : usuarios) {
			if (usuario instanceof Invitado && usuario.getMail().equals(invitado.getMail())) {
				return usuario;
			}
		}
		return null;

	}

	public Integer getCantidadDeInvitadosConfirmados() {
		Integer contInvitadosConfirmados = 0;

		for (Usuario usuario : usuarios) {

			if (usuario instanceof Invitado && ((Invitado) usuario).isAsistencia().equals(true)) {
				contInvitadosConfirmados++;
			}
		}
		return contInvitadosConfirmados;
	}



	public List<Invitado> obtenerListaDeInvitadosOrdenadaPorNombre(Evento evento) {
		List<Invitado> invitadosOrdenados = new ArrayList<>();

		invitadosOrdenados = evento.getInvitados();
		
		Collections.sort(invitadosOrdenados);
		return invitadosOrdenados;
		
	}

}
