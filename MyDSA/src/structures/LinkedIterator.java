package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * iteratosrfor dsa
 *
 * 
 */
public class LinkedIterator<T> implements Iterator<T> {
	@SuppressWarnings("unused")
	/**
	 * unused parameter but was on the slides
	 */
	private int count;
	/**
	 * the list to iterate
	 */
	private LinearNode<T> current;
	/**
	 * constructor
	 * @param collection-current
	 * @param size-count
	 */
	public LinkedIterator(LinearNode<T> collection,int size) {
		current=collection;
		count=size;
	}
	/**
	 * overridden hasnext
	 */
	@Override
	public boolean hasNext() {
		
		return current!=null;
	}
	/**
	 * overridden next
	 */
	@Override
	public T next() {
		if(!hasNext()) throw new NoSuchElementException();
		T result=current.getElement();
		current=current.getNext();
		return result;
	}
	/**
	 * unsupported remove operation
	 */
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}

}
