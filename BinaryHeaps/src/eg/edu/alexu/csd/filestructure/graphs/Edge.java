package eg.edu.alexu.csd.filestructure.graphs;

public class Edge {
	private int u ;
	private int v ;
	private int weight ;
	public Edge ( int u,int v , int w)
	{
		this.u =u ;
		this.v = v;
		this .weight = w;
	}
	
	public int getU() {
		return u;
	}

	public int getV() {
		return v;
	}

	public int getWeight() {
		return weight;
	}

}
