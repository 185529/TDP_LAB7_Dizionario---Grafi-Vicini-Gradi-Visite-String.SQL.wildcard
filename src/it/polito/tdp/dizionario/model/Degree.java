package it.polito.tdp.dizionario.model;

public class Degree {

	private String vertex;
	private Integer degree;
	
	/**
	 * 
	 */
	public Degree() {
		super();
	}

	/**
	 * @param vertex
	 * @param degree
	 */
	public Degree(String vertex, Integer degree) {
		super();
		this.vertex = vertex;
		this.degree = degree;
	}

	/**
	 * @return the vertex
	 */
	public String getVertex() {
		return vertex;
	}

	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(String vertex) {
		this.vertex = vertex;
	}

	/**
	 * @return the degree
	 */
	public Integer getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	
	
	
}
