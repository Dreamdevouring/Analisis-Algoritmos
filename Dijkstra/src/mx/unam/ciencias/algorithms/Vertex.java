package mx.unam.ciencias.algorithms;

import mx.unam.ciencias.edd.ComparableIndexable;

/**
 * Vertex class. Vertices for Weighted Graphs ({@link WGraph}).
 */
public class Vertex implements ComparableIndexable<Vertex>{

	/** NIL_VERTEX constant. */
	public static final Vertex NIL_VERTEX = null;

	/* ComparableIndexable index. */
	private int index;
	/** Label: Integer number. */
	public int label;
	/** Visit: True if already visited, false otherwise. */
	public boolean visit;
	/** InTree: True if it's in tree, false otherwise. */
	public boolean inTree;
	/** InQ: True if it's in queue Q, false otherwise. */
	public boolean inQ;
	/** Parent: The parent vertex in tree. */
	public Vertex parent;
	/** Dist: Distance. */
	public int dist;

	/**
	 * Creates a new Vertex with the given label.
	 * @param label The label.
	 */
	public Vertex (int label) {
		this.label = label;
	}

	/**
	 * Returns the vertex index. See {@link ComparableIndexable#getIndice} for
	 * more information.
	 * @return the index.
	 */
	@Override public int getIndice () {
		return index;
	}

	/**
	 * Sets the index of this vertex. See {@link ComparableIndexable#setIndice}
	 * for more information.
	 * @param index The new index.
	 */
	@Override public void setIndice (int index) {
		this.index = index;
	}

	/**
	 * Compares the vertex <i>this</i> with vertex <i>v</i>.
	 * Compares the vertices using dist as key.
	 * @param v The vertex to compare.
	 * @return 0 if both vertices have the same key, > 0 if
	 *         this.dist is greater than v.dist, &lt; 0 otherwise.
	 */
	@Override public int compareTo (Vertex v) {
		return this.dist - v.dist;
	}
	
	/**
	 * String representation for vertices. If the vertex has label n, then the
	 * string representation is (n).
	 * @return The string representation.
	 */
	@Override public String toString () {
		return "(" + label + ")";
	}

	/**
	 * Changes the vertex key.
	 * @param llave The new key.
	 */
	@Override public void setLlave(int llave) {
		dist = llave;
	}

	/**
	 * Returns the vertex key.
	 * @return The key.
	 */
	@Override public int getLlave() {
		return dist;
	}

}