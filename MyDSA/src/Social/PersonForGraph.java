package Social;

import Exceptions.FullListException;

/**
 * Person class used to represent graphs
 * @author Iker Pintado
 */
public class PersonForGraph {
	/**
	 * integer that represents the number of persons that are in the array
	 */
	public static int numP=0;
	/**
	 * The parameter for the person of the class
	 */
	public Person thePerson;
	/**
	 * Parameter of the vertices that this person is
	 */
	public int v;
	/**
	 * friendlist for graphs
	 */
	public PersonForGraph[] griendList;
	/**
	 * represent the number of friends added to griendList
	 */
	public int filled;
	/**
	 * represents the length of the griendlist
	 */
	public int length;
	/**
	 * Constructor of the helping class
	 * @param p -the person
	 * @param ver -the vertex the person is
	 */
	public PersonForGraph(Person p,int ver) {
		thePerson=p;
		v=ver;
		filled=0;
		setTheList();
		numP++;
	}
	
	/**
	 * initializes the list of friends
	 */
	public void setTheList() {
		length=thePerson.getNumFriends();
		if(length>0)
		griendList=new PersonForGraph[length];
		
	}
	/**
	 * Adds a person of the helping class to the list
	 * @param p -the person
	 * @throws FullListException is used when the list of friends is full
	 */
	public void addToTheList(PersonForGraph p) throws FullListException{
		if(filled==griendList.length) throw new FullListException();
		griendList[filled]=p;
		filled++;
	}
	
	@Override
	public boolean equals(Object e) {
		if(e instanceof PersonForGraph) {
			PersonForGraph p=(PersonForGraph)e;
			return this.thePerson.equals(p.thePerson)&&this.v==p.v;
		}else
			return false;
		
	}
}
