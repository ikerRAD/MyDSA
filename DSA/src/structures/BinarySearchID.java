package structures;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;
import Social.Person;
/**
 * Binary search tree for the class person ordered by id's
 * @author ikerb
 *
 */
public class BinarySearchID extends LinkedBinarySearchTree<Person> implements Iterable<Person>{
	/**
	 * Basic constructor of the class that calls the constructor of the superclass
	 */
	public BinarySearchID() {
		super();
	}
	/**
	 * ordered adder for the class
	 * @param p-The person
	 */
	public void add(Person p) {
		this.addElement(p);
	}
	/**
	 * removes a person and returns that same person
	 * @param p-The person to return
	 * @return the removed person
	 * @throws ElementNotFoundException
	 */
	public Person remove(Person p) throws ElementNotFoundException {
		return this.removeElement(p);
	}
	@Override
	/**
	 * Returns an iterator for the BinarySearchTree
	 */
	public Iterator<Person> iterator() {
		
		return this.iteratorInOrder();
	}
	/**
	 * converts the tree into a list
	 * @return
	 */
	public LinkedList<Person> toList(){
		LinkedList<Person> ret=new LinkedList<Person>();
		inorder(root,ret);
		/*Iterator<Person> it=this.iterator();
		while(it.hasNext()) {
			ret.addToTail(it.next());
		}*/
		return ret;
	}
	/**
	 * converts the tree into a tree ordered by fame
	 * @return
	 */
	public LinkedList<Person> toFameList(){
		LinkedList<Person> ret=new LinkedList<Person>();
		Iterator<Person> it=this.iterator();
		BinarySearchFriends bin=new BinarySearchFriends();
		while(it.hasNext()) {
			bin.add(it.next());
		}
		it=bin.iterator();
		while(it.hasNext()) {
			ret.addToHead(it.next());
		}
		return ret;
	}
	/**
	 * front-end method for printResidential
	 * @param tree :the tree to traverse
	 */
	public void printResidential(LinkedBinarySearchTree<String> tree) {
        printResidential(root, tree);
    }
	/**
	 * worker method that traverses a tree and prints the name, surname, birthplace and the places where the people born in a place into the tree have studied
	 * @param x :the node of this tree
	 * @param tree :the tree with the birthplaces
	 */
    private void printResidential(BinaryTreeNode<Person> x, LinkedBinarySearchTree<String> tree){
        if(x!=null) {
            printResidential(x.left, tree);
            if(tree.contains(x.element.getPersonData()[5])) {
                System.out.println("\u001B[33m"+"---------------------------------------"+"\u001B[0m"+" \n"+
                "\u001B[36m"+"Name: "+x.element.getPersonData()[1]      +"\u001B[0m"+" \n"+
                "\u001B[36m"+"Surname: "+x.element.getPersonData()[2]   +"\u001B[0m"+" \n"+
                "\u001B[36m"+"Birthplace: "+x.element.getPersonData()[5]+"\u001B[0m"+" \n"+
                "\u001B[36m"+"Stuided at: "+x.element.getPersonData()[7]+"\u001B[0m"+" \n"+
                "\u001B[33m"+"---------------------------------------"+"\u001B[0m"+" \n");
            }
            printResidential(x.right, tree);
        }
    }
	
}
