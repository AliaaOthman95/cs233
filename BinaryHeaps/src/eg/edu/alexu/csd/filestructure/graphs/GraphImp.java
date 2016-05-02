package eg.edu.alexu.csd.filestructure.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GraphImp implements IGraph {

	private int v, e;
	private Map<Integer, ArrayList<Edge>> Adjacency_List;
    private int distance ;
	@Override
	public void readGraph(File file) {

		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("C:\\testing.txt"));
			sCurrentLine = br.readLine();
			String[] numbers = sCurrentLine.split(" ");
			v = Integer.parseInt(numbers[0]);
			e = Integer.parseInt(numbers[1]);

			for (int i = 0; i < v; i++) {
				Adjacency_List.put(i, new ArrayList<Edge>());

			}
			while ((sCurrentLine = br.readLine()) != null) {
				numbers = sCurrentLine.split(" ");
				if (Integer.parseInt(numbers[1]) < v
						&& Integer.parseInt(numbers[0]) < v) {
					Edge d = new Edge(Integer.parseInt(numbers[1]),
							Integer.parseInt(numbers[2]));
					ArrayList<Edge> list = Adjacency_List.get(Integer
							.parseInt(numbers[0]));

					list.add(d);
					Adjacency_List.put(Integer.parseInt(numbers[0]), list);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
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
			neighbour.add(Adjacency_List.get(v).get(i).getNode());
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
		
		return false;
	}
	
	
	public void initialize()
	{
		
	}

}
