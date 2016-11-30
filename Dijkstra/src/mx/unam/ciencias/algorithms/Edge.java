package mx.unam.ciencias.algorithms;

/**
 * Edge class. Directed edges for Weighted Graphs ({@link WGraph}).
 */
public class Edge {

	/** NIL_EDGE constant. */
	public static final Edge NIL_EDGE = null;

	/**
	 * Initial vertex. That is, if the edge e = (u,v)
	 * then v_i = u.
	 */
	public Vertex v_i;
	/**
	 * Final vertex. That is, if the edge e = (u,v)
	 * then v_f = v.
	 */
	public Vertex v_f;
	/**
	 * Cost or weight.
	 */
	public int cost;

	/**
	 * Creates a new edge <strong>e</strong> from vertex i to vertex f with
	 * weight(e) = 1.
	 * @param i initial vertex.
	 * @param f final vertex.
	 */
	public Edge (Vertex i, Vertex f) {
		this(i, f, 1);
	}

	/**
	 * Creates a new edge <strong>e</strong> from vertex i to f with
	 * weight(e) = cost.
	 * @param i initial vertex.
	 * @param f final vertex.
	 * @param cost the cost/weight.
	 */
	public Edge (Vertex i, Vertex f, int cost) {
		v_i = i;
		v_f = f;
		this.cost = cost;
	}
	/**
	 * String representation for edges. If the edge has the form (u,v) with 
	 * weight w, then the string representation is (v)--w-->(v).
	 * @return The string representation.
	 */
	@Override public String toString () {
		return v_i.toString() + "--" + cost + "-->" + v_f.toString();
	}

}