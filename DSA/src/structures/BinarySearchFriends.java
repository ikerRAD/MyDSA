package structures;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;
import Social.Person;
/**
 * Binary search tree for the class person ordered by the number of friends
 * @author ikerb
 *
 */
public class BinarySearchFriends extends LinkedBinarySearchTree<Person> implements Iterable<Person>{
	/**
	 * Constructor of the class
	 */
	public BinarySearchFriends() {
		super();
	}

	/**
	    * Adds the specified object to the binary search tree in the
	    * appropriate position according to its number of friends.  Note that
	    * equal elements are added to the right.
	    *
	    * @param element  the element to be added to the binary search tree
	    */
	@Override
	public void addElement (Person element) 
	{  BinaryTreeNode<Person> temp = new BinaryTreeNode<Person> (element);
	   if (isEmpty())
	      root = temp;
	   else 
	   {  BinaryTreeNode<Person> current = root;
	      boolean added = false;

	      while (!added) 
          {	if (element.compareToByFriends(current.element) < 0)
          	{ if (current.left == null) 
	          {  current.left = temp;
	             added = true;
	          } 
	          else
	             current = current.left;
	          }
	       else
	       { if (current.right == null) 
	         {  current.right = temp;
	            added = true;
	         } 
	         else
	            current = current.right;
	         }
	      } //while
	   } // outer if   
	      count++;
	}
	/**
	 * calls the adder in order to make cleaner calls in previous classes
	 * @param p-the person
	 */
	public void add(Person p) {
		addElement(p);
	}
	/**
	    * Removes the first element that matches the specified target
	    * element from the binary search tree and returns a reference to
	    * it.  Throws a ElementNotFoundException if the specified target
	    * element is not found in the binary search tree.
	    *
	    * @param targetElement              the element being sought in the binary 
	    *                                   search tree
	    * @throws ElementNotFoundException  if an element not found exception occurs
	    */
	@Override
	public Person removeElement (Person targetElement) throws ElementNotFoundException{
		Person result = null;
		if (!isEmpty())
		{ if ((targetElement).equals(root.element)) 
			{  result =  root.element;
			root = replacement (root);
			count--; }
		else 
		{  BinaryTreeNode<Person> current, parent = root;
			boolean found = false;

			if ((targetElement).compareToByFriends(root.element) < 0)
				current = root.left;
			else   current = root.right;

			while (current != null && !found) 
			{  if (targetElement.equals(current.element)) 
				{  found = true;
				count--;
				result =  current.element;      
				if (current == parent.left)
				{ parent.left = replacement (current);   }
				else
				{  parent.right = replacement (current);  }
				}
			else 
			{  parent = current;
			if ((targetElement).compareToByFriends(current.element) < 0)
				current = current.left;
			else
				current = current.right;
			}
			} //while           
			if (!found)
				throw new ElementNotFoundException("binary search tree");
		}
		} // end outer if
		return result;
	}
	
	/**
	 * called method for removeElement method
	 * @param p the person to remove
	 * @return the removed person
	 * @throws ElementNotFoundException 
	 */
	public Person remove(Person p) throws ElementNotFoundException {
		return removeElement(p);
	}
	
	/**
	    * Returns a reference to the specified target element if it is
	    * found in the binary tree.  Throws a NoSuchElementException if
	    * the specified target element is not found in this tree.
	    *
	    * @param targetElement  the element being sought in the binary tree
	    * @throws ElementNotFoundException  if an element not found exception occurs
	    */
	   @SuppressWarnings("unused")
	   @Override
	public Person find (Person targetElement) throws ElementNotFoundException 
	   {
	      BinaryTreeNode<Person> current = root; 
	      BinaryTreeNode<Person> temp = current;
	      
	      if (!(current.element.equals(targetElement)) && (current.left !=null)&&
	            ((current.element).compareToByFriends(targetElement) > 0))
	         current = findAgain( targetElement, current.left);
	      
	      else if (!(current.element.equals(targetElement)) && (current.right != null)) 
	         current = findAgain( targetElement, current.right);
	      
	      if (!(current.element.equals(targetElement)))
	         throw new ElementNotFoundException ("binary search tree");
	      
	      return current.element;
	   }

	   /**
	    * Returns a reference to the specified target element if it is
	    * found in this tree.  
	    *
	    * @param targetElement  the element being sought in the tree
	    * @param next           the tree node to being searching on
	    */
	   @SuppressWarnings("unused")

	private BinaryTreeNode<Person> findAgain (Person targetElement, BinaryTreeNode<Person> next) 
	   {
	      BinaryTreeNode<Person> current = next;
	      
	      if (!(next.element.equals(targetElement)) && (next.left !=null) &&
	            ((next.element).compareToByFriends(targetElement) > 0))
	         next = findAgain( targetElement, next.left);
	      
	      else if (!(next.element.equals(targetElement)) && (next.right != null))
	         next = findAgain( targetElement, next.right);
	      
	      return next;
	   }
	   
	public LinkedList<Person> toList(){
		LinkedList<Person> ret=new LinkedList<Person>();
		//Iterator<Person> it=this.iterator();
		inorder(root,ret);
		/*while(it.hasNext()) {
			ret.addToTail(it.next());
		}*/
		return ret;
	}
	
	/**
	 * Returns an iterator for the BinarySearchTree
	 */
	@Override
	public Iterator<Person> iterator() {
		
		return this.iteratorInOrder();
	}

}
