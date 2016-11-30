package mx.unam.ciencias.algorithms;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.MonticuloMinimo;

/**
 * Prim's Algorithm for finding The Minimum Spanning Tree
 * in a Undirected Connected Weighted Graph (repeat that 8 times quickly).
 * Description:
 * 1. Each vertex is given "Infinity" as initial distance, except the initial
 * vertex a, a has distance 0. Also, each vertex has parent NIL_VERTEX and
 * marked as not visited.
 * 2. Create a Priority Queue with all the vertices.
 * 3. While the queue is not empty:
 *    a. Extract the minimum element v.
 *    b. Add it to the tree.
 *    c. For each neighbor of v, u, if u is still in the queue (not in the tree)
 *       and the weight of (v,u) is lesser than u's distance then set u's
 *       distance to the weight of (v,u) and set u's parent to v.
 *  4. To retrieve the tree, take the edges (v.parent,v) for each vertex in the
 *     graph (except a, whose parent is NIL_VERTEX for being the root), weights
 *     are in v.dist.
 * <strong>Data Structure:</strong> Priority Queue ({@link PriorityQueue}).
 * <strong>Time Complexity:</strong> O(mlogn).
 */
public class Prim {

	/* 
	 * The Min Heap based Priority Queue.
	 * Create -> new MonticuloMinimo<>() O(n) with a "smart" Heap implementation
	 * DeleteMin -> elimina O(logn)
	 * Insert -> agrega O(logn)
	 * DecreaseKey(vertex, new_key) -> first change the key of vertex with
	 * new_key (vertex' key must be grater than new_key), then reordena(vertex)
	 * O(logn)
	 */
	private static MonticuloMinimo<Vertex> Q;

	/**
	 * Runs Prim's algorithm over the Graph g, starting from vertex a.
	 * @param g The Graph.
	 * @param a The vertex.
	 */
	public static void Prim (WGraph g, Vertex a) {
		for (Vertex v : g.getVertices()) {
			//"Initialize" vertices in g
			v.visit = false; //Not visisted yet
			v.parent = Vertex.NIL_VERTEX; //No parent vertex in tree
			v.dist = Integer.MAX_VALUE; //"Infinite" distance
		}
		a.dist = 0; //Initial vertex
		//Create a new Priority Queue with all the vertices
		Q = new MonticuloMinimo<>(g.getVertices());
		while (!Q.esVacio()) { //While not empty
			//Get minimum
			Vertex v = Q.elimina();
			//Then, for each neighbor
			Lista<Edge> lv = g.getAdjacencies(v); //Adjacencies of v in g
			for (Edge e : lv) {
				Vertex w = e.v_f; //Neighbor
				//If w haven't been visited (is still in Q) and e.cost < w.dist
				//then the edge (v,w) is candidate for being in the tree
				if (!w.visit && e.cost < w.dist) {
					//New parent vertex.
					w.parent = v;
					w.dist = e.cost;
					//Reorder heap.
					Q.reordena(w);
				}
			}
			v.visit = true;
		}
	}

}