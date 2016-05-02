package eg.edu.alexu.csd.filestructure.graphs;

public class Edge {
	private int node ;
	private int weight ;
	public Edge ( int n , int w)
	{
		this.node =n ;
		this .weight = w;
	}
	public int getNode() {
		return node;
	}
	public int getWeight() {
		return weight;
	}

}
