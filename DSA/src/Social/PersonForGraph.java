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
	
	public PersonForGraph(Person p,int ver) {
		thePerson=p;
		v=ver;
		filled=0;
		setTheList();
		numP++;
	}
	
	public void setTheList() {
		length=thePerson.getNumFriends();
		if(length>0)
		griendList=new PersonForGraph[length];
		
	}
	
	public void addToTheList(PersonForGraph p) throws FullListException{
		if(filled==griendList.length) throw new FullListException();
		griendList[filled]=p;
		filled++;
	}
	
}
