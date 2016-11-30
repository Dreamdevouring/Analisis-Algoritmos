package mx.unam.ciencias.algorithms;

import mx.unam.ciencias.edd.Lista;

/**
 * Dijkstra's Algorithm for finding the Shortest Path Tree in a Directed
 * Weighted Graph with non negative weights.
 * Description:
 * 1. Assign to every node, except the initial node s, a distance of Infinity.
 *    Assign initial node distance to 0. Also assign every node parent to
 *    NIL_VERTEX and mark every node as not visited.
 * 2. Insert every node to the Queue.
 * 3. While the queue is not empty:
 *    a. Extract the minimum element u.
 *    b. For each neighbor w of u, if w hasn't been visited and the distance
 *       from s to u plus the weight of (u,w) is lesser than the saved distance
 *       from s to w, set w's parent as u and its distance as distance(s,u)
 *       + weight(u,w) because we have discovered a new shortest path from s to
 *       w.
 *    c. Mark u as visited.
 * 4. Retrieve the shortest path tree following the edges (v.parent,v) for each
 *    vertex in V\{s}.
 * <strong>Data Structure:</strong> Binomial Min Heap based Priority Queue.
 * <strong>Time complexity:</strong> O(m log n).
 * @author Gilberto Isaac López García.
 */
public class Dijkstra {
	
	//Binomial Heap based Priority Queue.
	private static BinomialHeap<Vertex> Q;

	/**
	 * Runs Dijkstra's algorithm over the graph g starting from vertex s.
	 * @param g The graph.
	 * @param s The vertex.
	 */
	public static void Dijkstra (WGraph g, Vertex s) {
		Q = new BinomialHeap<>(g.n);
		//Initial vertex distance
		s.dist = 0;
		for (Vertex v : g.getVertices()) {
			if (v != s)
				v.dist = Integer.MAX_VALUE;
			v.visit = false;
			v.parent = Vertex.NIL_VERTEX;
			Q.insert(v);
		}
		while (!Q.isEmpty()) {
			Vertex u = Q.deleteMinimum();
			Lista<Edge> lu = g.getAdjacencies(u);
			for (Edge e : lu) {
				Vertex w = e.v_f;
				//nvaD = d(s,u) + weight(u,w)
				int nvaD = e.cost + u.dist;
				//If nvaD < w.dist then the path {s,...,u}U{u,w} is shorter
				//than the path {s,...,w} already calculated.
				if (!w.visit && nvaD < w.dist) {
					w.parent = u;
					Q.decreaseKey(w, nvaD);
				}
			}
			//Never visits u again.
			u.visit = true;
		}
	}
	
}
