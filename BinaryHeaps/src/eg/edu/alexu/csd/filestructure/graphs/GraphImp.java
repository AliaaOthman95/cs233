 package eg.edu.alexu.csd.filestructure.graphs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphImp implements IGraph {
	private static int INFINITY = Integer.MAX_VALUE / 2;
	private Map<Integer, ArrayList<Point>> adjacencyList = new HashMap<Integer, ArrayList<Point>>();
	private int noOfVertices = 0;
	private int noOfEdges = 0;
	ArrayList<Integer> dijkstraProcess = new ArrayList<Integer>();

	@Override
	public void readGraph(File file) {

		if (file != null && file.isFile()) {

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String sCurrentLine = br.readLine();
				if (sCurrentLine != null) {
					String[] line = sCurrentLine.split(" ");
					if (line[0] != null && line[1] != null) {
						noOfVertices = Integer.parseInt(line[0]);
						noOfEdges = Integer.parseInt(line[1]);
					} else {
						throw new RuntimeException();
					}
					for (int i = 0; i < noOfVertices; i++) {
						adjacencyList.put(i, new ArrayList<Point>());
					}
					int counter = 0;
					while ((sCurrentLine = br.readLine()) != null) {
						line = sCurrentLine.split(" ");
						if (line[0] != null && line[1] != null && line[2] != null) {
							int source = Integer.parseInt(line[0]);
							int destination = Integer.parseInt(line[1]);
							int weight = Integer.parseInt(line[2]);
							if (source < noOfVertices && destination < noOfVertices) {
								ArrayList<Point> slist = adjacencyList.get(source);
								slist.add(new Point(destination, weight));
								adjacencyList.put(source, slist);
							}
							counter++;
						} else {
							throw new RuntimeException();
						}
					}

					if (counter != noOfEdges) {
						throw new RuntimeException();
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}

	}

	@Override
	public int size() {

		return noOfEdges;
	}

	@Override
	public ArrayList<Integer> getVertices() {

		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for (int i = 0; i < noOfVertices; i++) {
			vertices.add(i);
		}
		return vertices;
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		// TODO Auto-generated method stub
		ArrayList<Point> adj = adjacencyList.get(v);
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < adj.size(); i++) {
			list.add(adj.get(i).x);
		}

		return list;
	}

	int minDistance(int dist[], Boolean sptSet[]) {
		int min = INFINITY, min_index = -1;

		for (int v = 0; v < getVertices().size(); v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	@Override
	public void runDijkstra(int src, int[] distances) {

		int V = getVertices().size();
		Boolean sptSet[] = new Boolean[V];
		for (int i = 0; i < V; i++) {
			distances[i] = INFINITY;
			sptSet[i] = false;
		}

		distances[src] = 0;

		for (int count = 0; count < V - 1; count++) {
			int u = minDistance(distances, sptSet);

			sptSet[u] = true;

			dijkstraProcess.add(u);
			for (int v = 0; v < V; v++) {

				if (!sptSet[v] && distances[u] != INFINITY
						&& distances[u] + adjacencyList.get(u).get(v).y < distances[v]) {
					distances[v] = distances[u] + adjacencyList.get(u).get(v).y;
				}
			}
		}

	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {

		return dijkstraProcess;
	}

	@Override
	public boolean runBellmanFord(int src, int[] distances) {

		int V = getVertices().size();

		for (int i = 0; i < V; i++) {
			distances[i] = INFINITY;
		}

		distances[src] = 0;

		for (int i = 0; i < V - 1; i++) {
			for (int j = 0; j < adjacencyList.size(); j++) {
				for (int j2 = 0; j2 < getNeighbors(j).size(); j2++) {
					int v = adjacencyList.get(j).get(j2).x;
					int egdeWeight = adjacencyList.get(j).get(j2).y;
					if (distances[j] + egdeWeight < distances[v]) {
						distances[v] = distances[j] + egdeWeight;
					}
				}
			}
		}

		for (int j = 0; j < adjacencyList.size(); j++) {
			for (int j2 = 0; j2 < getNeighbors(j).size(); j2++) {
				int v = adjacencyList.get(j).get(j2).x;
				int egdeWeight = adjacencyList.get(j).get(j2).y;
				if (distances[j] + egdeWeight < distances[v]) {
					return false;
				}
			}
		}

		return true;
	}

}