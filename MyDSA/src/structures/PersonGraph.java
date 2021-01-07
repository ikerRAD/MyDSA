package structures;

import Social.PersonForGraph;


/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  named 0 through V-1.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of the neighbors adjacent to a vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */


public class PersonGraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;  // 
    /**
     * List that contains the value of each vertex
     */
    private PersonForGraph[] val;
    /**
     * counts the number of lonely vertices
     */
    private int lonely;
   /**
     * Create an empty graph with V vertices.
     * @throws java.lang.IllegalArgumentException if V < 0
     */
    @SuppressWarnings("unchecked")
	public PersonGraph(PersonForGraph[] valu) {
    	lonely=0;
        //if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = valu.length;
        this.E = 0;
        this.val=valu;
        //creation of generic arrays not allowed
//        adj = new Bag<Integer>[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        //Establish the friendships
        	int W,e;
        	boolean[] marked=new boolean[V];
        for (int v=0;v<V;v++) {
        	W=val[v].length;
        	if(W==0) {
        		lonely++;
        	}
        	for(int w=0;w<W && adj[v].size()<W;w++) {
        		e=val[v].griendList[w].v;
        		if(!marked[e]) {//if the relationship is not already added
        			addEdge(v,e);
        		}
        	}
        	marked[v]=true;
        }
    }
    
   /**
     * Copy constructor.
     */
    /*public PersonGraph(PersonGraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }*/

   /**
     * Return the number of vertices in the graph.
     */
    public int V() { return V; }

   /**
     * Return the number of edges in the graph.
     */
    public int E() { return E; }
    
    /**
     * Return the number of non-connected vertices
     */
    public int NonConnected() {
    	return lonely;
    }
    
    /**
     * Getter of the values array
     * @return the array of values
     */
    public PersonForGraph[] getValues() {
    	return val;
    }
    
   /**
     * Add the undirected edge v-w to graph.
     * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(int v, int w) {
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
        if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


   /**
     * Return the list of neighbors of vertex v as in Iterable.
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
     */
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
        return adj[v];
    }


   /**
     * Return a string representation of the graph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}
