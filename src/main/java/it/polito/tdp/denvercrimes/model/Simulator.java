package it.polito.tdp.denvercrimes.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.denvercrimes.db.EventsDAO;
import it.polito.tdp.denvercrimes.model.Evento.TipoEvento;



public class Simulator {
	
	//Variabili del mondo
	private Graph<Integer,DefaultWeightedEdge> grafo;
	private EventsDAO dao;
	private Map<Integer, Integer> agenti;
	
	//Parametri simulazione
	private int numeroAgenti=0;
	
	
	
	//Risultati da calcolare
	private int malGestiti;
	
	
	//Coda degli eventi 
	private PriorityQueue<Evento> coda;
	
	public void init(int n,int anno , int mese, int giorno,  Graph<Integer,DefaultWeightedEdge> grafo ) {
		this.numeroAgenti=n;
		this.dao=new EventsDAO();
		this.agenti= new HashMap<Integer, Integer>();
		this.malGestiti=0;
		this.grafo=grafo;
		for(int i=0; i<numeroAgenti;i++) {
			agenti.put(i,dao.getDistrettoStazione(anno));
		}
		this.coda= new PriorityQueue<Evento>();
		for(Event e: dao.getEventByDay(anno, mese, giorno)) {
			Evento e2= new Evento(TipoEvento.CRIMINE, e.getReported_date(), e);
			coda.add(e2);
			}
	}
	
	public void run() {
		while(!coda.isEmpty()) {
			Evento e = coda.poll();
			processEvent(e);
		}
	}

	private void processEvent(Evento e) {
		switch (e.getTipo()) {
		case CRIMINE:
			break;
		case ARRIVO_AGENTE:
			break;
		case GESTITO:
			break;
		}
		
	}
	

	
	
	
	
	
	
	
	
	
}
