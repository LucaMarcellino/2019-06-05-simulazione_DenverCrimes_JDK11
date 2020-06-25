package it.polito.tdp.denvercrimes.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.denvercrimes.db.EventsDAO;

public class Model {
	private EventsDAO dao;
	private Graph<Integer,DefaultWeightedEdge> grafo;
	private List<Integer> anni;
	
	public Model() {
		this.dao= new EventsDAO();
		this.anni= new ArrayList<Integer>(dao.getYears());
	}

	public List<Integer> getAnni() {
		return anni;
	}
	
	public void creaGrafo(int anno) {
		this.grafo= new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getVertex());
		
		for(Integer v1: grafo.vertexSet()) {
			for(Integer v2 : grafo.vertexSet()) {
				if(!v1.equals(v2)) {
					double lat1=dao.getArcoLat(anno, v1);
					double lat2 =dao.getArcoLat(anno, v2);
					
					double lon1= dao.getArcoLong(anno, v1);
					double lon2= dao.getArcoLong(anno, v2);
					
					double distanza = LatLngTool.distance(new LatLng(lat1, lon1), new LatLng(lat2, lon2), LengthUnit.KILOMETER);
					Graphs.addEdge(grafo, v1, v2, distanza);
				}
			}
		}
		System.out.print(" numero vertici " + grafo.vertexSet().size()+ "numero archi " + grafo.edgeSet().size());
	}
	
	public List<Integer> getVertex() {
		List<Integer> vertici= new ArrayList<Integer>(grafo.vertexSet());
		return vertici;
	}
	public int getEdge() {
		return grafo.edgeSet().size();
	}
	
	public List<Vicino> getVicini(int vertice){
		List<Integer> vicini= new ArrayList<Integer>(Graphs.neighborListOf(grafo, vertice));
		List<Vicino> result= new ArrayList<Vicino>();
		double distanza =0.0;
		for(Integer i : vicini) {
			distanza=grafo.getEdgeWeight(grafo.getEdge(vertice,i));
			if(distanza>0) {
				Vicino v= new Vicino(i, distanza);
				result.add(v);
			}
				
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Integer> getMonths(int anno){
		List<Integer> mesi = new ArrayList<Integer>(dao.getMonths(anno));
		return mesi;
	}
	
	public List<Integer> getDay(int anno, int mese){
		List<Integer> giorni = new ArrayList<Integer>(dao.getDay(anno, mese));
		return giorni;
	}

	
	
	
	
	
	
	
	
}
