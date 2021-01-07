package GraphTraversals;

import Social.Person;
import structures.PersonGraph;
import structures.Stack;

public class LongPathFinder {
	/**
	 * the biggest number of steps of the last solution
	 */
	private static int lastSol;
	
	/**
	 * The path of the last achieved solution
	 */
	private static Stack<Person> solution;
	
	/**
	 * Backtracking method to find the longest path between 2 vertices
	 * @param g the graph
	 * @param n the number of steps
	 * @param f the vertex we come from
	 * @param t the vertex we go to
	 * @param curPath the path we have already traversed
	 * @param edgeTo the edge to each vertex
	 */
	public static void BackTrack(PersonGraph g, int n, int f, int t, boolean[] curPath, int[] edgeTo) {
		if(n==0) {
			curPath[f]=true;
			lastSol=0;
			solution=null;
		}
		if(f==t) {//it is a solution but we need to check if it is the largest one and it has to maintain it's position as the largest one
			if(n>lastSol) {
				lastSol=n;
				solution=pathTrack(g,t,n,curPath,edgeTo);
			}
			
		}else {
			if(n<=g.V()-1-g.NonConnected()) {//n==g.V()-1-g.NonConnected() is the maximun number of possible steps in the chain
				int[] cand=new int[g.V()-g.NonConnected()];//list that will contain all the possible candidates
				int valid=0;
				valid=potential(cand,valid,g,curPath,f);//number of valid elements
				if(valid>0) {
					for(int i=0;i<valid&&lastSol<g.V()-1-g.NonConnected();i++) {//try them all unless a solution is found
						curPath[cand[i]]=true; 
						edgeTo[cand[i]]=f;
						BackTrack(g,n+1,cand[i],t,curPath,edgeTo);
						curPath[cand[i]]=false; //prepare it to be used again
					}
				}
			}
		}	
	}
	/**
	 * Calculates all the choices that have potential to be a solution
	 * @param c the array of potential solutions
	 * @param v the number of potential solutions
	 * @param g the graph
	 * @param curPath the current path we follow
	 * @param from the vertex to take solutions from
	 * @return
	 */
	public static int potential(int[] c, int v, PersonGraph g, boolean[] curPath, int from) {
		for(int w:g.adj(from)) {
			if(!curPath[w]) {
				c[v]=w;
				v++;
			}
		}
		return v;
	}
	
	/**
     * retrieves in a stack the 'longest' path to v
     * @param v-the vertices
     * @return a stack of Person that represent the path
     */
    public static Stack<Person> pathTrack(PersonGraph g, int v,int n, boolean[] curPath, int[] edgeTo) {
        Stack<Person> path = new Stack<Person>();
        int x,dec=n;
        for (x = v; dec>-1; x = edgeTo[x]) {
            path.push(g.getValues()[x].thePerson);
            dec--;
        }
        return path;
    }
    
    public static Stack<Person> getSolution(){
    	return solution;
    }
}
