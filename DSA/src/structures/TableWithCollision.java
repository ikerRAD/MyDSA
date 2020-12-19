package structures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for splitting an object V into classes using the key K
 * @author Iker Pintado
 *
 * @param <K> the key
 * @param <V> the value to split
 */
public class TableWithCollision<K, V> {
	/**
	 * Class that represent a class of objects V stored by the key value
	 * @author Iker Pintado
	 *
	 */
	public class Collision{
		/**
		 * the parameter for the key
		 */
		public K key;
		@SuppressWarnings("unused")
		/**
		 * The list off all the objects in the same class
		 */
		public LinkedList<V> colli;
		/**
		 * Very simple/basic equals method in order to find this object in its array list
		 */
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Collision e=(Collision)o;
			return key.equals(e.key);
		}
	}
	/**
	 * parameter of the class where there are stored all the different classes
	 */
	private ArrayList<Collision> table;
	/**
	 * Basic constructor of the class
	 */
	public TableWithCollision(){
		table=new ArrayList<Collision>();
	}
	/**
	 * Method that inserts the value in its corresponding class
	 * @param k -The key of the class
	 * @param val -the new value
	 */
	public void put(K k,V val) {
		Collision col;
		Iterator<Collision> it=table.iterator();
		boolean found=false;
		while(it.hasNext()&&!found) {
			col=it.next();
			if(k.equals(col.key)) {
				col.colli.addToHead(val);
				found=true;
			}				
		}
		if(!found) {
			col=new Collision();
			col.colli=new LinkedList<V>();
			col.key=k;
			col.colli.addToHead(val);
			table.add(col);
		}	
		
	}
	/**
	 * getter of the table parameter
	 * @return the table
	 */
	public ArrayList<Collision> getTable(){
		return table;
	}
}
