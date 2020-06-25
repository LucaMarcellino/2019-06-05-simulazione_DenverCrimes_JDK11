package it.polito.tdp.denvercrimes.model;

import java.time.LocalDateTime;

public class Evento implements Comparable<Evento> {
	public enum TipoEvento{
		CRIMINE,ARRIVO_AGENTE,GESTITO
	}

	private TipoEvento tipo;
	private LocalDateTime data;
	private Event evento;
	public Evento(TipoEvento tipo, LocalDateTime data, Event evento) {
		super();
		this.tipo = tipo;
		this.data = data;
		this.evento = evento;
	}
	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}
	public Event getEvento() {
		return evento;
	}
	public void setEvento(Event evento) {
		this.evento = evento;
	}
	public LocalDateTime getData() {
		return data;
	}
	@Override
	public int compareTo(Evento o) {
		return this.data.compareTo(o.getData());
	}
	
	
	
}
