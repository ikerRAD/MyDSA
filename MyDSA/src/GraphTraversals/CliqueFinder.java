package GraphTraversals;


import java.util.Iterator;


import Social.Person;

import structures.LinkedList;
import structures.PersonGraph;
/**
 * Class that performs a backtracking algorithm to find all the cliques
 * @author G612050 
 *
 */
public class CliqueFinder {
	/**
	 * static parameter for the list of different cliques
	 */
	private static LinkedList<LinkedList<Integer>> solus;
	
	/**
	 * The main method of the traverser class
	 * @param g the graph of people to find cliques from
	 */
	public static void findCliques(PersonGraph g) {
		solus=new LinkedList<LinkedList<Integer>>();
		for(int i=0;i<g.V();i++) {
			boolean[] cq=new boolean[g.V()];
			cq[i]=true;
			if(g.getValues()[i].thePerson.getNumFriends()>=3) {//if it is even possible to have a clique
				LinkedList<Integer> cand=null;//we create a candidate list that can contain all the friends of the vertex
				backTrack(i,g,1,cq,cand);
			}
		}
	}
	
	/**
	 * Brute force method to find the biggest clique
	 * @param thi - the vertex we come from
	 * @param g - the graph of people
	 * @param size - the times we have called this method in recursion (starting with 1) and also represents the people in the clique
	 * @param currentClique - Array of booleans that represent the people of the graph that are in the clique
	 * @param cand - list of the potential candidates to make a clique
	 */
	private static void backTrack(int thi, PersonGraph g, int size, boolean[] currentClique,LinkedList<Integer> cand) {
		LinkedList<Integer> cando=candidates(size,g,thi,cand,currentClique);
		if(cando.size()==0) {//we have a solution
			if(size>=4) {
				computeSolution(currentClique,g,size);
			}
		}else if(cando.size()>0) {
			for(Integer i:cando) {
				if(!currentClique[i]) {
					currentClique[i]=true;
					backTrack(i,g,size+1,currentClique,cando);
					//System.out.println("backtracked");
					currentClique[i]=false;
				}
			}
		}
		
	}

	/**
	 * Method that analyzes all the found possible solutions
	 * @param currentClique - Array of booleans that represent the people of the graph that are in the clique
	 * @param g - the graph of people
	 * @param size - the number of people in the clique
	 */
	private static void computeSolution(boolean[] currentClique, PersonGraph g,int size) {
		LinkedList<Integer> intro=new LinkedList<Integer>(),check;
		int j=0;
		//System.out.println("Size: "+size);
		//System.out.println(Arrays.toString(currentClique));
		for(int i=0;i<currentClique.length && size>j;i++) {
			if(currentClique[i]) {
				intro.addToTail((Integer)i);
				j++;
			}
		}
		boolean printAndGoIn=true;
		//if(intro.size()<4) printAndGoIn=false;
		Iterator<LinkedList<Integer>> it=solus.iterator();
		while(printAndGoIn && it.hasNext()) {//checks if the solution has been found
			check=it.next();
			if(check.Exactlyequals(intro)) {
				printAndGoIn=false;
			}
		}
		if(printAndGoIn) {//If we have a new solution we print it in execution time
			solus.addToTail(intro);
			String print="\u001B[33m"+"Clique number "+"\u001B[32m"+solus.size()+"\u001B[33m"+": "+"\u001B[0m"+" \n"
						+"\u001B[33m"+"-------------------------------- "+"\u001B[0m"+" \n";
			for(int w:intro) {
				Person p=g.getValues()[w].thePerson;
				print=print+"\u001B[36m"+"Id: "+p.getPersonData()[0]+"\u001B[0m"+" \n"+
						"\u001B[36m"+"Name: "+p.getPersonData()[1]      +"\u001B[0m"+" \n"+
		                "\u001B[36m"+"Surname: "+p.getPersonData()[2]   +"\u001B[0m"+" \n"+
						"\u001B[33m"+"-------------------------------- "+"\u001B[0m"+" \n";
			}
			print=print+"\n \n";
			System.out.println(print);		
		}
	}

	/**
	 * Computes the possible candidates to form a clique
	 * @param size - the size of the clique and times used recursion
	 * @param g - the graph of people
	 * @param thi - the vertex we come from
	 * @param cand - old list of the potential candidates to make a clique
	 * @param currentClique - Array of booleans that represent the people of the graph that are in the clique
	 * @return the linked list of all the candidates to form a clique
	 */
	private static LinkedList<Integer> candidates(int size, PersonGraph g, int thi, LinkedList<Integer> cand, boolean[] currentClique) {
		LinkedList<Integer> candu=new LinkedList<Integer>();
		if(size==1){
			for(int w:g.adj(thi)) {
				candu.addToTail(w);
			}
		}else {
			for(Integer w:g.adj(thi)) {
				if(!currentClique[w]) {
					if(cand.contains(w)) {
						candu.addToTail(w);
					}
				}
			}
		}
		//System.out.println(thi+Arrays.toString(cand)+useful);
		return candu;
	}
	
}
