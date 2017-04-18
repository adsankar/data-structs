package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * This file contains the methods for the graph
 * @author Aleksander Sankar
 */
public class Graph<V extends Comparable<V>> {
	// stores a sorted list of nodes
	private TreeSet<GraphNode<V>> nodes;
	// the adjacency list maintains order through the properties of a tree set
	private ArrayList<TreeSet<Edge<V>>> adjList;

	// inner class which holds information about each vertex in the graph
	private class GraphNode<D extends Comparable<D>> implements Comparable<D> {
		private D vertex;

		// constructs a new node
		public GraphNode(D vertex) {
			this.vertex = vertex;
		}

		@Override
		public int compareTo(D o) {
			return o.compareTo(vertex);
		}

	}

	// inner class which holds information about each edge between vertices
	private class Edge<D extends Comparable<D>> implements Comparable<D> {
		private D source;
		private D dest;
		private int weight;

		// constructs an edge between two vertices and gives it a weight
		public Edge(D source, D dest, int weight) {
			this.source = source;
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public int compareTo(D o) {
			return o.compareTo(dest);
		}

	}

	/**
	 * Default constructor which initializes the objects that store the graph
	 * information
	 */
	public Graph() {
		nodes = new TreeSet<GraphNode<V>>();
		adjList = new ArrayList<TreeSet<Edge<V>>>();

	}

	/**
	 * Adds a vertex to the current graph
	 * 
	 * @param vertex
	 * @return true if we can add it, false if the vertex is already there
	 */
	public boolean addVertex(V vertex) {
		for (GraphNode<V> x : nodes) {
			// loop through existing vertices to check if the vertex is already
			// present
			if (x.vertex.compareTo(vertex) == 0)
				return false;
		}
		// if not present, add it and add a row on the adjacency list
		nodes.add(new GraphNode<V>(vertex));
		adjList.add(new TreeSet<Edge<V>>());
		return true;
	}

	/**
	 * Checks if the vertex is in the graph or not
	 * 
	 * @param vertex
	 * @return true if it is there, false if not
	 */
	public boolean hasVertex(V vertex) {
		for (GraphNode<V> x : nodes) {
			// loop through checking for the specified vertex
			if (x.vertex.compareTo(vertex) == 0)
				return true;
		}
		return false; // if we get here, the vertex is not in the current graph
	}

	/**
	 * Returns a list of all of the vertices in the graph
	 * 
	 * @return an ArrayList containing all of the vertices
	 */
	public Collection<V> getVertices() {
		ArrayList<V> x = new ArrayList<V>();
		for (GraphNode<V> g : nodes)
			x.add(g.vertex);
		return x;
	}

