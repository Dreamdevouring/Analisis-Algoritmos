package dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import mx.unam.ciencias.algorithms.*;
import mx.unam.ciencias.edd.Lista;

/**
 * The main class for Project: Práctica 4: Rutas más cortas (shortest paths).
 * @author gilis
 */
public class Main {

	/**
	 * Creates a graph with the information in the text file given with the
	 * command line, runs Dijkstra's algorithm over the graph an prints the
	 * edges in the resulting shortest path tree.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		//The set of vertices.
		Lista<Vertex> vertices = new Lista<>();
		//The set of edges.
		Lista<Edge> edges = new Lista<>();
		//Initial vertex for Dijkstra's Algorithm
		Vertex a = Vertex.NIL_VERTEX;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(args[0]));
			String line = br.readLine();
			String[] f = line.split("\t");
			int n = Integer.parseInt(f[0]);
			for (int i = 0; i < n; i++)
				vertices.agrega(new Vertex(i));
			a = vertices.get(Integer.parseInt(f[1]));
			while ((line = br.readLine()) != null) {
				String[] edge = line.split("\t");
				//Initial vertex
				int uI = Integer.parseInt(edge[0]);
				//Final vertex
				int vI = Integer.parseInt(edge[1]);
				Vertex u = vertices.get(uI);
				Vertex v = vertices.get(vI);
				//Directed graph
				if (edge.length == 2) {
					edges.agrega(new Edge(u, v));
				} else {
					//Weight
					int w = Integer.parseInt(edge[2]);
					edges.agrega(new Edge(u, v, w));
				}
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Error:\n" + e.getMessage());
		}
		//The graph
		WGraph g = new WGraph(vertices, edges);
		Dijkstra.Dijkstra(g, a);
		//Tree edges
		Lista<Edge> mst = g.shortestPathTree();
		//Prints edges
		for (Edge e : mst)
			System.out.println(e);
	}
	
}
