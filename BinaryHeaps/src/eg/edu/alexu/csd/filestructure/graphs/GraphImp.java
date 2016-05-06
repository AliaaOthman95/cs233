package eg.edu.alexu.csd.filestructure.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphImp implements IGraph {

	private int v, e;
	private Map<Integer, ArrayList<Edge>> Adjacency_List = new HashMap<Integer, ArrayList<Edge>>();;
	private ArrayList<Integer> sequence = new ArrayList<Integer>();
	ArrayList<Integer> vertices = new ArrayList<Integer>();
	int[][] graph;

	@Override
	public void readGraph(File file) {

		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(file));
			sCurrentLine = br.readLine();
			if (sCurrentLine != null) {
				String[] numbers = sCurrentLine.split(" ");
				try {
					v = Integer.parseInt(numbers[0]);
					e = Integer.parseInt(numbers[1]);

				} catch (Exception e) {
					throw new RuntimeException();
				}
				for (int i = 0; i < v; i++) {
					Adjacency_List.put(i, new ArrayList<Edge>());

				}
				graph = new int[v][v];
				int counter = 0;
				while ((sCurrentLine = br.readLine()) != null) {

					numbers = sCurrentLine.split(" ");
					int source = Integer.parseInt(numbers[0]);
					int sink = Integer.parseInt(numbers[1]);
					int weight = Integer.parseInt(numbers[2]);
					try {
						if (source < v && sink < v) {
							graph[source][sink] = weight;
							Edge d = new Edge(source, sink, weight);
							ArrayList<Edge> list = Adjacency_List.get(source);

							list.add(d);
							Adjacency_List.put(source, list);
							if (!vertices.contains(source)) {
								vertices.add(source);
							}
							if (!vertices.contains(sink)) {
								vertices.add(sink);
							}
							counter++;
						}

					} catch (Exception e) {
						throw new RuntimeException();
					}

				}
				if (counter < e)
					throw new RuntimeException();
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public int size() {

		return e;
	}

	@Override
	public ArrayList<Integer> getVertices() {

		// for (int i = 0; i < v; i++) {
		// vertices.add(i);
		// }
		return vertices;
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {

		ArrayList<Integer> neighbour = new ArrayList<Integer>();
		for (int i = 0; i < Adjacency_List.get(v).size(); i++) {
			neighbour.add(Adjacency_List.get(v).get(i).getV());
		}

		return neighbour;
	}

	@Override
	public void runDijkstra(int src, int[] distances) {

		int V = getVertices().size();
		boolean included[] = new boolean[distances.length];
		initialize(src, distances);
		for (int i = 0; i < V - 1; i++) {

			int u = minDistance(distances, included);

			included[u] = true;
			sequence.add(u);
			for (int j = 0; j < V; j++) {

				if (!included[j] && distances[u] != (Integer.MAX_VALUE / 2)
						&& graph[u][j] != 0
						&& distances[u] + graph[u][j] < distances[j]) {
					distances[j] = distances[u] + graph[u][j];
				}
			}
		}

	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {

		return sequence;
	}

	@Override
	public boolean runBellmanFord(int src, int[] distances) {

		initialize(src, distances);

		for (int i = 0; i < v - 1; i++) {
			for (int j = 0; j < Adjacency_List.size(); j++) {
				for (Edge edge : Adjacency_List.get(j)) {
					int u = edge.getU();
					int v = edge.getV();
					// relax the edge
					if (distances[u] + edge.getWeight() < distances[v]) {
						distances[v] = distances[u] + edge.getWeight();

					}
				}
			}
		}

		return checkCycles(distances);
	}

	public void initialize(int src, int[] distances) {

		int V = getVertices().size();
		for (int j = 0; j < V; j++) {
			distances[j] = Integer.MAX_VALUE / 2;
		}

		distances[src] = 0;

	}

	public boolean checkCycles(int[] distances) {

		for (int j = 0; j < Adjacency_List.size(); j++) {
			for (Edge edge : Adjacency_List.get(j)) {
				int u = edge.getU();
				int v = edge.getV();
				if (distances[u] + edge.getWeight() < distances[v]) {

					return false;
				}

			}
		}
		return true;

	}

	public int minDistance(int distances[], boolean included[]) {

		int min = (Integer.MAX_VALUE / 2), min_index = -1;

		for (int i = 0; i < distances.length; i++)
			if (included[i] == false && distances[i] <= min) {
				min = distances[i];
				min_index = i;
			}

		return min_index;
	}
}