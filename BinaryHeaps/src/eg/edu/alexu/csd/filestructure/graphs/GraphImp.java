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
				int counter = 0;
				while ((sCurrentLine = br.readLine()) != null) {
					counter++;
					numbers = sCurrentLine.split(" ");
					try {
						if (Integer.parseInt(numbers[1]) < v
								&& Integer.parseInt(numbers[0]) < v) {
							Edge d = new Edge(Integer.parseInt(numbers[0]),
									Integer.parseInt(numbers[1]),
									Integer.parseInt(numbers[2]));
							ArrayList<Edge> list = Adjacency_List.get(Integer
									.parseInt(numbers[0]));

							list.add(d);
							Adjacency_List.put(Integer.parseInt(numbers[0]),
									list);
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
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for (int i = 0; i < v; i++) {
			vertices.add(i);
		}
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

	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {

		return null;
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
		distances = new int[v];
		for (int j = 0; j < distances.length; j++) {
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
}
