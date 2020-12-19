package structures;

import java.util.Iterator;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NotEnoughElementsException;
import Interfaces.ListADT;
/**
 * linked list for dsa
 *
 */
public class LinkedList<T> implements ListADT<T>,Iterable<T>{
	/**
	 * number of elements
	 */
	protected int count;
	/**
	 * the first and last element
	 */
	protected LinearNode<T> head,tail;
	/**
	 * constructor
	 */
	public LinkedList() {
		count=0;
		head=tail=null;
	}
	/**
	 * adds an element to head
	 * @param element
	 */
	public void addToHead(T element) {
		LinearNode<T> newNode=new LinearNode<T>(element);
		if(!isEmpty()) {
			newNode.setNext(head);
			head=newNode;
		}else {
			head=newNode;
			tail=newNode;
		}
		count++;
	}
	/**
	 * adds element to tail
	 * @param element
	 */
	public void addToTail(T element) {
		LinearNode<T> newNode=new LinearNode<T>(element);
		if(!isEmpty()) {
			tail.setNext(newNode);
			tail=newNode;
		}else {
			head=newNode;
			tail=newNode;
		}
		count++;
	}
	/**
	 * adds an element in the index position
	 * @param element
	 * @param index
	 * @throws NotEnoughElementsException equivalent to IndexOutOfBoundsException
	 */
	public void add(T element,int index) throws NotEnoughElementsException{
		LinearNode<T> newOne=new LinearNode<T>(element);
		LinearNode<T> traverse=head;
		int i=0;
		if(index>size()) {
			throw new NotEnoughElementsException("There are not enough elements for that index");
		}else if(index==0) {
			addToHead(element);
		}else if(index==size()) {
			addToTail(element);
		}else {
			while(traverse.hasNext()&&i<index-1) {//i have to exit when i=index-1 in order to make my element the next one, the one in the index
				traverse=traverse.getNext();
				i++;
			}
			newOne.setNext(traverse.getNext());
			traverse.setNext(newOne);
			count++;
		}

	}
	
	@Override
	public T removeFirst() throws EmptyCollectionException{
		if(isEmpty())
			throw new EmptyCollectionException("List");
		LinearNode<T> res=head;
		head=head.getNext();
		if(head==null)
			tail=null;
		
		count--;
		return res.getElement();
	}

	@Override
	public T removeLast() throws EmptyCollectionException{
		if(isEmpty())
			throw new EmptyCollectionException("List");
		LinearNode<T> previous=null;
		LinearNode<T> current=head;
		while(current.hasNext()) {
			previous=current;
			current=current.getNext();
		}
		tail=previous;
		if(tail==null)
			head=null;
		else
			tail.setNext(null);
		
		count--;
		return current.getElement();
	}
	
	@Override
	public T remove(T element) throws EmptyCollectionException,ElementNotFoundException{
		if(isEmpty()) throw new EmptyCollectionException("List");
		boolean found=false;
		LinearNode<T> previous=null;
		LinearNode<T> current=head;
		while(current!=null&&!found) {
			if(element.equals(current.getElement())) {
				found=true;
			}else {
				previous=current;
				current=current.getNext();
			}
		}
		if(!found) throw new ElementNotFoundException("The element does not belong to this list");
		
		if(size()==1)
			head=tail=null;
		else if(current.equals(head))
			head=current.getNext();
		else if(current.equals(tail)) {
			tail=previous;
			tail.setNext(null);
		}else
			previous.setNext(current.getNext());
			
		count--;
		return current.getElement();
	}
	/**
	 * given an index, removes the element in that position
	 * @param index
	 * @return the removed element
	 * @throws NotEnoughElementsException equivalent to IndexOutOfBoundsException
	 */
	public T remove(int index) throws NotEnoughElementsException{
		T res=null;
		LinearNode<T> traverse=head;
		LinearNode<T> previous=null;
		int i=0;
		if(index>size()-1) throw new NotEnoughElementsException("There are not enough elements for that index");
		else if(index==0)
			res=removeFirst();
		else if(index==size()-1)
			res=removeLast();
		else {
			while(traverse.hasNext()&&i<index) {
				previous=traverse;
				traverse=traverse.getNext();
				i++;
			}
			previous.setNext(traverse.getNext());
			count--;
			res=traverse.getElement();
		}
		return res;
	}
	/**
	 * gets the element on the index
	 * @param index
	 * @return the element
	 * @throws NotEnoughElementsException equivalent to IndexOutOfBoundsException
	 */
	public T get(int index) throws NotEnoughElementsException{
		T res=null;
		LinearNode<T> traverse=head;
		int i=0;
		if(index>size()-1) throw new NotEnoughElementsException("There are not enough elements for that index");
		else if(index==0)
			res=first();
		else if(index==size()-1)
			res=last();
		else {
			while(traverse!=null&&i<index) {
				traverse=traverse.getNext();
				i++;
			}
			
			res=traverse.getElement();
		}
		return res;
	}
	
	/**
	 * gets the index of the element
	 * @param the object
	 * @return the index
	 * @throws ElementNotFoundException when the element does not belong to the list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public int getIndex(T p) throws ElementNotFoundException,EmptyCollectionException{
		int res=0;
		LinearNode<T> traverse=head;
		int i=0;
		if(isEmpty()) throw new EmptyCollectionException("Linked List");
		boolean done=false;
		
		while(traverse!=null&&!done) {
			if(traverse.getElement().equals(p)) {
				done=true;
			}else {
				traverse=traverse.getNext();
				i++;
			}
		}
			
			res=i;
		if(traverse==null) throw new ElementNotFoundException();
			
		return res;
	}
	
	
	@Override
	public T first() {
		
		return head.getElement();
	}

	@Override
	public T last() {
		
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) throws EmptyCollectionException{
		if(isEmpty()) throw new EmptyCollectionException("List");
		boolean found=false;
		LinearNode<T> current=head;
		while(current!=null&&!found) {
			if(target.equals(current.getElement()))
				found=true;
			else
				current=current.getNext();
		}
		return found;
	}

	@Override
	public boolean isEmpty() {
		
		return count==0;
	}

	@Override
	public int size() {
		
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedIterator<T>(head,count);
	}
	/**
	 * copies the list
	 * @param the list to copy
	 */
	public void copy(LinkedList<T> list) {
		for(T elem:list) {
			this.addToTail(elem);
		}
	}

}