	/**
	 * Removes a vertex from the current graph
	 * 
	 * @param vertex
	 * @return true if removed successfully, false if not
	 */
	public boolean removeVertex(V vertex) {
		for (GraphNode<V> g : nodes) {
			if (g.vertex.compareTo(vertex) == 0) {
				nodes.remove(g);// remove the vertex from the vertex list
				for (TreeSet<Edge<V>> t : adjList) {
					// loop through and remove all edges using that vertex
					for (Iterator<Edge<V>> e = t.iterator(); e.hasNext();) {
						Edge<V> w = e.next();
						if (w.source.compareTo(vertex) == 0
								|| w.dest.compareTo(vertex) == 0)
							e.remove();
					}
				}
				// remove any rows on the adjacency list if they are now empty
				for (Iterator<TreeSet<Edge<V>>> e = adjList.iterator(); e
						.hasNext();) {
					TreeSet<Edge<V>> t = e.next();
					if (t.size() == 0)
						e.remove();
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds an edge given the source, destination, and weight of the edge
	 * 
	 * @param source
	 * @param dest
	 * @param cost
	 * @return true if added successfully, false if not
	 */
	public boolean addEdge(V source, V dest, int cost) {
		if (cost < 0)
			return false;
		// if the source or destination is not in the graph, add the vertex
		if (!hasVertex(source))
			addVertex(source);
		if (!hasVertex(dest))
			addVertex(dest);
		Edge<V> e = new Edge<V>(source, dest, cost);

		for (TreeSet<Edge<V>> t : adjList) {
			// find the correct row of the adjacency list
			if (t.size() == 0 || t.first().source.compareTo(source) == 0) {
				for (Iterator<Edge<V>> m = t.iterator(); m.hasNext();) {
					Edge<V> w = m.next();
					if (w.source.compareTo(source) == 0
							&& w.dest.compareTo(dest) == 0) {
						m.remove();//remove and replace duplicate edge
						t.add(e);
						return true;

					}

				}
				t.add(e);
				return true;
			}

		}

		return true;
	}

	/**
	 * Adds an edge of weight 1 to the graph
	 * 
	 * @param source
	 * @param dest
	 */
	public void addEdge(V source, V dest) {
		// add appropriate vertices if necessary
		if (!hasVertex(source))
			addVertex(source);
		if (!hasVertex(dest))
			addVertex(dest);

		Edge<V> e = new Edge<V>(source, dest, 1);

		for (TreeSet<Edge<V>> t : adjList) {
			// find the correct row of the adjacency list
			if (t.size() == 0 || t.first().source.compareTo(source) == 0) {
				for (Iterator<Edge<V>> m = t.iterator(); m.hasNext();) {
					Edge<V> w = m.next();
					if (w.source.compareTo(source) == 0
							&& w.dest.compareTo(dest) == 0) {
						m.remove();
						t.add(e);
						return;
					}
				}
				t.add(e);// add the new edge

				return;
			}
		}
	}

	/**
	 * Prints out the contents of the adjacency list for testing purposes
	 */
	public void printData() {
		for (TreeSet<Edge<V>> v : adjList) {
			for (Edge<V> e : v) {
				System.out.print(e.source.toString() + "," + e.dest.toString()
						+ "=" + e.weight + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Retrieves the edge between the source and destination vertices
	 * 
	 * @param source
	 * @param dest
	 * @return the weight of that edge
	 */
	public int getEdge(V source, V dest) {
		for (TreeSet<Edge<V>> t : adjList) {
			// loop through to find the edge
			for (Edge<V> w : t) {
				if (w.source.compareTo(source) == 0
						&& w.dest.compareTo(dest) == 0)
					return w.weight;// return its weight
			}
		}
		return -1;// if the edge is not in the graph
	}

	/**
	 * Removes the edge between the source and destination vertices
	 * 
	 * @param source
	 * @param dest
	 * @return true if removed successfully, false if not
	 */
	public boolean removeEdge(V source, V dest) {
		for (TreeSet<Edge<V>> t : adjList) {
			for (Iterator<Edge<V>> m = t.iterator(); m.hasNext();) {
				Edge<V> w = m.next();
				if (w.source.compareTo(source) == 0
						&& w.dest.compareTo(dest) == 0) {
					m.remove();//remove the edge
					for (Iterator<TreeSet<Edge<V>>> s = adjList.iterator(); s
							.hasNext();) {
						TreeSet<Edge<V>> j = s.next();
						if (j.size() == 0) {
							s.remove();//removes the row in the adjacency list
							//if it is empty
							return true;
						}
					}

					return true;
				}
			}

		}

		return false;// if the edge was not found
	}

	/**
	 * Returns a collection (in this case an ArrayList) of all of the neighbors
	 * of a vertex
	 * 
	 * @param vertex
	 * @return an ArrayList containing all of the neighbors
	 * @throws IllegalArgumentException
	 *             if the vertex is not in the graph
	 */
	public Collection<V> getNeighbors(V vertex) throws IllegalArgumentException {
		if (!hasVertex(vertex))
			throw new IllegalArgumentException();
		ArrayList<V> x = new ArrayList<V>();
		for (TreeSet<Edge<V>> t : adjList) {
			if (t.size() == 0 || t.first().source.compareTo(vertex) == 0) {
				// loops through each edge to find the neighbors
				for (Edge<V> w : t) {
					x.add(w.dest);
				}
				return x;
			}
		}
		return x;
	}

	/**
	 * Performs a breadth first search on the graph given the starting vertex
	 * 
	 * @param source
	 * @return an ArrayList representing the path taken in the search
	 * @throws IllegalArgumentException
	 *             if the source is not in the graph
	 */
	public List<V> bfs(V source) throws IllegalArgumentException {
		if (!hasVertex(source))
			throw new IllegalArgumentException();
		ArrayList<V> x = new ArrayList<>();
		Set<V> visited = new HashSet<V>();
		Queue<V> q = new LinkedList<V>();
		visited.add(source);
		q.add(source);
		while (!q.isEmpty()) {
			V w = q.remove();
			x.add(w);
			for (V u : getNeighbors(w)) {
				if (!visited.contains(u)) {
					visited.add(u);
					q.add(u);
				}
			}
		}
		return x;
	}

	/**
	 * Performs a depth first search on the graph given the starting vertex
	 * 
	 * @param source
	 * @return an ArrayList representing the path taken in the search
	 * @throws IllegalArgumentException
	 *             if the source is not in the graph
	 */
	public List<V> dfs(V source) throws IllegalArgumentException {
		if (!hasVertex(source))
			throw new IllegalArgumentException();
		ArrayList<V> x = new ArrayList<>();
		Stack<V> q = new Stack<V>();
		Set<V> visited = new HashSet<V>();
		q.push(source);
		visited.add(source);
		while (!q.isEmpty()) {

			V v = q.pop();
			x.add(v);
			if (!visited.contains(v)) {
				visited.add(v);

				for (V u : getNeighbors(v)) {
				
						q.push(u);
				}
			}

		}
		return x;
	}

	/**
	 * Contracts two vertices in the graph by combining them and combining their
	 * edges
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @return true if completed successfully, false if not
	 */
	public boolean contractEdge(V vertex1, V vertex2) {

		return true;
	}

}// end class