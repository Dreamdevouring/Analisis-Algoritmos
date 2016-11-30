package mx.unam.ciencias.algorithms;

import mx.unam.ciencias.edd.Lista;

/**
 * Directed Weighted Graphs.
 * A graph G = (V,E) is represented as follows:
 * 1. The set of vertices with a list of objects Vertex.
 * 2. The set of edges with a list of objects Edge.
 * 3. Each vertex has a list of edges representing its neighborhood.
 */
public class WGraph {

	//Set of vertices
	private Lista<Vertex> vertices;
	//Set of edges
	private Lista<Edge> edges;
	//Lists of adjacencies for each vertex in graph
	private Lista<Edge>[] adjacencies;
	/** Number of vertices. */
	public int n;
	/** Number of edges. */
	public int m;

	/*
	 * Dirty trick for creating generic Arrays. "Generic" classes don't know
	 * the type argument (Edge in this case) they were created with at run
	 * time (bullsh*t), so we create an array of Lista (raw type) and use
	 * casting. SuppressWarnings for "unchecked" type conversion in order for
	 * this thing to work (don't worry Java, I know what I'm doing).
	 * See Type Erasure: http://docs.oracle.com/javase/tutorial/java/generics/erasure.html
	 * and Raw Types: http://docs.oracle.com/javase/tutorial/java/generics/rawTypes.html
	 * for more information.
	 */
	@SuppressWarnings("unchecked") private void initializeLists (int size) {
		//Downcasting(?)
		adjacencies = (Lista<Edge>[]) new Lista[n];
	}

	/**
	 * Given a set of vertices and a set of edges, constructs a new
	 * Weighted Graph and initializes the adjacencies lists.
	 * @param vertices The set of vertices.
	 * @param edges The set of edges.
	 */
	public WGraph (Lista<Vertex> vertices, Lista<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
		n = vertices.getLongitud();
		m = edges.getLongitud();
		initializeLists(n);
		for (int i = 0; i < n; i++)
			adjacencies[i] = new Lista<>();
		for (Edge e : edges) {
			Vertex v_i = e.v_i;
			adjacencies[v_i.label].agrega(e);
		}
	}

	/**
	 * Given a vertex v, returns the adjacencies list of v
	 * in graph.
	 * @param v The vertex.
	 * @return the adjacencies list of v.
	 */
	public Lista<Edge> getAdjacencies (Vertex v) {
		return adjacencies[v.label];
	}

	/**
	 * Returns the set of vertices.
	 * @return the set of vertices.
	 */
	public Lista<Vertex> getVertices () {
		return vertices;
	}

	/**
	 * Returns the set of edges.
	 * @return the set of edges.
	 */
	public Lista<Edge> getEdges () {
		return edges;
	}
	
	/**
	 * Naive implementation for retrieving the set of edges in the
	 * minimum spanning tree of the graph.
	 * @return The set of edges in the minimum spanning tree.
	 */
	public Lista<Edge> minimumSpanningTree () {
		Lista<Edge> mste = new Lista<>();
		for (Vertex v : vertices) {
			Vertex p = v.parent;
			if (p == Vertex.NIL_VERTEX) //Initial vertex
				continue;
			//It's easier to do this, besides, I was running out of time
			mste.agrega(new Edge(p,v,v.dist));
		}
		return mste;
	}
	
}