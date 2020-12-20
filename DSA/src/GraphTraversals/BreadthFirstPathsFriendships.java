package GraphTraversals;

import Social.Person;
import Social.PersonForGraph;
import structures.PersonGraph;
import structures.Queue;
import structures.Stack;

/**
 * Graph traversal to know the shortest path
 * @author Iker Pintado
 *
 */

public class BreadthFirstPathsFriendships {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    private PersonForGraph[] val;

    /**
     * Constructor of the traversal
     * @param G-the graph to traverse
     * @param s-the vertices to take as reference in the traversal
     */
    public BreadthFirstPathsFriendships(PersonGraph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        val=G.getValues();
        bfs(G, s);
    }

    
    // multiple sources
    public BreadthFirstPathsFriendships(PersonGraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
        bfs(G, sources);
    }


    /**
     * The traversal as itself
     * @param G-The traversed graph
     * @param s-the element of the graph to take as reference
     */
    private void bfs(PersonGraph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    // BFS from multiple sources
    private void bfs(PersonGraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Checks if there is a path between v and s
     * @param v-the vertices
     * @return true if there is a path to the vertices
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Retrieves the shortest path from s to v
     * @param v-the vertices
     * @return the distance to v
     */
    public int distTo(int v) {
        return distTo[v];
    }

    /**
     * retrieves in a stack the shortest path to v
     * @param v-the vertices
     * @return a stack of Person that represent the path
     */
    public Stack<Person> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Person> path = new Stack<Person>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(val[x].thePerson);
        path.push(val[x].thePerson);
        return path;
    }




}
