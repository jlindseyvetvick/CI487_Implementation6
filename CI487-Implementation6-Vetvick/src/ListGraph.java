import java.util.*;

public class ListGraph<E> {

	public static class Edge<E> {

		public static final String map = null;
		final int weight;
		final E dest;

		public Edge(E dest) {
			this.dest = dest;
			weight = 0;
		}

		public Edge(E dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}

		public E getDest() {
			return dest;
		}

		public int getWeight() {
			return weight;
		}

		@Override
		public String toString() {
			return String.format("(%s, %s)", dest, weight);
		}

	}

	private final boolean directed;
	private final boolean weighted;
	private Map<E, List<Edge<E>>> map;

	ListGraph(boolean directed, boolean weighted) {
		this.directed = directed;
		this.weighted = weighted;
		map = new HashMap<>();
	}

	public void addVertex(E vertex) {
		map.putIfAbsent(vertex, new LinkedList<>());
	}

	public void removeVertex(E vertex) {
		for (E remainingV : map.keySet()) {
			List<Edge<E>> e = map.get(remainingV);

			for (int i = 0; i < e.size(); i++) {
				if (e.get(i).getDest().equals(vertex)) {
					removeEdge(remainingV, vertex);
				}
			}
		}
		map.remove(vertex);
	}

	public Set<E> getVertecies() {
		return map.keySet();
	}

	public void addEdgeUnweighted(E source, E dest) {
		List<Edge<E>> edges = map.get(source);
		List<Edge<E>> edges1 = map.get(dest);

		for (int i = 0; i < edges.size(); i++) {
			Edge<E> e = edges.get(i);
			if (e.getDest().equals(dest)) {
				return;
			}
		}

		edges.add(new Edge<E>(dest));

		if (directed == false) {
			edges1.add(new Edge<E>(source));

		}
	}

	public void addEdgeWeighted(E source, E dest, int weight) {
		int i = 0;
		List<ListGraph.Edge<E>> edges = map.get(source);
		List<Edge<E>> edges1 = map.get(dest);

		for (; i < edges.size(); i++) {
			ListGraph.Edge<E> e = edges.get(i);
			if (e.getDest().equals(dest)) {
				return;
			}
		}
		edges.add(new Edge<E>(dest, weight));

		if (directed == false) {
			edges1.add(new Edge<E>(source));
		}
	}

	public List<Edge<E>> getEdges(E source) {
		List<ListGraph.Edge<E>> edges = map.get(source);
		return edges;
	}

	public void removeEdge(E source, E dest) {
		int i = 0;
		List<ListGraph.Edge<E>> edges = map.get(source);
		List<ListGraph.Edge<E>> edges1 = map.get(dest);

		for (; i < edges.size(); i++) {
			ListGraph.Edge<E> e = edges.get(i);
			if (e.getDest().equals(dest)) {
				break;
			}
		}

		edges.remove(i);

		if (directed == false) {
			int j = 0;
			for (; j < edges1.size(); j++) {
				ListGraph.Edge<E> e = edges1.get(j);
				if (e.getDest().equals(source)) {
					break;
				}
			}
			edges1.remove(j);
		}
	}

	public List<E> bfs(E source) {

		Map<E, Boolean> visited = new HashMap<>();
		List<E> traversal = new LinkedList<>();

		for (E vertex : map.keySet()) {
			visited.put(vertex, false);
		}

		Queue<E> Q = new ArrayDeque<>();
		Q.offer(source);

		while (!Q.isEmpty()) {
			E currVertex = Q.poll();

			if (visited.get(currVertex)) {
				continue;
			}

			visited.put(currVertex, true);

			// for every adjacent edge, get the vertex (dest?)
			List<Edge<E>> edges = getEdges(currVertex);

			for (int i = 0; edges.size() > i; i++) {
				E destination = edges.get(i).getDest();

				// if the vertex has NOT been visited, then add it to the queue
				if (!visited.get(destination)) {
					Q.offer(destination);
				}
			}

			traversal.add(currVertex);
		}

		return traversal;
	}

	public List<E> dfs(E source) {
		Map<E, Boolean> visited = new HashMap<>();
		List<E> traversal = new LinkedList<>();

		for (E vertex : map.keySet()) {
			visited.put(vertex, false);
		}

		Stack<E> S = new Stack<>();
		S.push(source);

		while (!S.isEmpty()) {
			E currVertex = S.pop();

			if (visited.get(currVertex)) {
				continue;
			}

			visited.put(currVertex, true);

			// for every adjacent edge, get the vertex (dest?)
			List<Edge<E>> edges = getEdges(currVertex);

			for (int i = 0; edges.size() > i; i++) {
				E destination = edges.get(i).getDest();

				// if the vertex has NOT been visited, then add it to the queue
				if (!visited.get(destination)) {
					S.push(destination);
				}
			}

			traversal.add(currVertex);
		}

		return traversal;
	}
}
