package Interfaces;
import java.util.Iterator;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

public interface ListADT<T> extends Iterable<T>{
	/**
	 * removes last
	 * @return removed element
	 */
	public T removeFirst();
	/**
	 * removes first
	 * @return removed element
	 */
	public T removeLast();
	/**
	 * removes the given element
	 * @param element
	 * @return the removed element
	 * @throws EmptyCollectionException if the list is empty
	 * @throws ElementNotFoundException if the element is not inside
	 */
	public T remove(T element) throws EmptyCollectionException, ElementNotFoundException;
	/**
	 * gives the first element
	 * @return the first element
	 */
	public T first();
	/**
	 * gives the last element
	 * @return the last element
	 */
	public T last();
	/**
	 * checks if the element is in the list
	 * @param target to prove
	 * @return if it is in or not
	 */
	public boolean contains (T target);
	/**
	 * checks if the list is empty
	 * @return if it is empty or not
	 */
	public boolean isEmpty();
	/**
	 * checks the size
	 * @return the size
	 */
	public int size();
	/**
	 * returns an iterator of the list
	 */
	public Iterator<T> iterator();
	/**
	 * becomes the list a string
	 * @return the list
	 */
	public String toString();
}
