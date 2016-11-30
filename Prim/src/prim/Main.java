package prim;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.algorithms.*;
import java.io.*;

/**
 * The main Class for Project: Práctica 3: Árboles de expansión
 * (Spanning Trees).
 * @author Gilberto Isaac López García
 */
public class Main {

    /**
	 * Creates a graph with the information in the text file given with the
	 * command line, runs Prim's algorithm over the graph an prints the edges
	 * in the resulting tree.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		//The set of vertices.
		Lista<Vertex> vertices = new Lista<>();
		//The set of edges.
		Lista<Edge> edges = new Lista<>();
		//Initial vertex for Prim's Algorithm
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
				//Undirected graph
				if (edge.length == 2) {
					edges.agrega(new Edge(u, v));
					edges.agrega(new Edge(v, u));
				} else {
					//Weight
					int w = Integer.parseInt(edge[2]);
					edges.agrega(new Edge(u, v, w));
					edges.agrega(new Edge(v, u, w));
				}
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Error:\n" + e.getMessage());
		}
		//The graph
		WGraph g = new WGraph(vertices, edges);
		Prim.Prim(g, a);
		//Tree edges
		Lista<Edge> mst = g.minimumSpanningTree();
		//Prints edges
		for (Edge e : mst)
			System.out.println(e);
    }
}
