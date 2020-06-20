package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.Adiacenza;
import it.polito.tdp.food.db.FoodDao;

public class Model {
	SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	
	FoodDao dao;
	List<String> best;
	double pesoBest;
	
	public Model() {
	
		this.dao= new FoodDao();
	
	}
	
	public void creaGrafo(int c) {
		grafo= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.listVertici(c));

		for(Adiacenza a: dao.listAdiacenze(c)) {
			if(grafo.containsVertex(a.getP1()) && grafo.containsVertex(a.getP2())  ) {
			Graphs.addEdge(grafo, a.getP1(), a.getP2(), a.getPeso());
			}
		}
		
	}
	public List<String> listVertici(int c){
	return	this.dao.listVertici(c);
	}
	public int vertici() {
		return this.grafo.vertexSet().size();
	}
	public int archi() {
	 return this.grafo.edgeSet().size();
	}
	
	public Map<String, Double> connessi(String s){
		if(grafo!=null) {
		List<String> porzioni= Graphs.neighborListOf(grafo, s);
		Map<String,Double> result= new HashMap<>();
		for(String c: porzioni) {
			result.put(c, grafo.getEdgeWeight(grafo.getEdge(s, c)));
		}
		return result;
		}else { 
			return null;
		}
	}
	
	public List<String> camminoMinimo(int N, String s){
		best= new ArrayList<>();
		this.pesoBest=-1;
		List<String> parziale= new ArrayList<>();
		parziale.add(s);
		cerca(parziale,N);
		return best;
	}

	private void cerca(List<String> parziale, int n) {
		
		if(n==0) {
			if(calcolaPeso(parziale)>pesoBest) {
				best= new ArrayList<>(parziale);
				this.pesoBest= calcolaPeso(parziale);
			}
			
		}
		
		List<String> vicini= Graphs.neighborListOf(grafo, parziale.get(parziale.size()-1));
			
		for(String s: vicini) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				cerca(parziale, n-1);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}

	private double calcolaPeso(List<String> parziale) {
		double peso=0;
		
		for(int i=1; i<parziale.size();i++) {
			peso+= grafo.getEdgeWeight(grafo.getEdge(parziale.get(i-1), parziale.get(i)));
		}
		return peso;
	}

	public double pesoMax() {
		return this.pesoBest;
		
	}
	

	
}
