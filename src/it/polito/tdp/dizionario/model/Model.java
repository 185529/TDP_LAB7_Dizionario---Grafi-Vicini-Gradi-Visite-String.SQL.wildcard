package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	private UndirectedGraph<String, DefaultEdge> grafo;

	public void createGraph(int lunghezza) {
		
		WordDAO dao = new WordDAO();
		
		grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(grafo, dao.getAllWordsFixedLength(lunghezza));
		
		for(String v1 : grafo.vertexSet()){
			for(String v2 : grafo.vertexSet()){
				if(!v1.equals(v2)){
					if(notMoreThanOne(v1, v2)){
						grafo.addEdge(v1, v2);
					}
				}
			}
		}
		
		System.out.println(grafo.vertexSet());
		
	}

	private static boolean notMoreThanOne(String first, String second) {
		
		if(first.length() != second.length()){
			throw new RuntimeException("Le due parole hanno una lunghezza diversa.");
		}

		int distance = 1;
		
		for(int i=0; i<first.length(); i++){
			if (first.charAt(i) != second.charAt(i)){
				distance--;
			}
		}

		if (distance == 0)
			return true;
		else
			return false;
		
	}

	public List<String> displayNeighbours(String word) {

		return Graphs.neighborListOf(grafo, word.toLowerCase());
		
	}

	public Degree findMaxDegree() {
		
		Degree degreeMax = new Degree("", Integer.MIN_VALUE);
		
		for(String s : grafo.vertexSet()){
			if(grafo.degreeOf(s) > degreeMax.getDegree()){
				degreeMax.setDegree(grafo.degreeOf(s));
				degreeMax.setVertex(s.toUpperCase());
			}
		}
		
		return degreeMax;
		
	}

	public List<String> getAllNeighbours(String word) {
		
		List<String> visited = new ArrayList<String>();
		BreadthFirstIterator<String, DefaultEdge> bfv = new BreadthFirstIterator<String, DefaultEdge>(grafo, word);
		
		while(bfv.hasNext()){
			visited.add(bfv.next());
		}
		
		return visited;
		
	}
}
